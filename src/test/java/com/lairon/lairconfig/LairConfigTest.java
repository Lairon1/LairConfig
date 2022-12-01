package com.lairon.lairconfig;

import org.bspfsystems.yamlconfiguration.configuration.InvalidConfigurationException;
import org.junit.Test;

import java.io.IOException;

public class LairConfigTest {

    @Test
    public void test() throws IllegalAccessException, IOException, InvalidConfigurationException {
        LairConfig lairConfig = new LairConfig("C:\\Users\\Lairon\\IdeaProjects\\LairConfig\\src\\test\\resources\\test.yml");
        StorageTest storageClass = new StorageTest();
        lairConfig.registerStorageClass(storageClass);
        lairConfig.reload();;
    }

}