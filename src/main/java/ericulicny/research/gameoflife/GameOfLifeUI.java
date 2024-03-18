package ericulicny.research.gameoflife;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.control.Label;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class GameOfLifeUI extends Application {
    public static long SLEEPTIME_MS=5000;
    public static int ITERATIONS = 100;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        AtomicReference<Integer> iterationCount = new AtomicReference<>(new Integer(0));
        GameOfLife game = new GameOfLife();
        // Initialize your game here
        game.init(10, 10, new ArrayList<Point>(Arrays.asList(new Point(3, 3), new Point(3, 2), new Point(2, 2), new Point(1, 2))));
        GridPane gridPane = createGrid(game);
        Label iterationLabel = new Label("Iteration: 0");
        VBox vbox = new VBox(iterationLabel, gridPane);
        primaryStage.setScene(new Scene(vbox));
        primaryStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(SLEEPTIME_MS), event -> {
            if(game.iterate()) {
                GridPane newGridPane = createGrid(game);
                vbox.getChildren().set(1, newGridPane);
                iterationCount.getAndSet(iterationCount.get() + 1);
                iterationLabel.setText("Iteration: " + iterationCount);
            }
        }));

        timeline.setCycleCount(ITERATIONS);
        timeline.play();
    }

    private GridPane createGrid(GameOfLife game) {
        GridPane gridPane = new GridPane();
        int[][] board = game.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Rectangle cell = new Rectangle(20, 20);
                cell.setFill(board[i][j] == 1 ? Color.BLACK : Color.WHITE);
                int finalJ = j;
                int finalI = i;
                cell.setOnMouseClicked(event -> {
                    game.toggleCell(finalI, finalJ);
                    cell.setFill(board[finalI][finalJ] == 1 ? Color.BLACK : Color.WHITE);
                });
                gridPane.add(cell, j, i);
            }
        }
        return gridPane;
    }


}