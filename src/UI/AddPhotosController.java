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
public class AddPhotosController implements Initializable
{
    public javafx.scene.control.Label artTitle;
    public javafx.scene.control.TextField imagePathField;
    public javafx.scene.image.ImageView artPhoto;
    public javafx.scene.control.Button previousButton;
    public javafx.scene.control.Button nextButton;

    private String art;
    private static int picturePosition;

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
        //artTitle.setText(Main.currentView.getTitle());
        // TODO: Set this variable in main when a cell in the table is clicked
        // TODO: In the same method that displays the artwork details on the right

        // These lines are for testing
        art = artTitle.getText();
        art = "Egg";

        if(findArtwork().getPhotos() != null)
        {
            picturePosition = 0;

            switch (findArtwork().getPhotos().size())
            {
                case 0:
                    break;
                case 1:
                    artPhoto.setImage(new Image(findArtwork().getPhotos().get(0)));
                    break;
                default:
                    artPhoto.setImage(new Image(findArtwork().getPhotos().get(0)));
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
        artPhoto.setImage(new Image(findArtwork().getPhotos().get(picturePosition)));

        if(findArtwork().getPhotos().size() == (picturePosition + 1))
        {
            nextButton.setDisable(true);
        }
    }

    @FXML
    public void handlePrevious()
    {
        nextButton.setDisable(false);

        picturePosition--;
        artPhoto.setImage(new Image(findArtwork().getPhotos().get(picturePosition)));

        if(picturePosition == 0)
        {
            previousButton.setDisable(true);
        }
    }

    public void handleAddPhoto()
    {
        String filepath = imagePathField.getText();

        findArtwork().getPhotos().add(filepath);
        imagePathField.setText("");
        imagePathField.setPromptText("Image path...");
        handleNext();
    }

    public Artwork findArtwork()
    {
        for (AuctionListing i : Main.auctioneer.getAuctionListings())
        {
            if (i.getSellerUsername().equals(Main.admin.getCurrentUser().getUsername()))
            {
                if(i.getArtwork().getTitle().equals(art))
                {
                    return i.getArtwork();
                }
            }
        }

        return null;
    }

    @FXML
    public void handleDelete()
    {
        findArtwork().getPhotos().remove(picturePosition);
        handleCancel();
    }
}
