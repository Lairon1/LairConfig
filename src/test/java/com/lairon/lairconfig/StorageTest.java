package com.lairon.lairconfig;

import com.lairon.lairconfig.annotations.ConfigComment;
import com.lairon.lairconfig.annotations.ConfigPath;

import java.util.Map;

public class StorageTest extends StorageClass {

    @ConfigPath("SQL.USERNAME")
    private Map<String, String> test = Map.of("testss", "asd");
}
