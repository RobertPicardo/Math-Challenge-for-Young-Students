package com.example.mathchallenge.Maze;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;

public class MazeDeadEnd extends Application {

    private Label statusLabel;
    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);
        statusLabel = new Label("Navigate through the maze!");

        // The game UI components

        root.getChildren().addAll(statusLabel);
        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("Maze Game");
        stage.setScene(scene);
        stage.show();
    }

    private void notifyDeadEnd() {
        statusLabel.setText("You have reached a dead end. Try a different path!");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dead End Reached!");
        alert.setHeaderText(null);
        alert.setContentText("You have reached a dead end. Try a different path!");
        alert.showAndWait();

        // Play end sound
        String soundPath = "C:/Users/vip-7/Downloads/End_sound.mp3";
        File soundFile = new File(soundPath);
        if(soundFile.exists()) {
            Media sound = new Media(soundFile.toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        } else {
            System.out.println("Sound file not found: " + soundPath);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    // remaining class....etc
}
