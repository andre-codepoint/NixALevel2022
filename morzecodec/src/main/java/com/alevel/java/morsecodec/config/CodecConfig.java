package com.alevel.java.morsecodec.config;

import com.alevel.java.morsecodec.codec.ConfigurableTextCodec;
import com.alevel.java.morsecodec.codec.TextCodec;

import java.util.Properties;

public class CodecConfig {

    private final Properties appProperties;

    public CodecConfig(Properties appProperties) {
        this.appProperties = appProperties;
    }

    public TextCodec textCodec() {
        var configOptionsBuilder = ConfigurableTextCodec.ConfigOptions.builder();

        String dash;
        if ((dash = appProperties.getProperty("codec.dash")) != null) {
            configOptionsBuilder.dash(dash.charAt(0));
        }

        String dot;
        if ((dot = appProperties.getProperty("codec.dot")) != null) {
            configOptionsBuilder.dot(dot.charAt(0));
        }

        String charSeparator;
        if ((charSeparator = appProperties.getProperty("codec.charSeparator")) != null) {
            configOptionsBuilder.charSeparator(charSeparator);
        }

        String wordSeparator;
        if ((wordSeparator = appProperties.getProperty("codec.wordSeparator")) != null) {
            configOptionsBuilder.wordSeparator(wordSeparator);
        }

        return new ConfigurableTextCodec(configOptionsBuilder.build());
    }

}
