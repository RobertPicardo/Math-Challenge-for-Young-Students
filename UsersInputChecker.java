package com.example.mathchallenge.Maze;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UsersInputChecker extends Application {

        private Game_Controller_V2 controller = new Game_Controller_V2();
        private TextField answerInput = new TextField();

        @Override
        public void start(Stage stage) {
            VBox root = new VBox(10);

            // display question
            String question = controller.Generate_Question();

            Button submitButton = new Button("Submit Answer");
            submitButton.setOnAction(event -> checkAnswer());

            root.getChildren().addAll(answerInput, submitButton);
            Scene scene = new Scene(root, 300, 200);
            stage.setTitle("Maze Game");
            stage.setScene(scene);
            stage.show();
        }

        private void checkAnswer() {
            String userAnswer = answerInput.getText();
            int result = controller.CheckQuestion(userAnswer);
            if (result == 1) {
                // Correct answer
                controller.Increment_Correct();
                // // display user progress and proceed to the next question or level
            } else {
                // Incorrect answer
                controller.Increment_Incorrect();
                // Display the user failure and possibly repeat the question or provide hints
            }
        }

        public static void main(String[] args) {
            launch(args);
        }
    }


