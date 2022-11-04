package com.alevel.java.morsecodec.io.cli;

import com.alevel.java.morsecodec.io.InputReader;

import java.util.Scanner;

public class StdInputReader implements InputReader {

    @Override
    public String read() {
        return new Scanner(System.in).nextLine();
    }

}
