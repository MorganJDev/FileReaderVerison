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
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static UI.Main.closeApplication;

/**
 * @author Morgan Jones 904410
 */

public class MainMenuController implements Initializable
{
    // My Artwork Tab
    public javafx.scene.control.TableView myArtworkTable;
    public javafx.scene.control.TableColumn myReserveColumn;
    public javafx.scene.control.TableColumn myListingStatus;
    public javafx.scene.control.TableColumn myRemainingBids;
    public javafx.scene.control.TableColumn myTitleColumn;

    // Profile tab
    public javafx.scene.control.TextField usernameField;
    public javafx.scene.control.TextField firstNameField;
    public javafx.scene.control.TextField lastNameField;
    public javafx.scene.control.TextField telNoField;
    public javafx.scene.control.TextField addOneField;
    public javafx.scene.control.TextField addTwoField;
    public javafx.scene.control.TextField cityField;
    public javafx.scene.control.TextField countyField;
    public javafx.scene.control.TextField postCodeField;
    public javafx.scene.control.Button editDetailsButton;
    public javafx.scene.image.ImageView profileImageView;

    public javafx.scene.control.Tab profileTab;
    public javafx.scene.control.TabPane tabMenu;

    @FXML
    private void showProfile()
    {
        User newUser = Main.admin.getCurrentUser();
        usernameField.setText(newUser.getUsername());
        firstNameField.setText(newUser.getForename());
        lastNameField.setText(newUser.getSurname());
        telNoField.setText(newUser.getTelephoneNumber());
        addOneField.setText(newUser.getAddressLineOne());
        addTwoField.setText(newUser.getAddressLineTwo());
        cityField.setText(newUser.getCity());
        countyField.setText(newUser.getCounty());
        postCodeField.setText(newUser.getPostcode());

        profileImageView.setImage(new Image(Main.admin.getCurrentUser().getProfileImage()));
    }

    @FXML
    private void handleEditProfile()
    {
        if (editDetailsButton.getText().equals("Edit details"))
        {
            editDetailsButton.setText("Save details");
            usernameField.setEditable(true);
            firstNameField.setEditable(true);
            lastNameField.setEditable(true);
            telNoField.setEditable(true);
            addOneField.setEditable(true);
            addTwoField.setEditable(true);
            cityField.setEditable(true);
            countyField.setEditable(true);
            postCodeField.setEditable(true);
        }
        else
        {
            editDetailsButton.setText("Edit details");
            usernameField.setEditable(false);
            firstNameField.setEditable(false);
            lastNameField.setEditable(false);
            telNoField.setEditable(false);
            addOneField.setEditable(false);
            addTwoField.setEditable(false);
            cityField.setEditable(false);
            countyField.setEditable(false);
            postCodeField.setEditable(false);

            saveUser();
        }
    }

    private void saveUser()
    {
        for (User i : Main.admin.getAllUsers())
        {
            if (i.getUsername().equals(Main.admin.getCurrentUser().getUsername()))
            {
                i.setUsername(usernameField.getText());
                i.setForename(firstNameField.getText());
                i.setSurname(lastNameField.getText());
                i.setTelephoneNumber(telNoField.getText());
                i.setAddressLineOne(addOneField.getText());
                i.setAddressLineTwo(addTwoField.getText());
                i.setCity(cityField.getText());
                i.setCounty(countyField.getText());
                i.setPostcode(postCodeField.getText());

                Main.admin.setCurrentUser(i);
            }
        }
    }

    @FXML
    private void handleNewSculpture() throws Exception
    {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("SculptureDialog.fxml"));

        window.setScene(new Scene(current));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        Main.popup = window;
    }

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
    private void handleNewCustom() throws Exception
    {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("Canvas.fxml"));

        window.setScene(new Scene(current));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        Main.popup = window;
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        showProfile();

        if (Main.isReloaded)
        {
            tabMenu.getSelectionModel().select(profileTab);
            Main.isReloaded = false;
        }
    }
}
