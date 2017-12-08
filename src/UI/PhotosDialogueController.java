package UI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This manages the GUI for viewing artwork photos
 *
 * @author Morgan Jones 904410
 * @version 1.0
 */
public class PhotosDialogueController implements Initializable
{
    // References GUI
    public javafx.scene.image.ImageView artPhoto;
    public javafx.scene.control.Button previousButton;
    public javafx.scene.control.Button nextButton;

    // References the photos being displayed
    private static int picturePosition;
    private ArrayList<String> allPhotos;

    /**
     * This closes the form
     */
    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     * It enables/disables the next button depending on number of photos
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // The auction that was selected on the main form
        if(Main.selectedAuction.getArtwork().getPhotos() != null)
        {
            allPhotos = Main.selectedAuction.getArtwork().getPhotos();
            picturePosition = 0;

            switch (allPhotos.size())
            {
                case 0:
                    break;
                case 1:
                    artPhoto.setImage(new Image(allPhotos.get(0)));
                    break;
                default:
                    artPhoto.setImage(new Image(allPhotos.get(0)));
                    nextButton.setDisable(false);
                    break;
            }
        }
    }

    /**
     * This handles the next button.
     * It enables the previous button, loads the next image and checks if it is the last image
     */
    @FXML
    public void handleNext()
    {
        previousButton.setDisable(false);

        picturePosition++;
        artPhoto.setImage(new Image(allPhotos.get(picturePosition)));

        if(allPhotos.size() == (picturePosition + 1))
        {
            nextButton.setDisable(true);
        }
    }

    /**
     * This handles the previous button.
     * It enables the next button and loads the previous image.
     * It also disables the previous button if the picture is the first one
     */
    @FXML
    public void handlePrevious()
    {
        nextButton.setDisable(false);

        picturePosition--;
        artPhoto.setImage(new Image(allPhotos.get(picturePosition)));

        if(picturePosition == 0)
        {
            previousButton.setDisable(true);
        }
    }
}

