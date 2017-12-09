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
import javafx.scene.image.Image;
import javafx.scene.control.TableColumn;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import static UI.Main.closeApplication;

/**
 * This class manages the Main Menu window.
 * It controls navigation to all other menus and displays
 * all the user and auction info in their respective tabs
 *
 * @author Morgan Jones 904410
 * @author Luke Thomas 905557
 */

public class MainMenuController implements Initializable
{
    // References to GUI

    // Tab Bar
    public javafx.scene.control.Tab profileTab;
    public javafx.scene.control.TabPane tabMenu;
    public javafx.scene.control.Tab browseTab;

    // My Artwork Tab
    // Table
    @FXML private TableView<AuctionListing> myArtworkTable;
    @FXML private TableColumn<AuctionListing, String> myTitleColumn;
    @FXML private TableColumn<AuctionListing, Integer> myReserveColumn;
    @FXML private TableColumn<AuctionListing, String> myListingStatus;
    @FXML private TableColumn<AuctionListing, String> myRemainingBids;

    // Labels for my artwork
    @FXML private Label artDescriptionLabel;
    @FXML private Label yearCreatedLabel;
    @FXML private Label createdByLabel;
    @FXML private Label heightLabel;
    @FXML private Label widthLabel;
    @FXML private Label depthLabel;
    @FXML private Label materialLabel;

    // Browse tab
    // Table
    @FXML private TableView<AuctionListing> browseArtworkTable;
    @FXML private TableColumn<AuctionListing, String> browseTitleColumn;
    @FXML private TableColumn<AuctionListing, String> browseSellerColumn;

    // Labels for browse tab
    @FXML private Label artDescriptionLabel2;
    @FXML private Label yearCreatedLabel2;
    @FXML private Label createdByLabel2;
    @FXML private Label heightLabel2;
    @FXML private Label widthLabel2;
    @FXML private Label depthLabel2;
    @FXML private Label materialLabel2;

    // My Bids Tab
    @FXML private TableView<Bid> bidTable;
    @FXML private TableColumn<Bid, String> bidDateColumn;
    @FXML private TableColumn<Bid, String> bidTitleColumn;
    @FXML private TableColumn<Bid, String> bidSellerColumn;
    @FXML private TableColumn<Bid, Integer> bidAmountColumn;
    @FXML private TableColumn<Bid, String> bidStatusColumn;

    // Completed Auctions tab
    @FXML private TableView<AuctionListing> doneTable;
    @FXML private TableColumn<AuctionListing, String> doneTitleColumn;
    @FXML private TableColumn<AuctionListing, String> doneBuyerColumn;
    @FXML private TableColumn<AuctionListing, Integer> doneAmountColumn;

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
    public javafx.scene.image.ImageView profileImageView;

    // The selected artwork
    private AuctionListing selectedAuctionListing;

    /**
     * Method used to set the data stored in each table and each table column,
     * also sets the value of the additional information provided for a piece of artwork on
     * my artworks or browse to be equal to the currently selected artwork/auction
     */
    @FXML
    private void setupTable()
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

