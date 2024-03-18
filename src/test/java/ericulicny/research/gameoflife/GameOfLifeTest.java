package ericulicny.research.gameoflife;

import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class GameOfLifeTest {

    @Test
    public void testInit() {
        GameOfLife game = new GameOfLife();
        ArrayList<Point> startingPositions = new ArrayList<>();
        startingPositions.add(new Point(0, 0));
        game.init(3, 3, startingPositions);
        int[][] expectedBoard = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertArrayEquals(expectedBoard, game.getBoard());
    }

    @Test
    public void testCountNeighbors() {
        GameOfLife game = new GameOfLife();
        ArrayList<Point> startingPositions = new ArrayList<>();
        startingPositions.add(new Point(0, 0));
        startingPositions.add(new Point(0, 1));
        startingPositions.add(new Point(1, 0));
        game.init(3, 3, startingPositions);
        assertEquals(2, game.countNeighbors(0, 0));
    }

    @Test
    public void testIterate() {
        GameOfLife game = new GameOfLife();
        ArrayList<Point> startingPositions = new ArrayList<>();
        startingPositions.add(new Point(0, 0));
        startingPositions.add(new Point(0, 1));
        startingPositions.add(new Point(1, 0));
        game.init(3, 3, startingPositions);
        game.iterate();
        int[][] expectedBoard = {{1, 1, 0}, {1, 1, 0}, {0, 0, 0}};
        assertArrayEquals(expectedBoard, game.getBoard());
    }

    @Test
    public void testGetBoard() {
        GameOfLife game = new GameOfLife();
        ArrayList<Point> startingPositions = new ArrayList<>();
        startingPositions.add(new Point(0, 0));
        game.init(3, 3, startingPositions);
        int[][] expectedBoard = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertArrayEquals(expectedBoard, game.getBoard());
    }

    @Test
    public void testCheckStableState() {
        GameOfLife game = new GameOfLife();
        ArrayList<Point> startingPositions = new ArrayList<>();
        startingPositions.add(new Point(0, 0));
        game.init(3, 3, startingPositions);
        int[][] board1 = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int[][] board2 = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        assertTrue(game.checkStableState(board1, board2));
    }
}