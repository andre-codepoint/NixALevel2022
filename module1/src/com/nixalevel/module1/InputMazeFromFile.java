package com.nixalevel.module1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;

public class InputMazeFromFile implements InputMazeData {
    String file;


    public InputMazeFromFile(String file) {
        this.file = file;
    }

    @Override
    public boolean[][] inputMaze() {
        boolean[][] maze = new boolean[0][];
        //Scanner user_input = new Scanner(System.in);
        //System.out.print("Enter maze file : ");
        //String filename = user_input.nextLine();
        //user_input.close();
        String filename = this.file;
        int width = 0, height = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                height++;
                width = width + line.length();
            }
        } catch (IOException e) {
            throw new UncheckedIOException(new IOException("File I/O error"));
        }
        if (width % height == 0) {
            maze = new boolean[height][width / height];
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                StringBuilder sb = new StringBuilder();
                int num = 0;
                while ((line = reader.readLine()) != null) {
                    char[] ch = line.toCharArray();
                    for (int i = 0; i < ch.length; i++) {
                        if (ch[i] == '-')
                            maze[num][i] = true;
                        if (ch[i] == '+')
                            maze[num][i] = false;
                    }
                    num++;
                }
            } catch (IOException e) {
                throw new UncheckedIOException(new IOException("File I/O error"));
            }
        } else {
            System.out.println("Incorrect maze data in file");
        }
        return maze;
    }

}
