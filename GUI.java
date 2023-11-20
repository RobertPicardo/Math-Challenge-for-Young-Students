
import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUI extends Application {
    
		public int BlockStartX = 0;
    	public int BlockStartY = 0;
    	public int BlockEndX = 0;
    	public int BlockEndY = 0;
    	
    	public int BlockStart2X = 0;
    	public int BlockStart2Y = 0;
    	public int BlockEnd2X = 0;
    	public int BlockEnd2Y = 0;
    	
    	public int SelectBotWin = 0;
    	
    	public int CorrectResponses = 0;
    	public int BotCorrects = 0;
    	
    	public TranslateTransition translateTransition;
    	public TranslateTransition translateTransition2;
    
    	@Override
    
    public void start(Stage primaryStage) {
        
    	Randomize();
    	
    	Pane root = new Pane();
    	Game_Controller_RG GameController = new Game_Controller_RG();


        
        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        
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
        
        button.setText("Submit" + BotCorrects);
        
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


        primaryStage.show();
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

	public static void main(String[] args) {
        launch(args);
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

