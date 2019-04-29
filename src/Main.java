import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        TextField username = new TextField();
        TextField password = new TextField();
        HBox buttonLayout = new HBox();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(username, password, buttonLayout);

        Button loginButton = new Button("Login");
        loginButton.setId("login-button");
        Button registerButton = new Button("Register");
        buttonLayout.getChildren().addAll(loginButton, registerButton);

        Scene firstScene = new Scene(vBox, 640, 480);
        firstScene.getStylesheets().add("Styles.css");

        Text secondLabel = new Text("You are logged in!");
        Button logoutButton = new Button("Log out");
        HBox hBox = new HBox();
        hBox.getChildren().addAll(secondLabel, logoutButton);
        Scene otherScene = new Scene(hBox, 320, 240);

        loginButton.setOnAction(event -> {
            window.setScene(otherScene);
        });

        logoutButton.setOnAction(event -> {
            window.setScene(firstScene);
        });

        window.setScene(firstScene);
        primaryStage.show();
    }
}
