package com.lairon.lairconfig;


import com.google.common.io.Files;
import com.lairon.lairconfig.annotations.ConfigComment;
import com.lairon.lairconfig.annotations.ConfigPath;
import org.bspfsystems.yamlconfiguration.configuration.ConfigurationSection;
import org.bspfsystems.yamlconfiguration.configuration.InvalidConfigurationException;
import org.bspfsystems.yamlconfiguration.file.FileConfiguration;
import org.bspfsystems.yamlconfiguration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public final class LairConfig {

    private File file;
    private FileConfiguration config;

    private List<DataField> registeredFields = new ArrayList<>();

    public LairConfig(File file) {
        this.file = file;
    }

    public LairConfig(String filePath) {
        this(new File(filePath));
    }

    private void reloadFileConfiguration() throws IOException, InvalidConfigurationException {
        boolean save = false;
        if (!file.exists()) {
            Files.createParentDirs(file);
            file.createNewFile();
            save = true;
        }
        if (config == null)
            config = YamlConfiguration.loadConfiguration(file);
        else
            config.load(file);
        if (save) {
            for (DataField registeredField : registeredFields) {
                setValueIntoConfig(registeredField);
            }
            config.save(file);
        }
    }

    public void reload() throws IOException, InvalidConfigurationException, IllegalAccessException {
        reloadFileConfiguration();
        boolean save = false;
        for (DataField registeredField : registeredFields) {
            String path = registeredField.getPath();
            Object data = config.get(path);
            Field field = registeredField.getField();
            if (data == null) {
                save = true;

                setValueIntoConfig(registeredField);

                field.setAccessible(true);
                field.set(registeredField.getStorageClass(), registeredField.getValue());
                field.setAccessible(false);
                continue;
            }
            field.setAccessible(true);
            if (registeredField.getValue() instanceof Enum enumData) {
                field.set(registeredField.getStorageClass(), Enum.valueOf(enumData.getClass(), (String) data));
            } else if (registeredField.getValue() instanceof Map) {
                Map<String, String> map = new HashMap<>();
                ConfigurationSection configurationSection = config.getConfigurationSection(path);
                if (configurationSection != null) {
                    for (String key : configurationSection.getKeys(false)) {
                        map.put(key, config.getString(path + "." + key));
                    }
                } else {
                    setValueIntoConfig(registeredField);
                    save = true;
                }
                if (save)
                    field.set(registeredField.getStorageClass(), registeredField.getValue());
                else
                    field.set(registeredField.getStorageClass(), map);
            } else {
                field.set(registeredField.getStorageClass(), data);
            }
            field.setAccessible(false);
        }
        if (save)
            config.save(file);
    }

    private void setValueIntoConfig(DataField field) {
        String path = field.getPath();

        if (field.getValue() instanceof Enum enumData) {
            config.set(path, enumData.name());
        } else {
            config.set(path, field.getValue());
        }

        if (field.getComments() != null) {
            config.setComments(path, field.getComments());
        }
    }

    public void registerStorageClass(StorageClass storageClass) throws IllegalAccessException {
        ArrayList<Field> fields = new ArrayList<>(List.of(storageClass.getClass().getDeclaredFields()));
        Class<?> superclass = storageClass.getClass().getSuperclass();
        if (superclass != null)
            fields.addAll(List.of(superclass.getDeclaredFields()));
        for (Field field : fields) {
            field.setAccessible(true);
            Object data = field.get(storageClass);
            if (data instanceof StorageClass) {
                registerStorageClass((StorageClass) data);
                continue;
            }
            ConfigPath configPath = field.getAnnotation(ConfigPath.class);
            if (configPath == null) continue;
            ConfigComment configComment = field.getAnnotation(ConfigComment.class);
            DataField dataField = new DataField(storageClass.getPath() + configPath.value(), field, data, storageClass);
            field.setAccessible(false);
            if (configComment != null)
                dataField.setComments(Arrays.asList(configComment.value()));
            registeredFields.add(dataField);
        }
    }


}
