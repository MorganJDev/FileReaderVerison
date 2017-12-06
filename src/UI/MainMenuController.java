package UI;

import Classes.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static UI.Main.closeApplication;

/**
 * @author Morgan Jones 904410
 */

public class MainMenuController implements Initializable
{
    @FXML
    private TableView<AuctionListing> myArtworkTable;
    @FXML
    private TableColumn<AuctionListing, String> myTitleColumn;
    @FXML
    private TableColumn<AuctionListing, Integer> myReserveColumn;
    @FXML
    private TableColumn<AuctionListing, String> myListingStatus;
    @FXML
    private TableColumn<AuctionListing, String> myRemainingBids;

    @FXML
    private TableView<AuctionListing> browseArtworkTable;
    @FXML
    private TableColumn<AuctionListing, String> browseTitleColumn;
    @FXML
    private TableColumn<AuctionListing, String> browseSellerColumn;

    @FXML
    private Label artDescriptionLabel;
    @FXML
    private Label yearCreatedLabel;
    @FXML
    private Label createdByLabel;
    @FXML
    private Label heightLabel;
    @FXML
    private Label widthLabel;
    @FXML
    private Label depthLabel;
    @FXML
    private Label materialLabel;

    @FXML
    private Label artDescriptionLabel2;
    @FXML
    private Label yearCreatedLabel2;
    @FXML
    private Label createdByLabel2;
    @FXML
    private Label heightLabel2;
    @FXML
    private Label widthLabel2;
    @FXML
    private Label depthLabel2;
    @FXML
    private Label materialLabel2;

    @FXML
    private TableView<Bid> bidTable;
    @FXML
    private TableColumn<Bid, String> bidDateColumn;
    @FXML
    private TableColumn<Bid, String> bidTitleColumn;
    @FXML
    private TableColumn<Bid, String> bidSellerColumn;
    @FXML
    private TableColumn<Bid, Integer> bidAmountColumn;
    @FXML
    private TableColumn<Bid, String> bidStatusColumn;

    @FXML
    private TableView<AuctionListing> doneTable;
    @FXML
    private TableColumn<AuctionListing, String> doneTitleColumn;
    @FXML
    private TableColumn<AuctionListing, String> doneBuyerColumn;
    @FXML
    private TableColumn<AuctionListing, Integer> doneAmountColumn;

    private AuctionListing selectedAuctionListing;

