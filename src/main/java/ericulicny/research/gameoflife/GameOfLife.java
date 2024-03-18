package ericulicny.research.gameoflife;

import java.awt.*;
import java.util.ArrayList;

public class GameOfLife {

    private int[][] grid;
    private int gridsizeX = 0;
    private int gridsizeY = 0;

    public void init(int sizex, int sizey, ArrayList<Point> startingPositions) {
        gridsizeX = sizex;
        gridsizeY = sizey;
        grid = new int[gridsizeX][gridsizeY];
        for (int i = 0; i < sizex; i++ ) {
            for(int j = 0; j <sizey; j++ ){
                grid[i][j] = 0;
            }
        }

        // Set starting points
        for (Point p: startingPositions) {
            grid[p.x][p.y] = 1;
        }
    }

    public void toggleCell(int x, int y) {
        grid[x][y] = grid[x][y] == 1 ? 0 : 1;
    }

    /**
     * Method iterates the rules for the game of life
     * @return true if new grid is different from previous grid
     */
    public boolean iterate() {
        int [][] newGrid = new int[gridsizeX][gridsizeY];
        for (int i = 0; i < gridsizeX; i++ ) {
            for(int j = 0; j < gridsizeY; j++ ){
                int countedNeighbors = countNeighbors(i, j);

                // Rule: Any live cell with fewer than two live neighbors dies as if caused by underpopulation.
                // Rule: Any live cell with more than three live neighbors dies, as if by overpopulation.
                if (grid[i][j] == 1 && (countedNeighbors  < 2 || countedNeighbors > 3)) {
                    newGrid[i][j] = 0;
                }

                //  Rule: Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
                if(grid[i][j] == 0 && countedNeighbors == 3) {
                    newGrid[i][j] = 1;
                }

                // Rule: Any live cell with two or three live neighbors lives on to the next generation.
                if(grid[i][j] == 1 && (countedNeighbors == 3 || countedNeighbors == 2)) {
                    newGrid[i][j] = 1;
                }
            }
        }

        if(checkStableState(grid, newGrid))
            return false;

        grid = newGrid;
        return true;
    }

    /**
     * Counts the # of alive neighbors for a given node
     * @param x
     * @param y
     * @return
     */
    public int countNeighbors(int x, int y) {
        int aliveNeighbors = 0;

        for(int offset_x = -1; offset_x <=1; offset_x++){
            for(int offset_y = -1; offset_y <=1; offset_y++) {
                //Don't count yourself
                if(offset_x != 0  || offset_y != 0 ) {
                    //ensure bounds are respected
                    if((offset_x + x) < gridsizeX && (offset_x + x) >= 0 && (offset_y + y) < gridsizeY && (offset_y + y) >= 0) {
                        if (grid[offset_x + x][offset_y + y] == 1) {
                            aliveNeighbors++;
                        }
                    }
                }
            }
        }

        return  aliveNeighbors;


    }

    /**
     * Gets the current board state
     * @return
     */
    public int[][] getBoard() {
        return grid;
    }

    public boolean checkStableState(int[][] grid1, int[][] grid2){
        boolean stableState = false;
        for(int i = 0; i < gridsizeX; i++ ) {
            for (int j = 0; j < gridsizeY; j++) {
                if(grid1[i][j] != grid2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
