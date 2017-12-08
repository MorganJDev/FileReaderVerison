package UI;

import Classes.AuctionListing;
import Classes.Sculpture;
import javafx.fxml.FXML;

/**
 * This manages the GUI for registering a new sculpture
 *
 * @author Morgan Jones 904410
 * @version 1.0
 */
public class SculptureController
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
    public javafx.scene.control.TextField materialField;
    public javafx.scene.control.TextField depthField;

    /**
     * This extracts data from the text boxes, creates a new iteration of Sculpture and
     * posts it on auction to the system
     */
    @FXML
    private void handleOk()
    {
        String title = titleField.getText();
        String description = descriptionField.getText();
        String createdBy = createdByField.getText();
        String material = materialField.getText();

        // Extracts int values from text boxes
        int creationYear = Integer.parseInt(yearCreatedField.getText());
        int height = Integer.parseInt(heightField.getText());
        int width = Integer.parseInt(widthField.getText());
        int depth = Integer.parseInt(depthField.getText());
        int reserve = Integer.parseInt(reserveAmountField.getText());
        int maxBid = Integer.parseInt(maximumBidsField.getText());

        Sculpture newSculp = new Sculpture(title,description,createdBy,creationYear,null,
                width,height,depth,material,"sculpture");
        AuctionListing newListing = new AuctionListing(Main.admin.getCurrentUser(),newSculp,
                maxBid,reserve);

        // Adds new auction to static list
        Main.auctioneer.post(newListing);

        handleCancel();
    }

    /**
     * This closes the form
     */
    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }

}
