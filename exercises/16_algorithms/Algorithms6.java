// Algorithms6.java
//
// Breadth-first search on a grid. '.' is open, '#' is a wall. Find the
// length of the shortest path from S to E moving up/down/left/right, or -1
// if there is none.
//
// BFS: keep a queue of cells to visit, starting with S at distance 0. Take
// the front cell, look at its four neighbours; every open neighbour that was
// not seen yet goes into the queue with distance + 1. The first time you
// reach E, its distance is the answer. Because the queue is first-in
// first-out, cells are visited in order of distance.
//
// Useful: Deque<int[]> queue = new ArrayDeque<>(); queue.add(new int[]{r, c});
// queue.poll() takes from the front. A boolean[][] or int[][] for distances.

// I AM NOT DONE

import javalings.Check;
import java.util.ArrayDeque;
import java.util.Deque;

public class Algorithms6 {
    public static void main(String[] args) {
        String[] maze1 = {
            "S.#",
            ".##",
            "..E",
        };
        Check.equals(4, shortestPath(maze1), "small maze");

        String[] maze2 = {
            "S.......",
            ".######.",
            ".#....#.",
            ".#.##.#.",
            ".#.#E.#.",
            ".#.####.",
            ".#......",
            "........",
        };
        Check.equals(20, shortestPath(maze2), "bigger maze");

        String[] walled = {
            "S#E",
        };
        Check.equals(-1, shortestPath(walled), "no path");

        String[] trivial = {
            "SE",
        };
        Check.equals(1, shortestPath(trivial), "next to each other");
    }

    static int shortestPath(String[] grid) {
        return -1;
    }
}
