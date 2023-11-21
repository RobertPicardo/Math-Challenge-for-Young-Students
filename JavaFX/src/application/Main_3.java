package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class Main_3 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Math Challenge");

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        // Set the button size
        loginButton.setPrefWidth(150);
        registerButton.setPrefWidth(150);

        loginButton.setOnAction(e -> {
        
            Dialog<String> loginDialog = new Dialog<>();
            loginDialog.setTitle("Login");
            loginDialog.setHeaderText("Enter your name and password:");

            
            ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
            loginDialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

            
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            
            TextField nameField = new TextField();
            nameField.setPromptText("Name");
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Password");

            grid.add(new Label("Name:"), 0, 0);
            grid.add(nameField, 1, 0);
            grid.add(new Label("Password:"), 0, 1);
            grid.add(passwordField, 1, 1);

            loginDialog.getDialogPane().setContent(grid);

            
            Platform.runLater(nameField::requestFocus);

            
            loginDialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return nameField.getText() + ":" + passwordField.getText();
                }
                return null;
            });

            loginDialog.showAndWait().ifPresent(result -> {
                String[] parts = result.split(":");
                if (parts.length == 2) {
                    String name = parts[0];
                    String password = parts[1];
                    
                    System.out.println("Logged in as: " + name);
                }
            });
        });

        registerButton.setOnAction(e -> {
            
            TextInputDialog nameDialog = new TextInputDialog();
            nameDialog.setTitle("Register User");
            nameDialog.setHeaderText("Enter your name:");
            nameDialog.setContentText("Name:");
            nameDialog.getEditor().setPromptText("Name");

            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Create a password");

            TextInputDialog passwordDialog = new TextInputDialog();
            passwordDialog.setTitle("Register User");
            passwordDialog.setHeaderText("Create a password:");
            passwordDialog.setContentText("Password:");

            TextInputDialog gradeDialog = new TextInputDialog();
            gradeDialog.setTitle("Register User");
            gradeDialog.setHeaderText("Enter your grade level:");
            gradeDialog.setContentText("Grade Level:");
            gradeDialog.getEditor().setPromptText("Grade Level");

            nameDialog.showAndWait().ifPresent(name -> {
                passwordDialog.showAndWait().ifPresent(password -> {
                    gradeDialog.showAndWait().ifPresent(grade -> {
                        
                        System.out.println("Registered user: " + name + " (Grade " + grade + ")");
                    });
                });
            });
        });

        Label title = new Label("Math Challenge");
        title.setStyle("-fx-font-size: 24px;");

        VBox buttonLayout = new VBox(title, loginButton, registerButton);
        buttonLayout.setSpacing(20);

        Scene scene = new Scene(buttonLayout, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}