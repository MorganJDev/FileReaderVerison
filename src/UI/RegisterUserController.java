package UI;

import Classes.FileWriter;
import Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDateTime;

import static UI.Main.closeApplication;

public class RegisterUserController
{
    public javafx.scene.control.TextField username;
    public javafx.scene.control.TextField firstName;
    public javafx.scene.control.TextField lastName;
    public javafx.scene.control.TextField telephoneNumber;
    public javafx.scene.control.TextField addressLineOne;
    public javafx.scene.control.TextField addressLineTwo;
    public javafx.scene.control.TextField townOrCity;
    public javafx.scene.control.TextField county;
    public javafx.scene.control.TextField postcode;

    public void handleOk() throws Exception
    {
        String username = this.username.getText();
        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String telephoneNumber = this.telephoneNumber.getText();
        String addressLineOne = this.addressLineOne.getText();
        String addressLineTwo = this.addressLineTwo.getText();
        String townOrCity = this.townOrCity.getText();
        String county = this.county.getText();
        String postcode = this.postcode.getText();

        User newUser = new User(firstName,lastName,username, telephoneNumber,addressLineOne,
                addressLineTwo,townOrCity,county,postcode, LocalDateTime.now());

        Main.admin.registerUser(newUser);

        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        window.setScene(new Scene(current));
        window.show();

        Main.window.close();
        Main.window = window;
        Main.admin.setCurrentUser(newUser);

        window.setOnCloseRequest(e -> closeApplication());
    }

    @FXML
    private void handleCancel() throws Exception
    {
        Stage window = new Stage();
        Parent current = FXMLLoader.load(getClass().getResource("Login.fxml"));

        window.setScene(new Scene(current));
        window.show();

        Main.window.close();
        Main.window = window;

        window.show();
        Main.window.setOnCloseRequest(e -> closeApplication());
    }
}