        // bind the table fields for the Completed Artwork tab
        doneTitleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getArtworkTitle()));
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

    /**
     * Method used to set the information displayed by the labels on the my artwork tab
     * to the information of the currently selected artwor/auction
     * @param auctionListing the currently selected artwork/auction
     */
    private void showMyArtworkDetails(AuctionListing auctionListing) {
        selectedAuctionListing = auctionListing;
        Main.selectedAuction = selectedAuctionListing;

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

    /**
     * Method used to set the information displayed by the labels on the browse artwork tab
     * to the information of the currently selected artwor/auction
     * @param auctionListing the currently selected artwork/auction
     */
    private void showBrowseArtworkDetails(AuctionListing auctionListing) {
        selectedAuctionListing = auctionListing;
        Main.selectedAuction = selectedAuctionListing;

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

    /**
     * This populates the text boxes on the profile tab with the info about
     * the currently logged in user
     */
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

        if(Main.admin.getCurrentUser().getProfileImage() != "")
        {
            profileImageView.setImage(new Image(Main.admin.getCurrentUser().getProfileImage()));
        }
    }

    /**
     * This method manages editing and saving user data when edit is clicked.
     * Ir enables/disables the text boxes for editing and changes the button text
     */
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

    /**
     * This finds the current user in the static list and updates the fields from
     * the data in the text boxes
     */
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

    /**
     * This loads the menu to view all bids on a users artwork
     * @throws Exception If file is not found
     */
    @FXML
    private void handleViewMyArtworkBids() throws Exception
    {
        if (Main.selectedAuction != null)
        {
            Stage window = new Stage();
            Main.selectedAuction = selectedAuctionListing;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("SellHistoryDialogue.fxml"));
            Parent current = loader.load();

            window.setScene(new Scene(current));
            window.initModality(Modality.APPLICATION_MODAL);
            window.show();

            Main.popup = window;
        }
    }

    /**
     * This loads the menu to view the seller of an artwork
     * @throws Exception If file is not found
     */
    @FXML
    private void handleViewSeller() throws Exception
    {
        if (Main.selectedAuction != null)
        {
            Stage window = new Stage();
            Main.selectedAuction = selectedAuctionListing;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewSellerDialog.fxml"));
            Parent current = loader.load();

            window.setScene(new Scene(current));
            window.initModality(Modality.APPLICATION_MODAL);
            window.show();

            Main.popup = window;
        }
    }

    /**
     * This loads the menu to register a new sculpture
     * @throws Exception If file is not found
     */
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

    /**
     * This loads the login screen and closes the main
     * @throws Exception If file is not found
     */
    @FXML
    private void handleLogout() throws Exception
    {
        Parent current = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Main.window.close();
        Main.window.setScene(new Scene(current));
        Main.window.show();
        Main.window.setOnCloseRequest(e -> closeApplication());
    }

    /**
     * This loads the menu to register a new painting
     * @throws Exception If file is not found
     */
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

    /**
     * This loads the menu to add/view/delete photos to a users artwork
     * @throws Exception If file is not found
     */
    @FXML
    private void handleAddPhotos() throws Exception
    {
        if (Main.selectedAuction != null)
        {
            Stage window = new Stage();
            Main.selectedAuction = selectedAuctionListing;

            Parent current = FXMLLoader.load(getClass().getResource("AddPhotosDialogue.fxml"));

            window.setScene(new Scene(current));
            window.initModality(Modality.APPLICATION_MODAL);
            window.show();

            Main.popup = window;
        }
    }

    /**
     * This loads the menu to place a bid on an item
     * @throws Exception If file is not found
     */
    @FXML
    private void handleBid() throws Exception
    {
        if (Main.selectedAuction != null)
        {
            Stage window = new Stage();
            Main.selectedAuction = selectedAuctionListing;

            Parent current = FXMLLoader.load(getClass().getResource("BidDialog.fxml"));

            window.setScene(new Scene(current));
            window.initModality(Modality.APPLICATION_MODAL);
            window.show();

            Main.popup = window;
        }
    }

    /**
     * This loads the menu to view the photos of an artwork
     * @throws Exception If file is not found
     */
    @FXML
    private void handleViewPhotos() throws Exception
    {
        if (Main.selectedAuction != null)
        {
            Stage window = new Stage();
            Main.selectedAuction = selectedAuctionListing;

            Parent current = FXMLLoader.load(getClass().getResource("PhotosDialogue.fxml"));

            window.setScene(new Scene(current));
            window.initModality(Modality.APPLICATION_MODAL);
            window.show();

            Main.popup = window;
        }
    }

    /**
     * This loads the menu to view/select a user avatar
     * @throws Exception If file is not found
     */
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

    /**
     * This loads the menu to create a custom avatar
     * @throws Exception If file is not found
     */
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
     * This method is used to populate all the tables in the main menu with information from all auctions stored
     * on the system
     */
    public void populateTables()
    {
        User user = Main.admin.getCurrentUser();

        if (user != null) {
            //Populates the "My bids" tab table with bids placed by the current user
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

                //Populates the "My artworks" tab table with artworks sold by the current user
                ObservableList<AuctionListing> myArtworks = FXCollections.observableArrayList();
                for (AuctionListing al : Main.auctioneer.getMyOpenAuctionListings(user)) {
                    myArtworks.add(al);
                }
                myArtworkTable.setItems(myArtworks);

                //Populates the "Browse artworks" tab table with artworks sold by others user
                ObservableList<AuctionListing> browseArtworks = FXCollections.observableArrayList();
                for (AuctionListing al : Main.auctioneer.getOtherAuctionListings(user)) {
                    browseArtworks.add(al);
                }
                browseArtworkTable.setItems(browseArtworks);

                //Populates the "Closed auctions" tab table with completed auctions put up by the current user
                ObservableList<AuctionListing>  doneAuctions = FXCollections.observableArrayList();
                for (AuctionListing al : Main.auctioneer.getMyClosedAuctionListings(user)) {
                    doneAuctions.add(al);
                }
                doneTable.setItems(doneAuctions);
            }
        }
    }

    /**
     * Called to initialize a controller after its root element  has been
     * completely processed.
     * It runs methods to setup tables, populate tables and populate profile info.
     * It also checks if the main menu has been reloaded by a sub menu, if so it auto selects
     * a tab such as profile
     * @see #setupTable()
     * @see #populateTables()
     * @see #showProfile()
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setupTable();
        populateTables();

        showProfile();

        // If the main menu has been reloaded from the avatar screen
        // then the profile tab becomes selected
        switch(Main.reloadedOn)
        {
            case "Profile":
                tabMenu.getSelectionModel().select(profileTab);
                break;
            case "Browse":
                tabMenu.getSelectionModel().select(browseTab);
                break;
            default:
                break;
        }

        Main.reloadedOn = "";
    }
}
