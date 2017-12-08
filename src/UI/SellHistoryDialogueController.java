package UI;

import Classes.AuctionListing;
import Classes.Bid;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Manages the GUI for viewing the bid history of one of the users artworks,
 * it lets the user view a table of all the bids placed on their artwork/auction
 * @author Luke Thomas 905557
 */
public class SellHistoryDialogueController implements Initializable {

    // References to GUI
    @FXML private TableView<Bid> bidTable;
    @FXML private TableColumn<Bid, String> bidderColumn;
    @FXML private TableColumn<Bid, String> bidDateColumn;
    @FXML private TableColumn<Bid, Integer> bidAmountColumn;
    @FXML private TableColumn<Bid, String> bidStatusColumn;
    @FXML private Label artworkName;

    // The selected auction from main menu
    private AuctionListing selectedAuctionListing;

    /**
     * Sets the values stored in the table and all of it's columns to the information
     * stored in each bid placed on the artwork
     */
    private void setupTable() {
        // bind the table fields for the My Bids tab
        bidderColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getBidder().getUsername()));
        bidDateColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getBidPlaced().toString()));
        bidAmountColumn.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().getAmount()).asObject());
        bidStatusColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getStatus().toString()));
    }

    /**
     * Populates the table with all of the bids placed on the selected artwork
     */
    public void populateTable() {
        AuctionListing auctionListing = selectedAuctionListing;

        artworkName.setText(auctionListing.getArtworkTitle());
        ObservableList<Bid> bids = FXCollections.observableArrayList();
        for (Bid b : auctionListing.getBids()) {
            bids.add(b);
        }
        bidTable.setItems(bids);
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
     * completely processed. It also loads a table of bids placed on
     * the selected artwork
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.selectedAuctionListing = Main.selectedAuction;
        setupTable();
        populateTable();
    }
}
