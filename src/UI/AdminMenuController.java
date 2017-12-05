package UI;

import java.util.ArrayList;

import Classes.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminMenuController {
	
	@FXML
	private TableView<User> browseUserTable;
	@FXML
	private TableColumn<User, String> browseUsernameColumn;
	@FXML
	private TableColumn<User, String> browseForenameColumn;
	@FXML
	private TableColumn<User, String> browseSurnameColumn;

	@FXML
	private Label telephoneLabel;
	@FXML
	private Label addressOneLabel;
	@FXML
	private Label addressTwoLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label countyLabel;
	@FXML
	private Label postcodeLabel;
	
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
	
	private User selectedUser;
	private AuctionListing selectedAuctionListing;
	
	@FXML
	private void initialize() {
		// bind the table fields for the Browse Users tab
		browseUsernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
	    browseForenameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getForename()));
	    browseSurnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSurname()));
	    
	    // bind the table fields for the Browse Users tab
	    browseTitleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getArtworkTitle()));
	    browseSellerColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSellerUsername()));
	    
	    // Clear details when nothing is selected on tables
	    showBrowseUserDetails(null);
	    showBrowseArtworkDetails(null);
	      
	    // Listen for selection changes and show details when changed.
	    browseUserTable.getSelectionModel().selectedItemProperty()
	    	.addListener((observable, oldValue, newValue) -> showBrowseUserDetails(newValue));
	    browseArtworkTable.getSelectionModel().selectedItemProperty()
	    	.addListener((observable, oldValue, newValue) -> showBrowseArtworkDetails(newValue));
	}
	
	@FXML
	public void init() {
		User user = Main.admin.getCurrentUser();
		if (user != null) {
			browseUserTable.setItems((ObservableList<Classes.User>) Main.admin.getAllUsers());
			browseArtworkTable.setItems((ObservableList<AuctionListing>) Main.auctions.getAuctionListings());
	      }
	}
	
	private void showBrowseUserDetails(User user) {
		selectedUser = user;
		if (user != null) {
			telephoneLabel.setText(user.getTelephoneNumber());
			addressOneLabel.setText(user.getAddressLineOne());
			addressTwoLabel.setText(user.getAddressLineTwo());
			cityLabel.setText(user.getCity());
			countyLabel.setText(user.getCounty());
			postcodeLabel.setText(user.getPostcode());
		} else {
			telephoneLabel.setText("");
			addressOneLabel.setText("");
			addressTwoLabel.setText("");
			cityLabel.setText("");
			countyLabel.setText("");
			postcodeLabel.setText("");
		}
	}
	
	private void showBrowseArtworkDetails(AuctionListing auctionListing) {
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
	            materialLabel.setText("" + sculpture.getMaterial());
	            
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
	
    @FXML
    private void handleLogout() throws Exception {
        Stage window = new Stage();
        Parent current = FXMLLoader.load(getClass().getResource("Login.fxml"));

        window.setScene(new Scene(current));
        window.show();

        Main.window.close();
        Main.window = window;

        window.show();
    }

    @FXML
    private void handleEditUser() throws Exception {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("EditUserDialog.fxml"));

        window.setScene(new Scene(current));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        //EditUserDialogController.setUser(selectedUser);
        Main.popup = window;
    }
    
    @FXML
    private void handleEditArtwork() throws Exception {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("EditArtworkDialog.fxml"));

        window.setScene(new Scene(current));
        window.initModality(Modality.APPLICATION_MODAL);
        window.show();

        //EditArtworkDialogController.setAuction(selectedAuctionListing);
        Main.window = window;
    }
    
    @FXML
    private void handleDeleteUser() throws Exception {
    	ArrayList<User> allUsers = Main.admin.getAllUsers();
    	ArrayList<AuctionListing> allAuctions = Main.auctions.getAuctionListings();
        for (int i = 0 ; i < allUsers.size(); i++) {
        	if (allUsers.get(i).equals(selectedUser)) {
        		allUsers.remove(i);
        		for (int j = 0 ; j < allAuctions.size(); j++) {
        			if (allAuctions.get(j).getSeller().equals(selectedUser)) {
                		allAuctions.remove(j);
        			}
        		}
        	}
        }
    }
    
    @FXML
    private void handleDeleteArtwork() throws Exception {
    	ArrayList<AuctionListing> allAuctions = Main.auctions.getAuctionListings();
        for (int i = 0 ; i < allAuctions.size(); i++) {
        	if (allAuctions.get(i).equals(selectedAuctionListing)) {
        		allAuctions.remove(i);
        	}
        }
    }  
}