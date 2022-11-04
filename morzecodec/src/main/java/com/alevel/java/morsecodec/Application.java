package com.alevel.java.morsecodec;

import com.alevel.java.morsecodec.codec.TextCodec;
import com.alevel.java.morsecodec.config.CodecConfig;
import com.alevel.java.morsecodec.config.PropertiesConfig;
import com.alevel.java.morsecodec.io.cli.InteractiveCLI;

import java.util.Properties;

public class Application {

    private InteractiveCLI cli;

    public Application() {
        init();
    }

    private void init() {
        Properties appProperties = new PropertiesConfig().defaultAppProperties();
        TextCodec codec = new CodecConfig(appProperties).textCodec();
        this.cli = new InteractiveCLI(codec);
    }

    public void run() {
        cli.run();
    }

    public static void main(String[] args) {
        new Application().run();
    }

}