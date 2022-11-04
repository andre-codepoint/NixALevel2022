package com.alevel.java.morsecodec.io.file;

import com.alevel.java.morsecodec.io.OutputWriter;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class FileOutputWriter implements OutputWriter {

    private final Path path;

    public FileOutputWriter(Path path) {
        this.path = Objects.requireNonNull(path, "Path can't be null");
    }

    @Override
    public void write(String s) {
        try {
            Files.writeString(path, s, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }
}
