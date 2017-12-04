package Classes; 

/**
 * A class to model a bid on an auction listing
 * @author Keoni D'Souza 921231
 * @version 1.0
 */

import java.time.LocalDateTime;


public class Bid {

	private User bidder;
	private int amount;
	private String status;
	private LocalDateTime bidPlaced;

	/**
	 * Constructor method
	 * @param bidder The Classes.User who is placing a bid
	 * @param amount The price the bidder is placing
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
	 * @return The bidder
	 */
	public User getBidder() {
		return bidder;
	}

	/**
	 * @return The amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @return The time that the bid was placed (bidPlaced)
	 */
	public LocalDateTime getBidPlaced() {
		return bidPlaced;
	}
	
	public String getStatus() {
		return this.status;
	}
	
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
