package UI;

import Classes.AuctionListing;
import Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Luke Thomas [student number]
 */
public class ViewSellerDialogueController implements Initializable{

    @FXML
    private javafx.scene.image.ImageView sellerImage;
    @FXML
    private Label sellerName;
    @FXML
    private Button favouriteButton;

    private AuctionListing selectedAuctionListing;

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

    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.selectedAuctionListing = Main.selectedAuction;
        setupScene();
    }
}
