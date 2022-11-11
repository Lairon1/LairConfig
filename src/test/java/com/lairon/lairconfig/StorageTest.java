package com.lairon.lairconfig;

import com.lairon.lairconfig.annotations.ConfigComment;
import com.lairon.lairconfig.annotations.ConfigPath;

public class StorageTest extends StorageClass {

    @ConfigPath("SQL.USERNAME")
    private String userName = "Lairon1";

    @ConfigPath("description")
    private String description = "[^a-zA-Z0-9а-яА-Я!\"ё#$%&'()*+,.\\/:;<=>?@\\[\\] ^_`{|}~-]+";

    @ConfigComment("asdasdasdas")
    @ConfigPath("TestEnum.enum")
    private TestEnum testEnum = TestEnum.TEST;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPath() {
        return "Test.";
    }
}
