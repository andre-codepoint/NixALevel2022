package com.nixalevel.csvhometask;

import java.util.ArrayList;
import java.util.List;

public class CSVTable {
    private final CSVRow header;

    private final List<CSVRow> table = new ArrayList<>();

    public CSVTable(CSVRow header) {
        this.header = header;
    }

    public void addRow(CSVRow csvRow) {
        table.add(csvRow);
    }

    public List<String> getHeader() {
        return header.get();
    }

    public CSVRow getRow(int i){
        return table.get(i);
    }

    public List<CSVRow> getRows(){
        return table;
    }

    public String getItem(int i, int j){
        return getRow(i).get().get(j);
    }

    public int getColumnIndex(String j){
        int k = 0;
        for (String s:header.get()) {
            if (j.equalsIgnoreCase(s)) return k;
            k++;
        }
        return -1;
    }

    public String getItem(int i, String j){
        int k = 0;
        for (String s:header.get()) {
            if (j.equalsIgnoreCase(s)) return getItem(i,k);
            k++;
        }
        return null;
    }
}
