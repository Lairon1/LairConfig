package com.lairon.lairconfig;

import java.lang.reflect.Field;
import java.util.List;

public class DataField {

    private String path;
    private Field field;
    private Object value;
    private Object storageClass;
    private List<String> comments;

    protected DataField(String path, Field field, Object value, Object storageClass) {
        this.path = path;
        this.field = field;
        this.value = value;
        this.storageClass = storageClass;
    }

    protected String getPath() {
        return path;
    }

    protected Field getField() {
        return field;
    }

    protected Object getValue() {
        return value;
    }

    protected List<String> getComments() {
        return comments;
    }

    protected void setPath(String path) {
        this.path = path;
    }

    protected void setField(Field field) {
        this.field = field;
    }

    protected void setValue(Object value) {
        this.value = value;
    }

    protected void setComments(List<String> comments) {
        this.comments = comments;
    }

    protected Object getStorageClass() {
        return storageClass;
    }

    protected void setStorageClass(Object storageClass) {
        this.storageClass = storageClass;
    }
}
