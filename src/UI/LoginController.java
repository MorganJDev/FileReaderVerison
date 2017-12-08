package UI;
import Classes.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
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

/**
 * @author Morgan Jones 904410
 */

public class LoginController
{
    public javafx.scene.control.TextField usernameBox;

    public void handleRegister() throws Exception
    {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("RegisterUser.fxml"));

        window.setScene(new Scene(current));
        window.show();

        Main.window.close();
        Main.window = window;
    }

    @FXML
    public void handleLogin() throws Exception
    {
        if(foundUser())
        {
            //Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
            Stage window = new Stage();

            Parent current = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

            Main.window.close();
            Main.window.setScene(new Scene(current));
            Main.window.show();

            Main.window.setOnCloseRequest(e -> Main.closeApplication());
        }
    }
    
    @FXML
    public void handleAdminLogin() throws Exception
    {
        //Stage window = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));

        window.setScene(new Scene(current));
        window.show();

        Main.window.close();
        Main.window = window;
    }

    public Boolean foundUser()
    {
        Boolean found = false;
        String input = usernameBox.getText();

        for(int i = 0; i <= (Main.admin.getAllUsers().size() - 1); i++)
        {
            if (Main.admin.getAllUsers().get(i).getUsername().equals(input))
            {
                Main.admin.setCurrentUser(Main.admin.getAllUsers().get(i));
                found = true;
            }
        }

        return found;
    }
}
