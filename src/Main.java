import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {

    private TextField username;
    private TextField password;
    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        username = new TextField();
        username.setPromptText("Username");
        password = new PasswordField();
        password.setPromptText("Password");

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
            username,
            password,
            loginButton,
            registerButton
        );

        loginButton.setOnAction(event -> {
            login();
        });

        registerButton.setOnAction(event -> {
            register();
        });

        Scene scene = new Scene(vBox, 640, 480);
        window.setScene(scene);
        primaryStage.show();
    }

    private void login() {
        String usernameString = username.getText();
        String passwordString = password.getText();

        try {
            String successfulUsername = Authenticator.login(usernameString, passwordString);
            if (successfulUsername == null) {
                System.out.println("Login failed!");
            } else {
                window.close();
                System.out.println("Welcome back, " + successfulUsername);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void register() {
        String usernameString = username.getText();
        String passwordString = password.getText();

        try {
            String registeredUser = Authenticator.registerUser(usernameString, passwordString);
            System.out.println("Welcome, " + registeredUser);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println("User exists!");
        }
    }
}
