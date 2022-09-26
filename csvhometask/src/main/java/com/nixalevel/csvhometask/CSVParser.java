package com.nixalevel.csvhometask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class CSVParser implements CSVParseble {
    private String delimiter = ",";

    public CSVParser(String delimiter) {
        this.delimiter = delimiter;
    }


    @Override
    public CSVTable parse(Path source) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(source)) {
            String header = reader.readLine();
            if (header == null) {
                throw new IOException("Can't parse file is empty");
            }
            CSVTable table = new CSVTable(parseLine(header));
            String line;
            while ((line = reader.readLine()) != null) {
                table.addRow(parseLine(line));
            }
            return table;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    @Override
    public CSVTable parse(Reader source) {
        return null;
    }

    private CSVRow parseLine(String line) {
        return new CSVRow(Arrays.asList(line.split(this.delimiter)));
    }

}
