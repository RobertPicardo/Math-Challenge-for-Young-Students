package com.example.mathchallenge.Maze;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class EachRoundEnterSolution extends Application {

    private Question_Generator questionGenerator = new Question_Generator();
    private Label questionLabel = new Label();
    private TextField answerInput = new TextField();
    private String currentSolution;

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(10);

        // Generate the first question & solution
        String[] questionAndSolution = questionGenerator.generateQuestionAndSolution();
        questionLabel.setText(questionAndSolution[0]); // Display the question
        currentSolution = questionAndSolution[1]; // Store the solution

        Button submitButton = new Button("Submit Answer");
        submitButton.setOnAction(event -> checkAnswer());

        root.getChildren().addAll(questionLabel, answerInput, submitButton);
        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("Maze Game");
        stage.setScene(scene);
        stage.show();
    }

    private void checkAnswer() {
        String userAnswer = answerInput.getText();
        if (userAnswer.equals(currentSolution)) {
            // Correct answer
            // Display user progress and proceed to the next question or level
        } else {
            // Incorrect answer
            // Display the user
        }

        // Prepare for the next question
        String[] nextQuestionAndSolution = questionGenerator.generateQuestionAndSolution();
        questionLabel.setText(nextQuestionAndSolution[0]);
        currentSolution = nextQuestionAndSolution[1];
        answerInput.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
