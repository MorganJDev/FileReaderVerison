package Classes;

import java.util.ArrayList;

public class AuctionListing {

	private User seller;
	private Artwork artwork;
	private ArrayList<Bid> bids;
	private int maxBids;
	private String status = "Unlisted";
	private int reservePrice;
	private User winningBidder;
	private int winningPrice;
	
	public AuctionListing(User seller, Artwork artwork, int maxBids, int reservePrice) {
		this.seller = seller;
		this.artwork = artwork;
		this.maxBids = maxBids;
		this.reservePrice = reservePrice;
		this.bids = new ArrayList<Bid>();
	}

	public AuctionListing(User seller, Artwork artwork, int maxBids, int reservePrice, String currentStatus, ArrayList<Bid> currentBids
			, User currentWinner, int currentWinningPrice) {
		this.seller = seller;
		this.artwork = artwork;
		this.maxBids = maxBids;
		this.reservePrice = reservePrice;
		this.bids = currentBids;
		this.winningBidder = currentWinner;
		this.winningPrice = currentWinningPrice;
	}

	public Artwork getArtwork() {
		return artwork;
	}

	public int winningPrice() {
		return this.winningPrice;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public String getSellerUsername() {
		return this.seller.getUsername();
	}

	public void setArtwork(Artwork artwork) {
		this.artwork = artwork;
	}

	public void setMaxBids(int maxBids) {
		this.maxBids = maxBids;
	}

	public void setReservePrice(int reservePrice) {
		this.reservePrice = reservePrice;
	}

	public int reservePrice() {
		return this.reservePrice;
	}

	public String getArtworkTitle() {
		return artwork.getTitle();
	}

	/**
	 * @return the bids
	 */
	public ArrayList<Bid> getBids() {
		return bids;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the maxBids
	 */
	public int getMaxBids() {
		return maxBids;
	}

	public int getRemainingBids() {
		if (this.status == "Closed")
			return 0;

		int availableBids = maxBids;

		for (Bid b : bids) {
			if (b.getStatus() == "Accepted")
				availableBids--;
		}
		return availableBids;
	}

	/**
	 * @return the reservePrice
	 */
	public int getReservePrice() {
		return reservePrice;
	}

	public String processBid(Bid newbid) {
		// does this listing have any bid slots available?
		if (getRemainingBids() == 0) {
			newbid.setStatus("Ignored");
			return newbid.getStatus();
		}

		if (newbid.getBidder() == this.winningBidder) {
			newbid.setStatus("Rejected Heighest");
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

	public User getWinningBidder() {
		return this.winningBidder;
	}

	/**
	 * @return the winningPrice
	 */
	public int getWinningPrice() {
		return winningPrice;
	}
}
