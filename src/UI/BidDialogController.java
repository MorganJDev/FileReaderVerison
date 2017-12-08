package UI;

import Classes.AuctionListing;
import Classes.Bid;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Morgan Jones 904410
 */

public class BidDialogController implements Initializable
{
    public javafx.scene.control.TextField bidAmountField;
    public javafx.scene.control.Label artworkTitleLabel;

    private AuctionListing auction;

    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }

    @FXML
    private void handlePlaceBid() throws Exception
    {
        String result = "";
        Bid newBid = new Bid(Main.admin.getCurrentUser(),Integer.parseInt(bidAmountField.getText()),
                null,null);

        for (AuctionListing i : Main.auctioneer.getAuctionListings())
        {
            if (i.getArtworkTitle().equals(auction.getArtworkTitle()))
            {
                result = i.processBid(newBid);
                Main.popup.close();
            }
        }

        alertStatus(result);
        reloadMain();
    }

    private void alertStatus(String result)
    {
        Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
        newAlert.setTitle("Bid...");
        newAlert.setHeaderText(result);

        newAlert.showAndWait();
    }

    private void reloadMain() throws Exception
    {
        Stage window = new Stage();
        Main.reloadedOn = "Browse";

        Parent current = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        Main.window.close();
        Main.window.setScene(new Scene(current));
        Main.window.show();

        Main.window.setOnCloseRequest(e -> Main.closeApplication());

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
        auction = Main.selectedAuction;
        artworkTitleLabel.setText(auction.getArtworkTitle());
    }
}
