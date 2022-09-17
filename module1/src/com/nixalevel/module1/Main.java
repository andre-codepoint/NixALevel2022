package com.nixalevel.module1;

import java.awt.*;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        //InputMazeData inputMazeData = new InputMazeFromFile("D:\\Nix\\NixALevel\\module1\\src\\com\\nixalevel\\module1\\input.txt");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter path to file: ");
        InputMazeData inputMazeData = new InputMazeFromFile(scanner.next());
        final Maze maze = new Maze(inputMazeData.inputMaze());
        System.out.print("Enter X, Y of Start point: ");
        final Point start = new Point(scanner.nextInt(), scanner.nextInt());
        System.out.print("Enter X, Y of End point: ");
        final Point end = new Point(scanner.nextInt(), scanner.nextInt());
        final List<Point> path = new MazePathFinderWithoutNode().findPath(maze, start, end);
        System.out.println("Shortest path length: " + (path.size()));
        System.out.println(maze.withPath(path));
    }
}