    @FXML
    private void initialize()
    {
        // bind the table fields for the My Artwork tab
        myTitleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getArtworkTitle()));
        myReserveColumn
                .setCellValueFactory(cellData ->  new SimpleIntegerProperty(cellData.getValue().reservePrice()).asObject());
        myListingStatus.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        myRemainingBids.setCellValueFactory(
                cellData -> new SimpleStringProperty("" + cellData.getValue().getRemainingBids()));

        // bind the table fields for the Browse Artwork tab
        browseTitleColumn
                .setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getArtworkTitle()));
        browseSellerColumn
                .setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getSellerUsername()));

        // bind the table fields for the My Bids tab
        bidDateColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getBidPlaced().toString()));
        bidTitleColumn.setCellValueFactory(
                cellData ->  new SimpleStringProperty(cellData.getValue().getListing().getArtworkTitle()));
        bidSellerColumn.setCellValueFactory(
                cellData ->  new SimpleStringProperty(cellData.getValue().getListing().getSellerUsername()));
        bidAmountColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAmount()).asObject());
        bidStatusColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getStatus().toString()));

        // bind the table fields for the Completed Auctions tab
        doneTitleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSellerUsername()));
        doneBuyerColumn
                .setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWinningBidder().getUsername()));
        doneAmountColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().winningPrice()).asObject());

        // Clear details when nothing is selected on tables
        showMyArtworkDetails(null);
        showBrowseArtworkDetails(null);

        // Listen for selection changes and show details when changed.
        myArtworkTable.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showMyArtworkDetails(newValue));
        browseArtworkTable.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showBrowseArtworkDetails(newValue));

    }

    private void showMyArtworkDetails(AuctionListing auctionListing) {
        selectedAuctionListing = auctionListing;

        if (auctionListing != null) {
            Artwork artwork = auctionListing.getArtwork();

            artDescriptionLabel.setText(artwork.getDescription());
            yearCreatedLabel.setText("" + artwork.getCreationYear());
            createdByLabel.setText(artwork.getCreatorName());

            if (artwork instanceof Painting) {
                Painting painting = (Painting) artwork;

                heightLabel.setText("" + painting.getHeight());
                widthLabel.setText("" + painting.getWidth());
                depthLabel.setText("N/A");
                materialLabel.setText("N/A");
            } else if (artwork instanceof Sculpture) {
                Sculpture sculpture = (Sculpture) artwork;

                heightLabel.setText("" + sculpture.getHeight());
                widthLabel.setText("" + sculpture.getWidth());
                depthLabel.setText("" + sculpture.getDepth());
                materialLabel.setText(sculpture.getMaterial());
            } else {
                // Auction listing is null, remove all the text.
                artDescriptionLabel.setText("");
                yearCreatedLabel.setText("");
                createdByLabel.setText("");
                heightLabel.setText("");
                widthLabel.setText("");
                depthLabel.setText("");
                materialLabel.setText("");
            }
        }
    }

    private void showBrowseArtworkDetails(AuctionListing auctionListing) {
        selectedAuctionListing = auctionListing;

        if (auctionListing != null) {
            Artwork artwork = auctionListing.getArtwork();

            artDescriptionLabel2.setText(artwork.getDescription());
            yearCreatedLabel2.setText("" + artwork.getCreationYear());
            createdByLabel2.setText(artwork.getCreatorName());

            if (artwork instanceof Painting) {
                Painting painting = (Painting) artwork;

                heightLabel2.setText("" + painting.getHeight());
                widthLabel2.setText("" + painting.getWidth());
                depthLabel2.setText("N/A");
                materialLabel2.setText("N/A");
            } else if (artwork instanceof Sculpture) {
                Sculpture sculpture = (Sculpture) artwork;

                heightLabel2.setText("" + sculpture.getHeight());
                widthLabel2.setText("" + sculpture.getWidth());
                depthLabel2.setText("" + sculpture.getDepth());
                materialLabel2.setText(sculpture.getMaterial());
            } else {
                // Auction listing is null, remove all the text.
                artDescriptionLabel2.setText("");
                yearCreatedLabel2.setText("");
                createdByLabel2.setText("");
                heightLabel2.setText("");
                widthLabel2.setText("");
                depthLabel2.setText("");
                materialLabel2.setText("");
            }
        }
    }

    // Profile tab
    public javafx.scene.control.TextField usernameField;
    public javafx.scene.control.TextField firstNameField;
    public javafx.scene.control.TextField lastNameField;
    public javafx.scene.control.TextField telNoField;
    public javafx.scene.control.TextField addOneField;
    public javafx.scene.control.TextField addTwoField;
    public javafx.scene.control.TextField cityField;
    public javafx.scene.control.TextField countyField;
    public javafx.scene.control.TextField postCodeField;
    public javafx.scene.control.Button editDetailsButton;

    @FXML
    private void showProfile()
    {
        User newUser = Main.admin.getCurrentUser();
        usernameField.setText(newUser.getUsername());
        firstNameField.setText(newUser.getForename());
        lastNameField.setText(newUser.getSurname());
        telNoField.setText(newUser.getTelephoneNumber());
        addOneField.setText(newUser.getAddressLineOne());
        addTwoField.setText(newUser.getAddressLineTwo());
        cityField.setText(newUser.getCity());
        countyField.setText(newUser.getCounty());
        postCodeField.setText(newUser.getPostcode());
    }

    @FXML
    private void handleEditProfile()
    {
        if (editDetailsButton.getText().equals("Edit details"))
        {
            editDetailsButton.setText("Save details");
            usernameField.setEditable(true);
            firstNameField.setEditable(true);
            lastNameField.setEditable(true);
            telNoField.setEditable(true);
            addOneField.setEditable(true);
            addTwoField.setEditable(true);
            cityField.setEditable(true);
            countyField.setEditable(true);
            postCodeField.setEditable(true);
        }
        else
        {
            editDetailsButton.setText("Edit details");
            usernameField.setEditable(false);
            firstNameField.setEditable(false);
            lastNameField.setEditable(false);
            telNoField.setEditable(false);
            addOneField.setEditable(false);
            addTwoField.setEditable(false);
            cityField.setEditable(false);
            countyField.setEditable(false);
            postCodeField.setEditable(false);

            saveUser();
        }
    }

    private void saveUser()
    {
        for (User i : Main.admin.getAllUsers())
        {
            if (i.getUsername().equals(Main.admin.getCurrentUser().getUsername()))
            {
                i.setUsername(usernameField.getText());
                i.setForename(firstNameField.getText());
                i.setSurname(lastNameField.getText());
                i.setTelephoneNumber(telNoField.getText());
                i.setAddressLineOne(addOneField.getText());
                i.setAddressLineTwo(addTwoField.getText());
                i.setCity(cityField.getText());
                i.setCounty(countyField.getText());
                i.setPostcode(postCodeField.getText());

                Main.admin.setCurrentUser(i);
            }
        }
    }

    @FXML
    private void handleNewSculpture() throws Exception
    {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("SculptureDialog.fxml"));

        window.setScene(new Scene(current));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        Main.popup = window;
    }

    @FXML
    private void handleLogout() throws Exception
    {
        Stage window = new Stage();
        Parent current = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Main.window.close();
        Main.window.setScene(new Scene(current));
        Main.window.show();
        Main.window.setOnCloseRequest(e -> closeApplication());
    }

    @FXML
    private void handleNewPainting() throws Exception
    {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("PaintingDialog.fxml"));

        window.setScene(new Scene(current));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        Main.popup = window;
    }

    @FXML
    private void handleAddPhotos() throws Exception
    {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("AddPhotosDialogue.fxml"));

        window.setScene(new Scene(current));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        Main.popup = window;
    }

    @FXML
    private void handleBid() throws Exception
    {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("BidDialog.fxml"));

        window.setScene(new Scene(current));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        Main.popup = window;
    }

    @FXML
    private void handleViewPhotos() throws Exception
    {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("PhotosDialogue.fxml"));

        window.setScene(new Scene(current));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        Main.popup = window;
    }

    @FXML
    private void handleNewAvatar() throws Exception
    {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("AvatarDialogue.fxml"));

        window.setScene(new Scene(current));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        Main.popup = window;
    }

    @FXML
    private void handleNewCustom() throws Exception
    {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("Canvas.fxml"));

        window.setScene(new Scene(current));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        Main.popup = window;
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
    public void initialize(URL location, ResourceBundle resources) {
        User user = Main.admin.getCurrentUser();

        if (user != null) {
            ObservableList<Bid> bids = FXCollections.observableArrayList();
            for(AuctionListing al : Main.auctioneer.getAuctionListings()) {
                for (Bid b : al.getBids()) {
                    if (b.getBidder() == user) {
                        bids.add(b);
                    }
                }
            }
            bidTable.setItems(bids);

            if (Main.auctioneer != null) {
                myArtworkTable.setItems((ObservableList<AuctionListing>) Main.auctioneer.getMyOpenAuctionListings(user));

                browseArtworkTable.setItems((ObservableList<AuctionListing>) Main.auctioneer.getOtherAuctionListings(user));

                doneTable.setItems((ObservableList<AuctionListing>) Main.auctioneer.getMyClosedAuctionListings(user));
            }
        }
        showProfile();
    }
}
