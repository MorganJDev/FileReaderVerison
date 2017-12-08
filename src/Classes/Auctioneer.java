package Classes;

import UI.Main;

import java.util.ArrayList;

/**
 * This class stores and handles all auctions stored on the system
 * @author Adam Thomas [Student no.]
 * @version 1.0
 */
public class Auctioneer {

	private ArrayList<AuctionListing> auctionListings;

	/**
	 * Creates a new auctioneer
	 */
	public Auctioneer() {
		auctionListings = new ArrayList<>();
	}

	/**
	 * Returns all of the open auctions put up by a user
	 * @param user The user whose open auctions we are returning
	 * @return Users open auctions
	 */
	public ArrayList<AuctionListing> getMyOpenAuctionListings(User user) {
		ArrayList<AuctionListing> results = new ArrayList<AuctionListing>();

		for (AuctionListing listing : auctionListings) {
			if ((listing.getSellerUsername().equals(user.getUsername())) && (!(listing.getStatus().equals("Closed"))))
				results.add(listing);
		}

		return results;
	}

	/**
	 * Returns all of the closed auctions put up by a user
	 * @param user The user whose closed auctions we are returning
	 * @return Users closed auctions
	 */
	public ArrayList<AuctionListing> getMyClosedAuctionListings(User user) {
		ArrayList<AuctionListing> results = new ArrayList<AuctionListing>();

		for (AuctionListing listing : auctionListings) {
			if ((listing.getSellerUsername().equals(user.getUsername())) && (listing.getStatus().equals("Closed")))
				results.add(listing);
		}

		return results;
	}

	/**
	 * Returns all of the active auctions put up by other users
	 * @param user The user whose auctions will not be returned
	 * @return Other users active auctions
	 */
	public ArrayList<AuctionListing> getOtherAuctionListings(User user) {
		ArrayList<AuctionListing> results = new ArrayList<AuctionListing>();

		for (AuctionListing listing : auctionListings) {
			if (!(listing.getSellerUsername().equals(user.getUsername())) && (listing.getStatus().equals("Active")))
				results.add(listing);
		}

		return results;
	}

	public ArrayList<AuctionListing> getWonListings(User user) {
		ArrayList<AuctionListing> results = new ArrayList<AuctionListing>();

		for (AuctionListing listing : auctionListings) {
			if ((listing.getStatus() == "Closed") && (listing.getWinningBidder() == user))
				results.add(listing);
		}

		return results;
	}

	/**
	 * Place a bid on an auction
	 * @param listing the auction the bid is being placed on
	 * @param bid the bid being placed on the auction
	 * @return the status of the bid
	 */
	public String bidFor(AuctionListing listing, Bid bid) {
		return listing.processBid(bid);
	}

	/**
	 * Puts an auction up for sale
	 * @param listing the auction being put up
	 */
	public void post(AuctionListing listing) {
		listing.setStatus("Active");
		auctionListings.add(listing);
	}

	/**
	 * Returns all of the auctions stored on the system
	 * @return All auctions
	 */
	public ArrayList<AuctionListing> getAuctionListings() {
		return auctionListings;
	}

	/**
	 * Gets all of the auctions stored in the system input file and adds them to
	 * the list of auctions
	 */
	public void populateArray()
    {
        this.auctionListings = (FileReader.readAuctions("auctions.txt", Main.admin));
    }

	/**
	 * Writes out all of the auctions to an output file for storage
	 */
	public void writeFiles()
    {
        FileWriter.writeAuctions("auctions.txt",Main.auctioneer);
    }
}