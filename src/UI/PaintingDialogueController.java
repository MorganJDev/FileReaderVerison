package UI;

import Classes.AuctionListing;
import Classes.Painting;
import Classes.Sculpture;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * @author Morgan Jones 904410
 */

public class PaintingDialogueController
{
    public javafx.scene.control.TextField titleField;
    public javafx.scene.control.TextField descriptionField;
    public javafx.scene.control.TextField createdByField;
    public javafx.scene.control.TextField yearCreatedField;
    public javafx.scene.control.TextField heightField;
    public javafx.scene.control.TextField widthField;
    public javafx.scene.control.TextField reserveAmountField;
    public javafx.scene.control.TextField maximumBidsField;

    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }

    @FXML
    private void handlePostAuction()
    {
        String title = titleField.getText();
        String description = descriptionField.getText();
        String createdBy = createdByField.getText();
        int creationYear = Integer.parseInt(yearCreatedField.getText());
        int height = Integer.parseInt(heightField.getText());
        int width = Integer.parseInt(widthField.getText());
        int reserve = Integer.parseInt(reserveAmountField.getText());
        int maxBid = Integer.parseInt(maximumBidsField.getText());

        Painting newPaint = new Painting(title,description,createdBy,creationYear,null,
                width,height,"painting");
        AuctionListing newListing = new AuctionListing(Main.admin.getCurrentUser(),newPaint,
                maxBid,reserve);
        Main.auctioneer.post(newListing);

        handleCancel();
    }
}
