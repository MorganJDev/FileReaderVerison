package UI;
import Classes.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginController
{
    public void handleRegister()
    {

    }

    @FXML
    public void handleLogin() throws Exception
    {
        //Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        window.setScene(new Scene(current));
        window.show();

        Main.window.close();
        Main.window = window;
    }
}
