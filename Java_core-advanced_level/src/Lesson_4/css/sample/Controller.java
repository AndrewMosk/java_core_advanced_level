//package Lesson_4;
package Lesson_4.css.sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {
    private int maxWidth = 380;
    private int defaultWidth = 40;
    private double fontSize = 18;

    @FXML
    TextField textField;
    @FXML
    MenuItem close;
    @FXML
    MenuItem clear;
    @FXML
    MenuItem about;
    @FXML
    VBox vBox;

    public void infoAbout() throws IOException {
        Image imageInfo = new Image("file:images/info.jpg");
        Stage infoWindow = new Stage();

        Parent rootInfo = FXMLLoader.load(getClass().getResource("sample_info.fxml"));
        infoWindow.setTitle("Info");
        infoWindow.getIcons().add(imageInfo);
        infoWindow.setScene(new Scene(rootInfo, 350, 100));
        infoWindow.initModality(Modality.WINDOW_MODAL); //почему-то не сработало
        infoWindow.show();
    }

    public void clearWindow(){
        vBox.getChildren().clear();
    }

    public void closeWindow(){
        Platform.exit();
    }

    public void sendMsg(){

        if (!textField.getText().isEmpty()) {
            vBox.setFillWidth(false);
            vBox.setSpacing(10);
            vBox.getChildren().add(new Lesson_4.css.sample.TextMessage(textField.getText()));

            textField.clear();
            textField.requestFocus();
        }
    }
}
