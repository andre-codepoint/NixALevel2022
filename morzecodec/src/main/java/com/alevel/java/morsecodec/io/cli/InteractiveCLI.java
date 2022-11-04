package com.alevel.java.morsecodec.io.cli;

import com.alevel.java.morsecodec.codec.TextCodec;
import com.alevel.java.morsecodec.io.InputReader;
import com.alevel.java.morsecodec.io.OutputWriter;
import com.alevel.java.morsecodec.io.file.FileInputReader;
import com.alevel.java.morsecodec.io.file.FileOutputWriter;

import java.nio.file.Path;
import java.util.Scanner;

public class InteractiveCLI {

    private final TextCodec codec;

    public InteractiveCLI(TextCodec codec) {
        this.codec = codec;
    }

    public void run() {
        var scanner = new Scanner(System.in);

        Mode mode = getMode(scanner);

        InputReader inputReader = getInputReader(scanner);

        OutputWriter outputWriter = getOutputWriter(scanner);

        String input = inputReader.read();

        String output = switch (mode) {
            case DECODE -> codec.decode(input);
            case ENCODE -> codec.encode(input);
        };

        outputWriter.write(output);
    }

    private static OutputWriter getOutputWriter(Scanner scanner) {
        OutputWriter outputWriter;

        System.out.println("Input path to output file (leave empty for console input)");

        String outputPath = scanner.nextLine();
        if (outputPath.isBlank()) {
            outputWriter = new StdOutputWriter();
        } else {
            outputWriter = new FileOutputWriter(Path.of(outputPath));
        }
        return outputWriter;
    }

    private static InputReader getInputReader(Scanner scanner) {
        InputReader inputReader;

        System.out.println("Input path to input file (leave empty for console input)");

        String inputPath = scanner.nextLine();
        if (inputPath.isBlank()) {
            inputReader = new StdInputReader();
        } else {
            inputReader = new FileInputReader(Path.of(inputPath));
        }
        return inputReader;
    }

    private static Mode getMode(Scanner scanner) {
        Mode mode;

        do {
            System.out.printf("Input mode:%n0: decoding%n1: encoding%n");
            mode = switch (scanner.nextInt()) {
                case 0 -> Mode.DECODE;
                case 1 -> Mode.ENCODE;
                default -> null;
            };
            scanner.nextLine();

        } while (mode == null);

        return mode;
    }

    private enum Mode {
        DECODE, ENCODE
    }

}
