import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.util.Random;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public class Main extends Application {

    private Stage gameSelectionStage;
    
    private static int TIMER_SECONDS = 45;
    private int countdown = TIMER_SECONDS;
    private Label timerLabel;
    private Timeline countdownTimer;
    private int currentLevel = 1;
    int CorrectResponses = 0;
    
	public int BlockStartX = 0;
	public int BlockStartY = 0;
	public int BlockEndX = 0;
	public int BlockEndY = 0;
	
	public int BlockStart2X = 0;
	public int BlockStart2Y = 0;
	public int BlockEnd2X = 0;
	public int BlockEnd2Y = 0;
	
	public int SelectBotWin = 0;
	
	public int BotCorrects = 0;
	
	public TranslateTransition translateTransition;
	public TranslateTransition translateTransition2;
    


    public static void main(String[] args) {

        launch(args);
    }
    

    @Override
    public void start(Stage primaryStage) {

    	StackPane root = new StackPane();
    	Pane root2 = new Pane();
    	
        gameSelectionStage = new Stage();
        gameSelectionStage.setTitle("Game Selection");
        
        VBox gameLayout = new VBox();
        gameLayout.setAlignment(Pos.CENTER);
        gameLayout.setSpacing(20);
        
        Label welcomeLabel = new Label("Welcome, Sam!");
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
    	
        Button mazeButton = createStyledButton("Maze Game");
        Button raceButton = createStyledButton("Race Game");
        Button recallButton = createStyledButton("Quick Recall");
        Button viewReportButton = createStyledButton("View Report");
        Button exitButton = createStyledButton("Exit");

        gameLayout.getChildren().addAll(welcomeLabel, mazeButton, raceButton, recallButton, viewReportButton,exitButton);

        mazeButton.setOnAction(e -> System.out.println("Starting Maze Game"));
        raceButton.setOnAction(e -> {
        	
        	openNewWindow2(root2);
        	
            Scene scene2 = new Scene(root2, 800, 400);
            primaryStage.setScene(scene2);
            primaryStage.show();
            
        	
        });
        
        
        recallButton.setOnAction(e ->
        {
        	openNewWindow(root); 
        	
        	Scene scene = new Scene(root, 500, 500);
	        primaryStage.setScene(scene);
	        primaryStage.show();
        
        });
        
        Scene gameScene = new Scene(gameLayout, 400, 300);
        gameSelectionStage.setScene(gameScene);
        gameSelectionStage.show();
        
    
    }


    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(200);
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #45a049; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;"));
        return button;
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
    
    private void openNewWindow(StackPane root) 
    {
    	Game_Controller_V2 GameController = new Game_Controller_V2();
    	
            
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



        countdownTimer.play();

        
    }
    
    private void openNewWindow2(Pane root) 
    {
    	CorrectResponses = 0;
    	
    	Randomize();
    	
    	
    	Game_Controller_V2 GameController = new Game_Controller_V2();

        
        Button button = new Button("Submit");
        button.setLayoutX(560);  button.setLayoutY(350);
        
        Label Info = new Label("You are Blue. Enter the Correct Solution to \nMove Ahead in the Race and Cross the Finish Line");
        Info.setStyle("-fx-font-size: 20;");
        Info.setLayoutX(0); Info.setLayoutY(0);
        
        
        Label QuestionLabel = new Label(GameController.Generate_Question());
        QuestionLabel.setStyle("-fx-font-size: 45;");
        QuestionLabel.setLayoutX(0); QuestionLabel.setLayoutY(325);
        
        TextField textField = new TextField();
        textField.setLayoutX(400); textField.setLayoutY(350);
        
        
        Line finishLine = new Line(700, 50, 700, 350);
        finishLine.setStroke(Color.BLACK);
        finishLine.setStrokeWidth(5);
        
        Line horizontalLine = new Line(0, 200, 700, 200);
        horizontalLine.setStrokeWidth(3);
        

        Text label = new Text(650,30, "Finish Line");
        label.setFill(Color.BLACK);
        Font comicSansBold = Font.font("Comic Sans MS", FontWeight.BOLD, 18);
        label.setFont(comicSansBold);

        
        Button restartButton = new Button("Restart");
        restartButton.setLayoutX(240); restartButton.setLayoutY(160);
        
        Label gameCompletedLabel = new Label("Error!");
        gameCompletedLabel.setStyle("-fx-font-size: 45;");
        gameCompletedLabel.setLayoutX(200); gameCompletedLabel.setLayoutY(100);
        
        Rectangle square = new Rectangle(50, 50, Color.BLUE);
        square.setLayoutX(0); square.setLayoutY(100);
        
        Rectangle square2 = new Rectangle(50, 50, Color.CRIMSON);
        square2.setLayoutX(0); square2.setLayoutY(250);

        translateTransition = new TranslateTransition(Duration.millis(500), square);
        translateTransition2 = new TranslateTransition(Duration.millis(500), square2);
        

        
        translateTransition.setOnFinished(event -> {
            translateTransition.stop();
        });
        
        
        restartButton.setOnAction(e -> {
        restartvalues();
        root.getChildren().removeAll(gameCompletedLabel, restartButton);

        setanimation();
        translateTransition.play();
        translateTransition2.play();
        
        
        		
        });

        button.setOnAction(e -> {
        
        setanimation();	
        	
        if((BotCorrects >= 14) || (CorrectResponses >= 14))
        {
        	root.getChildren().addAll(gameCompletedLabel, restartButton);
        }
    else
        {
        	
        
        String userInput = textField.getText();
        textField.clear();
        if (GameController.CheckQuestion(userInput) == 1) 
            {

                QuestionLabel.setText(GameController.Generate_Question());
				CorrectResponses++;
                GameController.Increment_Correct();
                IncrementLocation(1);
                setanimation();
                translateTransition.play();

            } 
        else 
            {

                QuestionLabel.setText(GameController.Generate_Question());
                GameController.Increment_Incorrect();
            }
        

        
        if(SelectBotWin < 3)
        	{	
	        	IncrementLocation(0);
	        	setanimation();
	        	translateTransition2.play();
	        	BotCorrects++;
	        }
        else
	        {
	        	
	        }
        Randomize();
        
        GameController.Generate_Difficult_Level();
        
        
        }
        
        if((BotCorrects >= 14) || (CorrectResponses >= 14))
        {
        	root.getChildren().addAll(gameCompletedLabel, restartButton);
        	
        	if((BotCorrects >= 14) && (CorrectResponses >= 14))
		        {
        			gameCompletedLabel.setText("It's a Tie!");
		        }
        	else if((BotCorrects >= 14) && (CorrectResponses < 14))
	        	{
        			gameCompletedLabel.setText("Bot Wins");
	        	}
        	else if((BotCorrects < 14) && (CorrectResponses >= 14))
	        	{
        			gameCompletedLabel.setText("You Win");
	        	}
        	else
        	{}
        	
        }
        
        });
 
        
        root.getChildren().addAll(finishLine, label, Info, horizontalLine, button, QuestionLabel, textField, square, square2);
    	
    }
    
    private void setanimation() 
	{
        translateTransition.setFromX(BlockStartX);
        translateTransition.setFromY(BlockStartY);
        translateTransition.setToX(BlockEndX);
        translateTransition.setToY(BlockEndY);
        
        translateTransition2.setFromX(BlockStart2X);
        translateTransition2.setFromY(BlockStart2Y);
        translateTransition2.setToX(BlockEnd2X);
        translateTransition2.setToY(BlockEnd2Y);
		
	}

private void restartvalues() 
	{
	BlockStartX = 0;
	BlockStartY = 0;
	BlockEndX = 0;
	BlockEndY = 0;
	
	BlockStart2X = 0;
	BlockStart2Y = 0;
	BlockEnd2X = 0;
	BlockEnd2Y = 0;
	
	SelectBotWin = 0;
	
	CorrectResponses = 0;
	BotCorrects = 0;

	}


void IncrementLocation(int Case)
{
	if(Case == 1)
	{
		BlockStartX = BlockEndX;
		BlockEndX = BlockEndX + 50;
	
	}
	if(Case == 0)
	{
		BlockStart2X = BlockEnd2X;
		BlockEnd2X = BlockEnd2X + 50;
	}
}


void Randomize()
{
Random random = new Random();
SelectBotWin = random.nextInt(4);
}

    	
    	
    	
    }	
    