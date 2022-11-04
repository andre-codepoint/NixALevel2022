package com.alevel.java.morsecodec.codec;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Map.entry;
import static java.util.function.Predicate.not;

public class ConfigurableTextCodec implements TextCodec {

    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\s");

    private final Map<String, String> enc;

    private final Map<String, String> dec;

    private final Pattern wordSeparator;

    private final Pattern charSeparator;

    public ConfigurableTextCodec(ConfigOptions options) {
        this.enc = options.encodings();
        this.dec = this.enc.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        this.wordSeparator = Pattern.compile(options.wordSeparator());
        this.charSeparator = Pattern.compile(options.charSeparator());
    }

    public ConfigurableTextCodec() {
        this(ConfigOptions.defaultOptions());
    }

    @Override
    public String encode(CharSequence input) {
        if (input == null) {
            return "";
        }

        return WHITESPACE_PATTERN.splitAsStream(input)
                .map(word -> word.chars()
                        .mapToObj((c -> encodeCharacter((char) c)))
                        .collect(Collectors.joining(charSeparator.pattern())))
                .collect(Collectors.joining(wordSeparator.pattern()));
    }

    @Override
    public String decode(CharSequence input) {
        if (input == null) {
            return "";
        }

        return wordSeparator.splitAsStream(input)
                .filter(not(String::isBlank))
                .map(word -> charSeparator
                        .splitAsStream(word)
                        .map(this::decodeSequence)
                        .collect(Collectors.joining()))
                .collect(Collectors.joining(" "));
    }

    public String encodeCharacter(char c) {
        String key = String.valueOf(Character.toUpperCase(c));
        String encoded = enc.get(key);
        if (encoded == null) {
            throw new IllegalArgumentException("No Morse representation found for the key: " + key);
        }
        return encoded;
    }

    private String decodeSequence(String sequence) {
        String decoded = dec.get(sequence);
        if (decoded == null) {
            throw new IllegalArgumentException("Unknown Morse sequence: " + sequence);
        }
        return decoded;
    }

    public record ConfigOptions(char dash, char dot, String charSeparator, String wordSeparator) {

        public Map<String, String> encodings() {
            return Map.ofEntries(
                    // letters
                    entry("A", of(dot, dash)),
                    entry("B", of(dash, dot, dot, dot)),
                    entry("C", of(dash, dot, dash, dot)),
                    entry("D", of(dash, dot, dot)),
                    entry("E", of(dot)),
                    entry("F", of(dot, dot, dash, dot)),
                    entry("G", of(dash, dash, dot)),
                    entry("H", of(dot, dot, dot, dot)),
                    entry("I", of(dot, dot)),
                    entry("J", of(dot, dash, dash, dash)),
                    entry("K", of(dash, dot, dash)),
                    entry("L", of(dot, dash, dot, dot)),
                    entry("M", of(dash, dash)),
                    entry("N", of(dash, dot)),
                    entry("O", of(dash, dash, dash)),
                    entry("P", of(dot, dash, dash, dot)),
                    entry("Q", of(dash, dash, dot, dash)),
                    entry("R", of(dot, dash, dot)),
                    entry("S", of(dot, dot, dot)),
                    entry("T", of(dash)),
                    entry("U", of(dot, dot, dash)),
                    entry("V", of(dot, dot, dot, dash)),
                    entry("W", of(dot, dash, dash)),
                    entry("X", of(dash, dot, dot, dash)),
                    entry("Y", of(dash, dot, dash, dash)),
                    entry("Z", of(dash, dash, dot, dot)),

                    // digits
                    entry("1", of(dot, dash, dash, dash, dash)),
                    entry("2", of(dot, dot, dash, dash, dash)),
                    entry("3", of(dot, dot, dot, dash, dash)),
                    entry("4", of(dot, dot, dot, dot, dash)),
                    entry("5", of(dot, dot, dot, dot, dot)),
                    entry("6", of(dash, dot, dot, dot, dot)),
                    entry("7", of(dash, dash, dot, dot, dot)),
                    entry("8", of(dash, dash, dash, dot, dot)),
                    entry("9", of(dash, dash, dash, dash, dot)),
                    entry("0", of(dash, dash, dash, dash, dash)),

                    //todo punctuation
                    entry(".", of(dot, dash, dot, dash, dot, dash)),
                    entry(",", of(dash, dash, dot, dot, dash, dash)),
                    entry("!", of(dash, dot, dash, dot, dash,dash)),
                    entry("?", of(dot, dot, dash, dash, dot, dot))
            );
        }


        private static String of(char... chars) {
            return String.valueOf(chars);
        }

        public static ConfigOptions defaultOptions() {
            return new ConfigOptions('-', '.', "   ", "       ");
        }

        public static Builder builder() {
            return new Builder();
        }

        public static final class Builder {

            private char dash = '-';

            private char dot = '.';

            private String charSeparator = "   ";

            private String wordSeparator = "       ";

            public Builder dash(char c) {
                this.dash = c;
                return this;
            }

            public Builder dot(char c) {
                this.dot = c;
                return this;
            }

            public Builder charSeparator(String s) {
                this.charSeparator = s;
                return this;
            }

            public Builder wordSeparator(String s) {
                this.wordSeparator = s;
                return this;
            }

            public ConfigOptions build() {
                return new ConfigOptions(dash, dot, charSeparator, wordSeparator);
            }

        }

    }


}
