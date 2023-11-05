import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class GUI extends Application {

    private static int TIMER_SECONDS = 45;
    private int countdown = TIMER_SECONDS;
    private Label timerLabel;
    private Timeline countdownTimer;
    private int currentLevel = 1;
    
    int CorrectResponses = 0;
    
    //Level 1, 20 questions in 45
    //level 2, 20 questions in 40
    //level 3, 20 questions in 35
    //level 4, 20 Questions in 30
    //Level 3, 20 Questions in 25
    

    @Override
    public void start(Stage primaryStage) {

    	Game_Controller_V2 GameController = new Game_Controller_V2();
    	
        StackPane root = new StackPane();
    
        Label Level_label = new Label();
        updateLevelLabel(Level_label, currentLevel);

        
        TextField textField = new TextField();

        Button submitButton = new Button("Submit");

        HBox inputBox = new HBox(10);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.getChildren().addAll(textField, submitButton);

        Label correctLabel = new Label("Correct");
        correctLabel.setStyle("-fx-font-size: 36; -fx-text-fill: green;");
        StackPane.setAlignment(correctLabel, Pos.BOTTOM_CENTER);
        StackPane.setMargin(correctLabel, new Insets(0, 0, 150, 0));

        Label incorrectLabel = new Label("Incorrect");
        incorrectLabel.setStyle("-fx-font-size: 36; -fx-text-fill: red;");
        StackPane.setAlignment(incorrectLabel, Pos.BOTTOM_CENTER);
        StackPane.setMargin(incorrectLabel, new Insets(0, 0, 150, 0));
        
        correctLabel.setVisible(false);
        incorrectLabel.setVisible(false);

        timerLabel = new Label(Integer.toString(countdown));
        timerLabel.setStyle("-fx-font-size: 45;");
        StackPane.setAlignment(timerLabel, Pos.TOP_CENTER);
        StackPane.setMargin(timerLabel, new Insets(75, 0, 0, 0));
        
        Label QuestionLabel = new Label(GameController.Generate_Question());
        QuestionLabel.setStyle("-fx-font-size: 45;");
        StackPane.setAlignment(QuestionLabel, Pos.TOP_CENTER);
        StackPane.setMargin(QuestionLabel, new Insets(150, 0, 0, 0));

        StackPane.setAlignment(Level_label, Pos.TOP_CENTER);
        StackPane.setAlignment(inputBox, Pos.CENTER);
        
        Button restartButton = new Button("Restart");
        StackPane.setAlignment(restartButton, Pos.CENTER);
        StackPane.setMargin(restartButton, new Insets(200, 0, 0, 0));


        submitButton.setOnAction(e -> {
        	
            String userInput = textField.getText();
            textField.clear();
            if (GameController.CheckQuestion(userInput) == 1) 
	            {
	                correctLabel.setVisible(true);
	                incorrectLabel.setVisible(false);
	                QuestionLabel.setText(GameController.Generate_Question());
	                CorrectResponses++;
	                GameController.Increment_Correct();
	            } 
            else 
	            {
	                correctLabel.setVisible(false);
	                incorrectLabel.setVisible(true);
	                QuestionLabel.setText(GameController.Generate_Question());
	                GameController.Increment_Incorrect();
	            }
            
            GameController.Generate_Difficult_Level();
            
        });
        
        Label CorrectNumResponseLabel = new Label();
        Label levelDoneLabel = new Label("Level Done");

        countdownTimer = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            countdown--;
            timerLabel.setText(Integer.toString(countdown));
            if (countdown == 0) {
                countdownTimer.stop();
                root.getChildren().removeAll(Level_label, inputBox, correctLabel, incorrectLabel, QuestionLabel);
                
                levelDoneLabel.setStyle("-fx-font-size: 45;");
                
                
                CorrectNumResponseLabel.setText("Correct Responses: " + CorrectResponses);
                StackPane.setAlignment(CorrectNumResponseLabel, Pos.CENTER);
                CorrectNumResponseLabel.setStyle("-fx-font-size: 45;");
                StackPane.setMargin(CorrectNumResponseLabel, new Insets(75, 0, 0, 0));
                
                 root.getChildren().addAll(levelDoneLabel, CorrectNumResponseLabel, restartButton);

                
                if (CorrectResponses >= 20) 
	                {
	
	                    currentLevel++;
	                    TIMER_SECONDS -= 5; 
	                    restartButton.setText("Next Level");
	                    
	                    if (currentLevel > 5) 
		                    {
		                        showGameCompletedMessage(root);
		                    } 
	                }
                   
            }
        }));
        countdownTimer.setCycleCount(TIMER_SECONDS);
        
        restartButton.setOnAction(e -> {
        	
        	root.getChildren().addAll(Level_label, inputBox, correctLabel, incorrectLabel, QuestionLabel);
        	root.getChildren().removeAll(levelDoneLabel, CorrectNumResponseLabel, restartButton);
        	
        	restartButton.setText("Restart");
        	
            restartGame();
            updateLevelLabel(Level_label, currentLevel);
        });

        root.getChildren().addAll(Level_label, inputBox, correctLabel, incorrectLabel, timerLabel, QuestionLabel);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);

        countdownTimer.play();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private void showGameCompletedMessage(StackPane root) {
        root.getChildren().clear();
        Label gameCompletedLabel = new Label("All Levels Completed!");
        gameCompletedLabel.setStyle("-fx-font-size: 45;");
        StackPane.setAlignment(gameCompletedLabel, Pos.CENTER);
        StackPane.setMargin(gameCompletedLabel, new Insets(0, 0, 0, 0));
        root.getChildren().add(gameCompletedLabel);
    }
    
    private void restartGame() {

        countdown = TIMER_SECONDS;
        CorrectResponses = 0;
        countdownTimer.setCycleCount(TIMER_SECONDS);
        countdownTimer.play();

    }
    
    private void updateLevelLabel(Label label, int level) {
        label.setText("Level " + level);
        label.setStyle("-fx-font-size: 45;");
    }
    
}





