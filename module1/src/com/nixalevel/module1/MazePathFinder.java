package com.nixalevel.module1;

import java.awt.*;
import java.util.List;

public interface MazePathFinder {

    List<Point> findPath(Maze maze,
                         Point source,
                         Point target);
}
