package UI;

import Classes.AuctionListing;
import Classes.Painting;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * This controls the GUI of adding a new painting.
 * It handles posting a new auction to the system
 *
 * @author Morgan Jones 904410
 * @version 1.0
 */

public class PaintingDialogueController
{
    // References to GUI
    public javafx.scene.control.TextField titleField;
    public javafx.scene.control.TextField descriptionField;
    public javafx.scene.control.TextField createdByField;
    public javafx.scene.control.TextField yearCreatedField;
    public javafx.scene.control.TextField heightField;
    public javafx.scene.control.TextField widthField;
    public javafx.scene.control.TextField reserveAmountField;
    public javafx.scene.control.TextField maximumBidsField;

    /**
     * This closes the form
     */
    @FXML
    private void handleCancel() throws Exception
    {
        Parent current = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        Main.window.close();
        Main.window.setScene(new Scene(current));
        Main.window.show();

        Main.window.setOnCloseRequest(e -> Main.closeApplication());

        Main.popup.close();
    }

    /**
     * This pulls data from text boxes, creates a new painting object
     * and then posts it for auction
     */
    @FXML
    private void handlePostAuction() throws Exception
    {
        String title = titleField.getText();
        String description = descriptionField.getText();
        String createdBy = createdByField.getText();

        // Extracting int values from input
        int creationYear = Integer.parseInt(yearCreatedField.getText());
        int height = Integer.parseInt(heightField.getText());
        int width = Integer.parseInt(widthField.getText());
        int reserve = Integer.parseInt(reserveAmountField.getText());
        int maxBid = Integer.parseInt(maximumBidsField.getText());

        Painting newPaint = new Painting(title,description,createdBy,creationYear,null,
                width,height);
        AuctionListing newListing = new AuctionListing(Main.admin.getCurrentUser(),newPaint,
                maxBid,reserve);
        Main.auctioneer.post(newListing);

        // Closes the form
        handleCancel();
    }
}
