package com.alevel.java.morsecodec.config;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Properties;

public class PropertiesConfig {

    public Properties defaultAppProperties() {
        try(var input = getClass().getResourceAsStream("/application.properties")) {
            var props = new Properties();
            props.load(input);
            return props;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
