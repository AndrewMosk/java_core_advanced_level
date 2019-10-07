package Lesson_4.TextArea;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainFX_TextArea extends Application {
    private static Stage primaryStage;

    private void setPrimaryStage(Stage stage) {
        MainFX_TextArea.primaryStage = stage;
    }
    static Stage getPrimaryStage() {
        return MainFX_TextArea.primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        setPrimaryStage(primaryStage);
        Image image = new Image("file:images/icon.jpg");
        primaryStage.getIcons().add(image);

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Chat");
        primaryStage.setScene(new Scene(root, 300, 500));

        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
