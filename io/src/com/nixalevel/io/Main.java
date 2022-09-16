package com.nixalevel.io;
//        Користувач вводить шлях до файлу або директорії, а також регулярний вираз(regEx).
//        Для цього файлу,або для всіх файлів в директорії та її піддиректоріях вивести в консоль:
//        Абсолютний шлях до файлу
//        Всі рядки в цьому файлі,де є групи символів, що задовільняють даний regEx(RX),
//                виділив ці групи за допомогою[]

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {
    public static List<File> listOfFilesFromGivenPath(String fileOrDirectoryName) {
        File directory = new File(fileOrDirectoryName);
        List<File> resultList = new LinkedList<File>();
        for (File file : directory.listFiles()) {
            if (!file.isDirectory()) {
                resultList.add(new File(file.getAbsolutePath()));
            } else if (file.isDirectory()) {
                resultList.addAll(listOfFilesFromGivenPath(file.getAbsolutePath()));
            }
        }
        return resultList;
    }

    private static void listContents(String path) throws IOException {
        File file = new File(path);
        Stream.of(Objects.requireNonNull(file.listFiles()))
                .forEach(System.out::println);
    }

    public static String roundedRegex(String string, String inputRegex) {
        Pattern regex = Pattern.compile(inputRegex);
        Matcher m = regex.matcher(string);
        StringBuffer sb = new StringBuffer(string.length());
        while (m.find()) {
            m.appendReplacement(sb, Matcher.quoteReplacement("[" + m.group(0) + "]"));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private static String fileToString(File fileToScan) {
        Path filePath = FileSystems.getDefault().getPath(fileToScan.getAbsolutePath());
        StringBuilder contentBuilder = new StringBuilder();
        if (Files.exists(filePath)) {
            try (Stream<String> stream = Files.lines(filePath, StandardCharsets.UTF_8)) {
                stream.forEach(s -> contentBuilder.append(s).append("\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String fileContent = contentBuilder.toString();
            return fileContent;
        }

        return "File or Dir doesnt exist";
    }

    private static List<File> extracted(String pathToScan) {
        List<File> listOfFiles = new LinkedList<File>();
        File file = (new File(pathToScan));
        if (file.exists()) {
            if (file.isFile()) {
                try {
                    listOfFiles.add(new File(file.getAbsolutePath()));
                } catch (Exception e) {
                    throw new RuntimeException("Error during get absolut path of given file");
                } finally {

                }
            } else if (file.isDirectory()) {
                try {
                    listOfFiles.addAll(listOfFilesFromGivenPath(file.getAbsolutePath()));
                } catch (Exception e) {
                    throw new RuntimeException("Error during get absolut path of files from given dir");
                } finally {

                }
            }
        } else {
            System.out.println("File or directories does not exist or incorrect name");
        }
        return listOfFiles;
    }

    public static void main(String[] args) throws IOException {
        String pathToScan = "d:/temp/demo.txt";
        String regex = "NIX";
//        Scanner scanner = new Scanner(System.in);
//        String pathToScan = scanner.next();
//        String regex = scanner.next();
        List<File> listOfFiles = extracted(pathToScan);
        for (File file : listOfFiles) {
            System.out.println(file.getAbsolutePath());
            System.out.println(roundedRegex(fileToString(file), regex));
        }
    }
}



