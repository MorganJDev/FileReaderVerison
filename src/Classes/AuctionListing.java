package Classes;

import java.util.ArrayList;

/**
 * This class represents a single auction listing
 * @author Adam Thomas [Student no.]
 * @version 1.0
 */
public class AuctionListing {

	private User seller;
	private Artwork artwork;
	private ArrayList<Bid> bids;
	private int maxBids;
	private String status;
	private int reservePrice;
	private User winningBidder;
	private int winningPrice;

	/**
	 * Creates a new auction without any bids placed on it
	 * @param seller The user who is selling the artwork in this auction
	 * @param artwork The artwork that has been put up for auction
	 * @param maxBids The maximum amount of bids allowed for this auction
	 * @param reservePrice The reserve (minimum) price of this auction
	 */
	public AuctionListing(User seller, Artwork artwork, int maxBids, int reservePrice) {
		this.seller = seller;
		this.artwork = artwork;
		this.maxBids = maxBids;
		this.reservePrice = reservePrice;
		this.status = "Active";
		/*Seen as there are no bids yet, bids is made to be a new, empty list, the seller is set
		as the winningBidder and reservePrice is set as the winning price*/
		this.bids = new ArrayList<Bid>();
		this.winningBidder = seller;
		this.winningPrice = reservePrice;
	}

	/**
	 * Creates an auction with bids placed on it
	 * @param seller The user who is selling the artwork in this auction
	 * @param artwork The artwork that has been put up for auction
	 * @param maxBids The maximum amount of bids allowed for this auction
	 * @param reservePrice The reserve (minimum) price of this auction
	 * @param currentStatus The current status of the auction, auctions are either "active" or "closed"
	 * @param currentBids All the bids that have been placed on the auction so far
	 * @param currentWinner The user who is currently winning this auction
	 * @param currentWinningPrice The price of the current winning bid
	 */
	public AuctionListing(User seller, Artwork artwork, int maxBids, int reservePrice, String currentStatus, ArrayList<Bid> currentBids
			, User currentWinner, int currentWinningPrice) {
		this.seller = seller;
		this.artwork = artwork;
		this.maxBids = maxBids;
		this.reservePrice = reservePrice;
		this.status = currentStatus;
		this.bids = currentBids;
		this.winningBidder = currentWinner;
		this.winningPrice = currentWinningPrice;
	}

	/**
	 * Get the artwork up for auction
	 * @return The auctioned artwork
	 */
	public Artwork getArtwork() {
		return artwork;
	}

	/**
	 * Get the current winning price for this auction
	 * @return The winning price
	 */
	public int winningPrice() {
		return this.winningPrice;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	/**
	 * Get the user who put up this auction
	 * @return The seller of the artwork up for auction
	 */
	public User getSeller() {
		return this.seller;
	}

	/**
	 * Get the username of the seller
	 * @return Seller's username
	 */
	public String getSellerUsername() {
		return this.seller.getUsername();
	}

	/**
	 * Sets the artwork associated with this auction
	 * @param artwork The artwork up for auction
	 */
	public void setArtwork(Artwork artwork) {
		this.artwork = artwork;
	}

	public void setMaxBids(int maxBids) {
		this.maxBids = maxBids;
	}

	public void setReservePrice(int reservePrice) {
		this.reservePrice = reservePrice;
	}

	/**
	 * Get the reservePrice of this auction
	 * @return the reserve price
	 */
	public int reservePrice() {
		return this.reservePrice;
	}

	/**
	 * Get the title of the artwork up for auction
	 * @return Auctioned artwork title
	 */
	public String getArtworkTitle() {
		return artwork.getTitle();
	}

	/**
	 * Get all of the bids placed on the auction so far
	 * @return Bids placed on this auction
	 */
	public ArrayList<Bid> getBids() {
		return bids;
	}

	/**
	 * Get the status of this auction
	 * @return the auction status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Method that sets the staus of this auction
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Get the max number of bids that can be placed on this auction
	 * @return the maximum bids
	 */
	public int getMaxBids() {
		return maxBids;
	}

	/**
	 * Get the remaining bids for this auction
	 * @return The remaining bids
	 */
	public int getRemainingBids() {
		if (this.status == "Closed")
			return 0;

		int availableBids = maxBids;

		for (Bid b : bids) {
			if (b.getStatus().equals("Accepted"))
				availableBids--;
		}
		return availableBids;
	}

	/**
	 * Get the reserve price for this auction
	 * @return the reservePrice
	 */
	public int getReservePrice() {
		return reservePrice;
	}

	/**
	 * This method is used to process a bid that has been placed on the auction, this will
	 * determine the status of the bid i.e. whether it is accepted, ignored, rejected etc.
	 * and add it to the auctions list of bids
	 * @param newbid The bid that is being placed on the auction
	 * @return The bids status
	 */
	public String processBid(Bid newbid) {
		// does this listing have any bid slots available?
		if (getRemainingBids() == 0) {
			newbid.setStatus("Ignored");
			return newbid.getStatus();
		}

		if (newbid.getBidder() == this.winningBidder) {
			newbid.setStatus("Rejected Highest");
			return newbid.getStatus();
		}

		// determine what the bid status will be

		// initially assume this is the leading bid
		newbid.setStatus("Accepted");

		// if there are any higher bids previously placed
		// then change this bid to be rejected
		for (Bid bid : bids) {
			if (bid.getAmount() >= newbid.getAmount()) {
				newbid.setStatus("Rejected Outbid");
				return newbid.getStatus();
			}
		}

		// check this bid is above the reserve for the listing
		if (newbid.getAmount() < getReservePrice()) {
			newbid.setStatus("Rejected");
			return newbid.getStatus();
		}

		// store the current winning bidder
		if (newbid.getStatus() == "Accepted") {
			this.winningBidder = newbid.getBidder();
			this.winningPrice = newbid.getAmount();
		}

		// add this bid to the auction listing
		bids.add(newbid);

		newbid.setListing(this);

		// close the auction if there are no more bid slots remaining
		if (getRemainingBids() == 0) {
			boolean winnerFound = false;

			for (int j = bids.size() - 1; j >= 0; j--) {
				Bid bid = bids.get(j);
				if ((winnerFound == false) && (bid.getStatus() == "Accepted")) {
					winnerFound = true;
					bid.setStatus("Winner");
					winningBidder = bid.getBidder();
					this.status = "Closed";
				} else {
					if (bid.getStatus() == "Accepted") {
						bid.setStatus("Loser");
					}
				}
			}
		}

		// return this bid status
		return newbid.getStatus();
	}

	/**
	 * Get the bidder (user) who is currently winning this auction
	 * @return The current winning bidder
	 */
	public User getWinningBidder() {
		return this.winningBidder;
	}

	/**
	 * Get the price of the current winning bid
	 * @return the winningPrice
	 */
	public int getWinningPrice() {
		return winningPrice;
	}
}
