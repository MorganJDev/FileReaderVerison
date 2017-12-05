package UI;

import Classes.Artwork;
import Classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static UI.Main.closeApplication;

/**
 * @author Morgan Jones 904410
 */

public class MainMenuController
{
    public javafx.scene.control.TableView myArtworkTable;
    public javafx.scene.control.TableColumn myReserveColumn;
    public javafx.scene.control.TableColumn myListingStatus;
    public javafx.scene.control.TableColumn myRemainingBids;
    public javafx.scene.control.TableColumn myTitleColumn;

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
    
    @FXML
    private void handleFavourite() {
       Main.admin.getCurrentUser().addFavouriteUser(selectedAuctionListing.getSeller());
       Alert alert = new Alert(AlertType.INFORMATION);
       alert.initOwner(Main.window);
       alert.setTitle("Artatawe : User favourited");
       alert.setHeaderText(selectedAuctionListing.getSeller() + " has been added to your favourite sellers!");

       alert.showAndWait();
    }
}
