package UI;

import Classes.AuctionListing;
import Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Luke Thomas [student number]
 */
public class ViewSellerDialogueController implements Initializable{

    // References to GUI
    @FXML private javafx.scene.image.ImageView sellerImage;
    @FXML private Label sellerName;
    @FXML private Button favouriteButton;

    // Selected auction from main menu
    private AuctionListing selectedAuctionListing;

    /**
     * Sets up the scene so that the selected sellers username and profile image
     * are displayed, also sets the text of the favourite button depending on whether
     * the current user has favourited this seller or not
     */
    private void setupScene() {
        if (selectedAuctionListing != null) {
            sellerName.setText(selectedAuctionListing.getSellerUsername());
            sellerImage.setImage(new Image(selectedAuctionListing.getSeller().getProfileImage()));
            favouriteButton.setText("Favourite");
            String seller = selectedAuctionListing.getSellerUsername();
            for (User fav : Main.admin.getCurrentUser().getFavouriteUsers()) {
                if (seller.equals(fav.getUsername())) {
                    favouriteButton.setText("Unfavourite");
                }
            }
        }
    }

    /**
     * This adds the selected seller to the current users favourite list if they are not on it already,
     * otherwise it will remove them from the current users favourites
     * @throws Exception if no auction is selected
     */
    @FXML
    private void handleFavourite() throws Exception
    {
        boolean alreadyFavourited = false;
        int i = 0;
        if (selectedAuctionListing != null) {
            while(!Main.admin.getCurrentUser().getFavouriteUsers().isEmpty()) {
                ArrayList<User> allFavs = Main.admin.getCurrentUser().getFavouriteUsers();
                if (selectedAuctionListing.getSeller().equals(allFavs.get(i))) {
                    Main.admin.getCurrentUser().getFavouriteUsers().remove(i);
                    favouriteButton.setText("Favourite");
                    alreadyFavourited = true;
                } else {
                    i++;
                }
            }
            if (!alreadyFavourited) {
                Main.admin.getCurrentUser().addFavouriteUser(selectedAuctionListing.getSeller());
                favouriteButton.setText("Unfavourite");
            }
        }
    }

    /**
     * This closes the form upon clicking cancel
     */
    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed. It also loads the information stored in the currently selected auction
     * so that we can get information from the seller
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.selectedAuctionListing = Main.selectedAuction;
        setupScene();
    }
}
