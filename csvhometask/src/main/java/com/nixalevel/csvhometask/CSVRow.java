package com.nixalevel.csvhometask;

import java.util.ArrayList;
import java.util.List;

public class CSVRow {
    private List<String> row;

    public CSVRow(List<String> strings) {
        this.row = strings;
    }

    public List<String> get() {
        return row;
    }


}
