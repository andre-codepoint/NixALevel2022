package com.alevel.java.morsecodec.codec;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigurableTextCodecTest {


    @Test
    void testEncoding() {
        var codec = new ConfigurableTextCodec();
        assertEquals("", codec.encode(null));
        assertEquals("", codec.encode(""));
        assertEquals("", codec.encode(" "));
        assertEquals("", codec.encode("   "));
        assertEquals(".-", codec.encode("A"));
        assertEquals(".-", codec.encode("a"));
        assertEquals("....   .   .-..   .-..   ---       .--   ---   .-.   .-..   -..",
                codec.encode("HELLO WORLD"));
        assertEquals(".-.-.-", codec.encode("."));
        assertEquals("--..--", codec.encode(","));
        assertEquals("..--..", codec.encode("?"));
        assertEquals("-.-.--", codec.encode("!"));
    }

    @Test
    void testDecoding() {
        var codec = new ConfigurableTextCodec();
        assertEquals("", codec.decode(null));
        assertEquals("", codec.decode(""));
        assertEquals("", codec.decode(" "));
        assertEquals("", codec.decode("   "));
        assertEquals("A", codec.decode(".-"));
        assertEquals("HELLO WORLD",
                codec.decode("....   .   .-..   .-..   ---       .--   ---   .-.   .-..   -.."));
    }

    @Test
    void testConfig() {
        var options = ConfigurableTextCodec.ConfigOptions.builder()
                .dash('=')
                .dot('*')
                .charSeparator("/")
                .wordSeparator(" ")
                .build();

        var codec = new ConfigurableTextCodec(options);

        assertEquals("123 A", codec.decode("*====/**===/***== *="));
        assertEquals("*====/**===/***== *=", codec.encode("123 A"));
    }
}