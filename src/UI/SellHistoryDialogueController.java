package UI;

import Classes.AuctionListing;
import Classes.Bid;
import Classes.User;
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
 * @author Luke Thomas [student no]
 */
public class SellHistoryDialogueController implements Initializable {

    @FXML
    private TableView<Bid> bidTable;
    @FXML
    private TableColumn<Bid, String> bidderColumn;
    @FXML
    private TableColumn<Bid, String> bidDateColumn;
    @FXML
    private TableColumn<Bid, Integer> bidAmountColumn;
    @FXML
    private TableColumn<Bid, String> bidStatusColumn;
    @FXML
    private Label artworkName;

    private AuctionListing selectedAuctionListing;

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

    public void populateTable() {
        AuctionListing auctionListing = selectedAuctionListing;

        artworkName.setText(auctionListing.getArtworkTitle());
        ObservableList<Bid> bids = FXCollections.observableArrayList();
        for (Bid b : auctionListing.getBids()) {
            bids.add(b);
        }
        bidTable.setItems(bids);
    }

    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.selectedAuctionListing = Main.selectedAuction;
        setupTable();
        populateTable();
    }
}
