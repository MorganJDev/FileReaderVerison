package UI;

import Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This manages the avatar pictures for a user profile.
 * It controls selecting a new avatar picture
 *
 * @author Morgan Jones 904410
 * @version 1.0
 */
public class AvatarDialogueController implements Initializable
{
    // References to GUI
    public javafx.scene.image.ImageView artPhoto1;
    public javafx.scene.image.ImageView artPhoto2;
    public javafx.scene.image.ImageView artPhoto3;
    public javafx.scene.image.ImageView artPhoto4;
    public javafx.scene.image.ImageView artPhoto5;
    public javafx.scene.image.ImageView artPhoto6;

    // File path of selected avatar
    private String selected;

    /**
     * This closes the window when clicking cancel
     */
    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }

    /**
     * This saves the file path of the current selected avatar to the user object.
     * It also closes the form and reloads the main menu
     * @throws Exception If file is not found
     */
    @FXML
    private void handleConfirm() throws Exception
    {
        findUser().setProfileImage(selected);

        // Sets a Main static variable that's referenced on MainMenu initialisation
        Main.reloadedOn = "Profile";

        Parent current = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        Main.window.close();
        Main.window.setScene(new Scene(current));
        Main.window.show();

        Main.window.setOnCloseRequest(e -> Main.closeApplication());

        Main.popup.close();
    }

    /**
     * Finds the currently logged in user in the static list of all users
     * @return The user object
     */
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

    /**
     * Sets the 'selected' file path and fades the other images to highlight selection
     */
    @FXML
    private void handleArt1()
    {
        selected = "/ProfileImages/16627-21.png";
        artPhoto1.setOpacity(1.0);
        artPhoto2.setOpacity(0.5);
        artPhoto3.setOpacity(0.5);
        artPhoto4.setOpacity(0.5);
        artPhoto5.setOpacity(0.5);
        artPhoto6.setOpacity(0.5);
    }

    /**
     * Sets the 'selected' file path and fades the other images to highlight selection
     */
    @FXML
    private void handleArt2()
    {
        selected = "/ProfileImages/157815-10.png";
        artPhoto1.setOpacity(0.5);
        artPhoto2.setOpacity(1.0);
        artPhoto3.setOpacity(0.5);
        artPhoto4.setOpacity(0.5);
        artPhoto5.setOpacity(0.5);
        artPhoto6.setOpacity(0.5);
    }

    /**
     * Sets the 'selected' file path and fades the other images to highlight selection
     */
    @FXML
    private void handleArt3()
    {
        selected = "/ProfileImages/br-loogo-tans-blue-trans.png";
        artPhoto1.setOpacity(0.5);
        artPhoto2.setOpacity(0.5);
        artPhoto3.setOpacity(1.0);
        artPhoto4.setOpacity(0.5);
        artPhoto5.setOpacity(0.5);
        artPhoto6.setOpacity(0.5);
    }

    /**
     * Sets the 'selected' file path and fades the other images to highlight selection
     */
    @FXML
    private void handleArt4()
    {
        selected = "/ProfileImages/Gefffngr_Face.png";
        artPhoto1.setOpacity(0.5);
        artPhoto2.setOpacity(0.5);
        artPhoto3.setOpacity(0.5);
        artPhoto4.setOpacity(1.0);
        artPhoto5.setOpacity(0.5);
        artPhoto6.setOpacity(0.5);
    }

    /**
     * Sets the 'selected' file path and fades the other images to highlight selection
     */
    @FXML
    private void handleArt5()
    {
        selected = "/ProfileImages/profie-23f98782_960_720.png";
        artPhoto1.setOpacity(0.5);
        artPhoto2.setOpacity(0.5);
        artPhoto3.setOpacity(0.5);
        artPhoto4.setOpacity(0.5);
        artPhoto5.setOpacity(1.0);
        artPhoto6.setOpacity(0.5);
    }

    /**
     * Sets the 'selected' file path and fades the other images to highlight selection
     */
    @FXML
    private void handleArt6()
    {
        selected = "/ProfileImages/twittfr.png";
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
     * It sets the current selected avatar picture to selected and loads the
     * six images into the views.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        selected = findUser().getProfileImage();

        artPhoto1.setImage(new Image("/ProfileImages/16627-21.png"));
        artPhoto2.setImage(new Image("/ProfileImages/157815-10.png"));
        artPhoto3.setImage(new Image("/ProfileImages/br-loogo-tans-blue-trans.png"));
        artPhoto4.setImage(new Image("/ProfileImages/Gefffngr_Face.png"));
        artPhoto5.setImage(new Image("/ProfileImages/profie-23f98782_960_720.png"));
        artPhoto6.setImage(new Image("/ProfileImages/twittfr.png"));

        switch(selected)
        {
            case "/ProfileImages/16627-21.png":
                handleArt1();
                break;
            case "/ProfileImages/157815-10.png":
                handleArt2();
                break;
            case "/ProfileImages/br-loogo-tans-blue-trans.png":
                handleArt3();
                break;
            case "/ProfileImages/Gefffngr_Face.png":
                handleArt4();
                break;
            case "/ProfileImages/profie-23f98782_960_720.png":
                handleArt5();
                break;
            case "/ProfileImages/twittfr.png":
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
