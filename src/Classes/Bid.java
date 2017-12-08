package Classes; 

/**
 * A class to model a bid on an auction listing
 * @author Keoni D'Souza 921231
 * @version 1.0
 */

import java.time.LocalDateTime;


public class Bid {

	private AuctionListing auctionListing;
	private User bidder;
	private int amount;
	private String status;
	private LocalDateTime bidPlaced;

	/**
	 * Constructor method
	 * @param bidder The Classes.User who is placing a bid
	 * @param amount The price the bidder is placing
	 * @param status The status of this bid
	 * @param bidPlaced the date this bid was placed
	 */
	public Bid(User bidder, int amount, String status, LocalDateTime bidPlaced) {
		this.bidder = bidder;
		this.amount = amount;
		if (status == null) {
			this.status = "Unposted";
		} else {
			this.status = status;
		}
		if (bidPlaced == null) {
			this.bidPlaced = LocalDateTime.now();
		} else {
			this.bidPlaced = bidPlaced;
		}
	}

	/**
	 * Get the listing the bid is placed on
	 * @return the listing
	 */
	public AuctionListing getListing() {
		return auctionListing;
	}

	/**
	 * Get the user who placed this bid
	 * @return The bidder
	 */
	public User getBidder() {
		return bidder;
	}

	/**
	 * Get the amount of this bid
	 * @return The amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Get the time that the bid was placed
	 * @return the bid placed date
	 */
	public LocalDateTime getBidPlaced() {
		return bidPlaced;
	}

	/**
	 * Returns the status of this bid
	 * @return bid status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Set the auction listing this bid has been placed on
	 * @param auctionListing the listing this bid has been placed on
	 */
	public void setListing(AuctionListing auctionListing) {
		this.auctionListing = auctionListing;
	}

	/**
	 * Set the status of this bid
	 * @param newstat bid status
	 */
	public void setStatus(String newstat) {
		this.status = newstat;
	}

	@Override
	public String toString() {
		return "This bid at " +
				this.amount +
				" has been placed by " +
				this.bidder +
				" at " +
				this.bidPlaced +
				".";
		}

}
