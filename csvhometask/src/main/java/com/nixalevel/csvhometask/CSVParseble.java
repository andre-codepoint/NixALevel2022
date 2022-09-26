package com.nixalevel.csvhometask;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;

public interface CSVParseble {
    CSVTable parse(Path source) throws IOException;

    CSVTable parse(Reader source) throws IOException;
}
