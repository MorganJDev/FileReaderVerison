package UI;

import Classes.AuctionListing;
import Classes.Painting;
import Classes.Sculpture;
import javafx.fxml.FXML;

public class SculptureController
{
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

    @FXML
    private void handleOk()
    {
        String title = titleField.getText();
        String description = descriptionField.getText();
        String createdBy = createdByField.getText();
        int creationYear = Integer.parseInt(yearCreatedField.getText());
        int height = Integer.parseInt(heightField.getText());
        int width = Integer.parseInt(widthField.getText());
        int depth = Integer.parseInt(depthField.getText());
        int reserve = Integer.parseInt(reserveAmountField.getText());
        int maxBid = Integer.parseInt(maximumBidsField.getText());
        String material = materialField.getText();

        Sculpture newSculp = new Sculpture(title,description,createdBy,creationYear,null,
                width,height,depth,material);
        AuctionListing newListing = new AuctionListing(Main.admin.getCurrentUser(),newSculp,
                maxBid,reserve);
        Main.auctioneer.post(newListing);

        handleCancel();
    }

    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }

}
