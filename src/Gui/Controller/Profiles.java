package Gui.Controller;

import Gui.SceneChanger;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Profiles implements Initializable {
@FXML private ComboBox<String> usernameCombobox;
@FXML private JFXTextField usernameTextField;
@FXML private JFXButton logIn;
@FXML private JFXButton signUp;
    Controller controller = Controller.getInstance();
    private ArrayList<String> names = new ArrayList<>();
    private ObservableList<String> usernames = FXCollections.observableArrayList();


    public void initialize(URL location, ResourceBundle resources) {
        try {
            controller.loadPlayers();
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
        for (int i=0;i<controller.usersDB.getPlayers().size();i++){
            names.add(controller.usersDB.getPlayers().get(i).getUsername());
        }
        usernames.addAll(names);
        usernameCombobox.setItems(usernames);

        logIn.setOnMouseClicked(event -> {
            if (usernameCombobox.getValue() != null) {
                controller.setUser(usernameCombobox.getValue());//brings the chosen name
                //goes to main screeen
                controller.playSound("press", 0);
                SceneChanger sceneChanger=new SceneChanger();
                try {
                    sceneChanger.getMainMenu(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //TODO show error message
            }
        });
        
        signUp.setOnMouseClicked(event -> {
            if (controller.usersDB.verify(usernameTextField.getText())) {
                controller.usersDB.addUser(usernameTextField.getText(), 0, 0);
                controller.usersDB.setPlayer(usernameTextField.getText());
                // goes to main menu
                controller.playSound("press", 0);
                try {
                    controller.savePlayers();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                SceneChanger sceneChanger=new SceneChanger();
                try {
                    sceneChanger.getMainMenu(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //TODO show error message user already exists!
            }
        });
    }
}
