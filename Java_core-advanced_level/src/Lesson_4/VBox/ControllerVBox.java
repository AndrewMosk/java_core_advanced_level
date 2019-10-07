package Lesson_4.VBox;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerVBox implements Initializable {
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

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private final String IP_ADDRESS = "localhost";
    private final int PORT = 8189;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket(IP_ADDRESS,PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            Task<Void> task = new Task<Void>() {
                @Override protected Void call() {
                    try {
                        while (true){
                            String msg = in.readUTF();
                            Platform.runLater(() -> addText(msg));
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }
            };
            Thread thread =new Thread(task);
            thread.setDaemon(true);
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addText(String msg){
        vBox.setFillWidth(false);
        vBox.setSpacing(10);
        vBox.getChildren().add(new TextMessage(msg));
    }

    public void infoAbout() throws IOException {
        Image imageInfo = new Image("file:images/info.jpg");
        Stage infoWindow = new Stage();

        Parent rootInfo = FXMLLoader.load(getClass().getResource("sample_info.fxml"));
        infoWindow.setTitle("Info");
        infoWindow.getIcons().add(imageInfo);
        infoWindow.setScene(new Scene(rootInfo, 350, 100));
        infoWindow.setResizable(false);
        infoWindow.initOwner(MainVBox.getPrimaryStage());
        infoWindow.initModality(Modality.WINDOW_MODAL);
        infoWindow.show();
    }

    public void clearWindow(){
        vBox.getChildren().clear();
    }

    public void closeWindow(){
        Stage stage = MainVBox.getPrimaryStage();
        stage.close();
    }

    public void sendMsg(){
        if (!textField.getText().isEmpty()) {
            try {
                vBox.setFillWidth(false);
                vBox.setSpacing(10);
                out.writeUTF(textField.getText());
                textField.clear();
                textField.requestFocus();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
