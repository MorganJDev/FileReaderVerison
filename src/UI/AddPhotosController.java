package UI;

import Classes.Artwork;
import Classes.AuctionListing;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class manages the GUI for adding photos to Artwork objects.
 * It manages adding, deleting and viewing photos for an artwork.
 *
 * @author Morgan Jones 904410
 * @version 1.0
 */
public class AddPhotosController implements Initializable
{
    // References to GUI
    public javafx.scene.control.Label artTitle;
    public javafx.scene.control.TextField imagePathField;
    public javafx.scene.image.ImageView artPhoto;
    public javafx.scene.control.Button previousButton;
    public javafx.scene.control.Button nextButton;

    // Stores no of picture being viewed
    private static int picturePosition;

    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed. It also loads an image to the view and
     * enables the next button depending on no of photos
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        artTitle.setText(Main.selectedAuction.getArtworkTitle());

        if(findArtwork().getPhotos() != null)
        {
            picturePosition = 0;

            // Enables next button if artwork pictures > 1
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

    /**
     * This handles the next button.
     * It enables the previous button, loads the next image and checks if it is the last image
     */
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
        artPhoto.setImage(new Image(findArtwork().getPhotos().get(picturePosition)));

        if(picturePosition == 0)
        {
            previousButton.setDisable(true);
        }
    }

    /**
     * This handles adding a new photo by taking the text in the field and appending said file path
     * to the array of image file paths. It then displays the new image
     */
    public void handleAddPhoto()
    {
        String filepath = imagePathField.getText();

        // Only sculptures have multiple photos
        if(Main.selectedAuction.getArtwork().getType().equals("sculpture"))
        {
            findArtwork().getPhotos().add(filepath);

            // Reset field
            imagePathField.setText("");
            imagePathField.setPromptText("Image path...");

            // Move to the new photo and change buttons accordingly
            picturePosition = (Main.selectedAuction.getArtwork().getPhotos().size() - 1);
            artPhoto.setImage(new Image(findArtwork().getPhotos().get(picturePosition)));
            nextButton.setDisable(true);
            previousButton.setDisable(false);
        }
        else
        {
            // If it is a painting then adding a photo replaces the current one
            findArtwork().getPhotos().add(filepath);
            findArtwork().getPhotos().remove(0);
        }
    }

    public Artwork findArtwork()
    {
        for (AuctionListing i : Main.auctioneer.getAuctionListings())
        {
            if (i.getSellerUsername().equals(Main.admin.getCurrentUser().getUsername()))
            {
                if(i.getArtwork().getTitle().equals(Main.selectedAuction.getArtworkTitle()))
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

        // Closes form
        handleCancel();
    }
}
