package com.nixalevel.module1;

import java.awt.Point;
import java.util.*;

public class MazePathFinderWithoutNode implements MazePathFinder {

    private Maze maze;
    private Point source;
    private Point target;
    private boolean[][] visited;
    private Map<Point, Point> parents;

    public MazePathFinderWithoutNode() {
    }

    private MazePathFinderWithoutNode(final Maze maze,
                                      final Point source,
                                      final Point target) {
        Objects.requireNonNull(maze, "The input maze is null.");
        Objects.requireNonNull(source, "The source node is null.");
        Objects.requireNonNull(target, "The target node is null.");
        this.maze = maze;
        this.source = source;
        this.target = target;
        checkSourceNode();
        checkTargetNode();
        this.visited = new boolean[maze.getHeight()][maze.getWidth()];
        this.parents = new HashMap<>();
        this.parents.put(source, null);
    }

    @Override
    public List<Point> findPath(final Maze maze, final Point source, final Point target) {
        return new MazePathFinderWithoutNode(maze, source, target).compute();
    }

    private List<Point> compute() {
        final Queue<Point> queue = new ArrayDeque<>();
        final Map<Point, Integer> distances = new HashMap<>();
        queue.add(source);
        distances.put(source, 0);
        while (!queue.isEmpty()) {
            final Point current = queue.remove();
            if (current.equals(target)) {
                return constructPath();
            }

            for (final Point child : generateChildren(current)) {
                if (!parents.containsKey(child)) {
                    parents.put(child, current);
                    queue.add(child);
                }
            }
        }
        return null;
    }

    private List<Point> constructPath() {
        Point current = target;
        final List<Point> path = new ArrayList<>();

        while (current != null) {
            path.add(current);
            current = parents.get(current);
        }

        Collections.<Point>reverse(path);
        return path;
    }

    private Iterable<Point> generateChildren(final Point current) {
        final Point north = new Point(current.x, current.y - 1);
        final Point south = new Point(current.x, current.y + 1);
        final Point west = new Point(current.x - 1, current.y);
        final Point east = new Point(current.x + 1, current.y);

        final List<Point> childList = new ArrayList<>(4);

        if (maze.cellIsTraversible(north)) {
            childList.add(north);
        }

        if (maze.cellIsTraversible(south)) {
            childList.add(south);
        }

        if (maze.cellIsTraversible(west)) {
            childList.add(west);
        }

        if (maze.cellIsTraversible(east)) {
            childList.add(east);
        }

        return childList;
    }

    private void checkSourceNode() {
        checkNode(source,
                "The source node (" + source + ") is outside the maze. " +
                        "The width of the maze is " + maze.getWidth() + " and " +
                        "the height of the maze is " + maze.getHeight() + ".");

        if (!maze.cellIsFree(source.x, source.y)) {
            throw new IllegalArgumentException(
                    "The source node (" + source + ") is at a occupied cell.");
        }
    }

    private void checkTargetNode() {
        checkNode(target,
                "The target node (" + target + ") is outside the maze. " +
                        "The width of the maze is " + maze.getWidth() + " and " +
                        "the height of the maze is " + maze.getHeight() + ".");

        if (!maze.cellIsFree(target.x, target.y)) {
            throw new IllegalArgumentException(
                    "The target node (" + target + ") is at a occupied cell.");
        }
    }

    private void checkNode(final Point node, final String errorMessage) {
        if (node.x < 0
                || node.x >= maze.getWidth()
                || node.y < 0
                || node.y >= maze.getHeight()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}