package UI;

import Classes.Artwork;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

import static UI.Main.closeApplication;

public class MainMenuController
{
    @FXML
    private void handleLogout() throws Exception
    {
        Stage window = new Stage();
        Parent current = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Main.window.close();
        Main.window.setScene(new Scene(current));
        Main.window.show();
        Main.window.setOnCloseRequest(e -> closeApplication());
    }

    @FXML
    private void handleNewPainting() throws Exception
    {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("PaintingDialog.fxml"));

        window.setScene(new Scene(current));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        Main.popup = window;
    }

    @FXML
    private void handleAddPhotos() throws Exception
    {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("AddPhotosDialogue.fxml"));

        window.setScene(new Scene(current));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        Main.popup = window;
    }

    @FXML
    private void handleBid() throws Exception
    {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("BidDialog.fxml"));

        window.setScene(new Scene(current));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        Main.popup = window;
    }

    @FXML
    private void handleViewPhotos() throws Exception
    {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("PhotosDialogue.fxml"));

        window.setScene(new Scene(current));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        Main.popup = window;
    }

    @FXML
    private void handleNewAvatar() throws Exception
    {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("AvatarDialogue.fxml"));

        window.setScene(new Scene(current));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        Main.popup = window;
    }
}
