package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			Image img = new Image ("file:C:/Users/Robert/eclipse-workspace/JavaFX/Maze1.jpg");
			System.out.println("Image Width: " + img.getWidth());
			System.out.println("Image Height: " + img.getHeight());
			ImageView imv = new ImageView(img);
			
			Image img2 = new Image ("file:C:/Users/Robert/eclipse-workspace/JavaFX/MazePerson.jpg");
			System.out.println("Image Width: " + img2.getWidth());
			System.out.println("Image Height: " + img2.getHeight());
	        ImageView imageView2 = new ImageView(img2);
			
			Scene scene = new Scene(root,1500.0,1500.0);
			primaryStage.setTitle("The Maze");
			primaryStage.setScene(scene);
			primaryStage.show();
			//System.out.print("hello world\ntest");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		//System.out.print("hello world\ntest 2");
	}
}
