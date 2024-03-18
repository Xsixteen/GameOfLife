package ericulicny.research.gameoflife;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ConsoleDriver {

    public static long SLEEPTIME_MS=5000;
    public static int ITERATIONS = 100;

    public static void main(String[] args) throws InterruptedException {
        GameOfLife gameOfLife = new GameOfLife();
        int iterationCount = 0;
        ArrayList<Point> pointArrayList = new ArrayList<>();

        pointArrayList.add(new Point(3,3));
        pointArrayList.add(new Point(3,2));
        pointArrayList.add(new Point(2,2));
        pointArrayList.add(new Point(1,2));

        pointArrayList.add(generateRandomPoint(10,10));
        pointArrayList.add(generateRandomPoint(10,10));
        pointArrayList.add(generateRandomPoint(10,10));
        pointArrayList.add(generateRandomPoint(10,10));
        pointArrayList.add(generateRandomPoint(10,10));
        pointArrayList.add(generateRandomPoint(10,10));

        gameOfLife.init(10,10,pointArrayList);
        displayBoard(10,10, gameOfLife.getBoard());
        for(int i = 0; i < ITERATIONS; i++ ){
            if(!gameOfLife.iterate())
                break;
            System.out.println("");
            displayBoard(10,10, gameOfLife.getBoard());
            iterationCount++;
            Thread.sleep(SLEEPTIME_MS);
        }
        System.out.println("System has reached a stable state after " + iterationCount + " iterations.");
    }

    public static Point generateRandomPoint(int boardXSize, int boardYSize) {
        Random rand = new Random();
        Point point = new Point(rand.nextInt(boardXSize), rand.nextInt(boardYSize));

        return point;
    }

    private static void displayBoard(int xmax, int ymax, int[][] board) {
        for(int i = 0; i < xmax; i++){
            for (int j = 0; j < ymax; j++) {
                if(board[i][j] == 0) {
                    System.out.print(" . ");
                } else {
                    System.out.print(" * ");
                }
            }
            System.out.println("");
        }
    }
}
