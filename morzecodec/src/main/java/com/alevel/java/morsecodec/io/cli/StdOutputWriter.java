package com.alevel.java.morsecodec.io.cli;

import com.alevel.java.morsecodec.io.OutputWriter;

public class StdOutputWriter implements OutputWriter {

    @Override
    public void write(String s) {
        System.out.println(s);
    }

}
