package com.alevel.java.morsecodec.io.file;

import com.alevel.java.morsecodec.io.InputReader;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class FileInputReader implements InputReader {

    private final Path path;

    public FileInputReader(Path path) {
        this.path = Objects.requireNonNull(path, "Path can't be null");
    }

    @Override
    public String read() {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
