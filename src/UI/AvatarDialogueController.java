package UI;

import Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Morgan Jones 904410
 */

public class AvatarDialogueController implements Initializable
{
    public javafx.scene.image.ImageView artPhoto1;
    public javafx.scene.image.ImageView artPhoto2;
    public javafx.scene.image.ImageView artPhoto3;
    public javafx.scene.image.ImageView artPhoto4;
    public javafx.scene.image.ImageView artPhoto5;
    public javafx.scene.image.ImageView artPhoto6;

    private String selected;

    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }

    @FXML
    private void handleConfirm() throws Exception
    {
        findUser().setProfileImage(selected);

        Stage window = new Stage();
        Main.isReloaded = true;

        Parent current = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        Main.window.close();
        Main.window.setScene(new Scene(current));
        Main.window.show();

        Main.window.setOnCloseRequest(e -> Main.closeApplication());

        Main.popup.close();
    }

    private User findUser()
    {
        for (User i : Main.admin.getAllUsers())
        {
            if (i.getUsername().equals(Main.admin.getCurrentUser().getUsername()))
            {
                return i;
            }
        }

        return null;
    }

    @FXML
    private void handleArt1()
    {
        selected = "/ProfileImages/16627-200.png";
        artPhoto1.setOpacity(1.0);
        artPhoto2.setOpacity(0.5);
        artPhoto3.setOpacity(0.5);
        artPhoto4.setOpacity(0.5);
        artPhoto5.setOpacity(0.5);
        artPhoto6.setOpacity(0.5);
    }

    @FXML
    private void handleArt2()
    {
        selected = "/ProfileImages/157815-200.png";
        artPhoto1.setOpacity(0.5);
        artPhoto2.setOpacity(1.0);
        artPhoto3.setOpacity(0.5);
        artPhoto4.setOpacity(0.5);
        artPhoto5.setOpacity(0.5);
        artPhoto6.setOpacity(0.5);
    }

    @FXML
    private void handleArt3()
    {
        selected = "/ProfileImages/br-logo-trans-blue-trans.png";
        artPhoto1.setOpacity(0.5);
        artPhoto2.setOpacity(0.5);
        artPhoto3.setOpacity(1.0);
        artPhoto4.setOpacity(0.5);
        artPhoto5.setOpacity(0.5);
        artPhoto6.setOpacity(0.5);
    }

    @FXML
    private void handleArt4()
    {
        selected = "/ProfileImages/Gengar_Face.png";
        artPhoto1.setOpacity(0.5);
        artPhoto2.setOpacity(0.5);
        artPhoto3.setOpacity(0.5);
        artPhoto4.setOpacity(1.0);
        artPhoto5.setOpacity(0.5);
        artPhoto6.setOpacity(0.5);
    }

    @FXML
    private void handleArt5()
    {
        selected = "/ProfileImages/profile-2398782_960_720.png";
        artPhoto1.setOpacity(0.5);
        artPhoto2.setOpacity(0.5);
        artPhoto3.setOpacity(0.5);
        artPhoto4.setOpacity(0.5);
        artPhoto5.setOpacity(1.0);
        artPhoto6.setOpacity(0.5);
    }

    @FXML
    private void handleArt6()
    {
        selected = "/ProfileImages/twitter.png";
        artPhoto1.setOpacity(0.5);
        artPhoto2.setOpacity(0.5);
        artPhoto3.setOpacity(0.5);
        artPhoto4.setOpacity(0.5);
        artPhoto5.setOpacity(0.5);
        artPhoto6.setOpacity(1.0);
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        selected = findUser().getProfileImage();

        artPhoto1.setImage(new Image("/ProfileImages/16627-200.png"));
        artPhoto2.setImage(new Image("/ProfileImages/157815-200.png"));
        artPhoto3.setImage(new Image("/ProfileImages/br-logo-trans-blue-trans.png"));
        artPhoto4.setImage(new Image("/ProfileImages/Gengar_Face.png"));
        artPhoto5.setImage(new Image("/ProfileImages/profile-2398782_960_720.png"));
        artPhoto6.setImage(new Image("/ProfileImages/twitter.png"));

        switch(selected)
        {
            case "/ProfileImages/16627-200.png":
                handleArt1();
                break;
            case "/ProfileImages/157815-200.png":
                handleArt2();
                break;
            case "/ProfileImages/br-logo-trans-blue-trans.png":
                handleArt3();
                break;
            case "/ProfileImages/Gengar_Face.png":
                handleArt4();
                break;
            case "/ProfileImages/profile-2398782_960_720.png":
                handleArt5();
                break;
            case "/ProfileImages/twitter.png":
                handleArt6();
                break;
            default:
                artPhoto1.setOpacity(1.0);
                artPhoto2.setOpacity(1.0);
                artPhoto3.setOpacity(1.0);
                artPhoto4.setOpacity(1.0);
                artPhoto5.setOpacity(1.0);
                artPhoto6.setOpacity(1.0);
                break;
        }
    }
}
