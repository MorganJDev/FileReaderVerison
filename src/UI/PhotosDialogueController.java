package UI;

import Classes.Artwork;
import Classes.AuctionListing;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Morgan Jones 904410
 */
public class PhotosDialogueController implements Initializable
{
    public javafx.scene.image.ImageView artPhoto;
    public javafx.scene.control.Button previousButton;
    public javafx.scene.control.Button nextButton;

    private static int picturePosition;
    private ArrayList<String> allPhotos;

    @FXML
    private void handleCancel()
    {
        Main.popup.close();
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

