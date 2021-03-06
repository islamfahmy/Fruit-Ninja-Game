package Gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FxmlFiles/Profiles.fxml"));
        primaryStage.setTitle("Fruits Ninja");
        primaryStage.setScene(new Scene(root, 1270, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}