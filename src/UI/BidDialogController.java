package UI;

import Classes.AuctionListing;
import Classes.Bid;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This controls the interface for adding a new bid.
 * It alerts the user if the bid is accepted or not and
 * calls the @processBid method in AuctionLisitng
 * @see AuctionListing#processBid(Bid)
 *
 * @author Morgan Jones 904410
 * @version 1.0
 */

public class BidDialogController implements Initializable
{
    // References to GUI
    public javafx.scene.control.TextField bidAmountField;
    public javafx.scene.control.Label artworkTitleLabel;

    // The auction which the user selected to bid on
    private AuctionListing auction;

    /**
     * This closes the form upon clicking cancel
     */
    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }

    /**
     * This generates a new bid from the inputted amount.
     * It then calls the process bid method in AuctionListing and outputs
     * if the bid was accepted etc.
     * Calls reload main menu
     * @see AuctionListing#processBid(Bid)
     *
     * @throws Exception If file is not found in method that reloads mainmenu
     */
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

        // Popup box showing if bid is accepted/rejected and reloads main
        alertStatus(result);
        reloadMain();
    }

    /**
     * Loads an alert box with the bid status
     * @param result The output from processBid.
     * @see AuctionListing#processBid(Bid)
     */
    private void alertStatus(String result)
    {
        Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
        newAlert.setTitle("Bid...");
        newAlert.setHeaderText(result);

        newAlert.showAndWait();
    }

    /**
     * This method is called after adding a bid. It reloads main menu and closes this form
     * @throws Exception If file is not found
     */
    private void reloadMain() throws Exception
    {
        // Sets a Main static variable that's referenced on MainMenu initialisation
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
     * Also sets which auction was selected and sets the title
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
