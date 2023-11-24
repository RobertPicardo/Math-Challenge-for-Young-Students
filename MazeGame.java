package Maze;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MazeGame extends Application {

    private Label feedbackLabel;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10); // For layout
        feedbackLabel = new Label("Make a move in the maze!");

        Button correctButton = new Button("Correct Move");
        correctButton.setOnAction(e -> provideFeedback(true));

        Button incorrectButton = new Button("Incorrect Move");
        incorrectButton.setOnAction(e -> provideFeedback(false));

        root.getChildren().addAll(feedbackLabel, correctButton, incorrectButton);

        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("Maze Game");
        stage.setScene(scene);
        stage.show();
    }

    private void provideFeedback(boolean isCorrect) {
        if (isCorrect) {
            feedbackLabel.setText("Correct! Good job.");
        } else {
            feedbackLabel.setText("Incorrect. Try again.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
