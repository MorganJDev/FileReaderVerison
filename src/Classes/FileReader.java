package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used to read files storing information on users, favourites and auctions on the system
 * so that the system can reload information from the last time it was used
 * @author Georgi Georgiev
 * @author Luke Thomas 905557
 */
public class FileReader {

	/**
	 * Method used to read a single user from the input file
	 * @param line Line containing information about one user
	 * @return The user that is read in
	 */
    private static User newUser (String line) {
        Scanner lineScanner = new Scanner(line);
        lineScanner.useDelimiter("%");
        String forename = lineScanner.next();
        String surname = lineScanner.next();
        String username = lineScanner.next();
        String telephoneNumber = lineScanner.next();
        String addressLineOne = lineScanner.next();
        String addressLineTwo = lineScanner.next();
        String city = lineScanner.next();
        String county = lineScanner.next();
        String postcode = lineScanner.next();
        String lastLogin = lineScanner.next();
        LocalDateTime lastLoginDate = LocalDateTime.parse(lastLogin);
        
        User user = new User (forename, surname, username, telephoneNumber, 
            addressLineOne, addressLineTwo, city, county, postcode, lastLoginDate);

		if (lineScanner.hasNext())
		{
			String profileImage = lineScanner.next();
			user.setProfileImage(profileImage);
			profileImage = "";
		}

        lineScanner.close();

        return user;
    }

	/**
	 * Method used to read a single auction from the input file
	 * @param line Line containing information about one auction
	 * @param um The usermanager containing all of the users stored on the system
	 * @return The auction that is read in
	 */
    private static AuctionListing newListing(String line, UserManager um) {
        AuctionListing newListing;
    	ArrayList<String> images = new ArrayList<String>();

    	//First the information of the artwork associated with this auction is read in
    	Artwork artwork = null;
    	Scanner lineScanner = new Scanner(line);
        lineScanner.useDelimiter("%");
    	String type = lineScanner.next();
    	String title = lineScanner.next();
		String desc = lineScanner.next();
		String creator = lineScanner.next();
		int creationYear = lineScanner.nextInt();

		/*If the artwork is a painting then read in the additional
		information associated with a painting*/
    	if (type.equals("painting")) {
			while (!lineScanner.hasNextInt()) {
				String image = lineScanner.next();
				images.add(image);
			}
    		int width = lineScanner.nextInt();
    		int height = lineScanner.nextInt();
    		Painting painting = new Painting(title,desc,creator,creationYear,images,width,height);
    		artwork = painting;

    	//Else, do the same but for sculpture
    	} else if (type.equals("sculpture")) {

    		/*Because width comes after an artworks photos in the input file, as long as next is not an
			int we know that it must be a photo*/
    		while (!lineScanner.hasNextInt()) {
    			String image = lineScanner.next();
    			images.add(image);
    		}
    		int width = lineScanner.nextInt();
    		int height = lineScanner.nextInt();
    		int depth = lineScanner.nextInt();
    		String material = lineScanner.next();
    		Sculpture sculpture = new Sculpture(title,desc,creator,creationYear,images,width,height,depth,material);
    		artwork = sculpture;
    	}

    	/*Next we read in the auction listing information, because we read in users before
    	* auctions, we can just store the username of the seller and current winner and just compare
    	* them with all of the stored users to find the correct one for each*/
    	String sellerName = lineScanner.next();
    	for(User u : um.getAllUsers()) {
    		if (u.getUsername().equals(sellerName)) {
    			User seller = u;
    			int maxbids = lineScanner.nextInt();
    	    	int reserve = lineScanner.nextInt();
				String status = lineScanner.next();
				String winnerName = lineScanner.next();
				for(User z : um.getAllUsers()) {
					if (z.getUsername().equals(winnerName)) {
						User winner = z;
						int winPrice = lineScanner.nextInt();

						/*After reading in the auction listing information, we need to read in all of
						the bids placed on this auction listing*/
						if (lineScanner.hasNext()) {
							String bidString = lineScanner.next();
							Scanner allBids = new Scanner(bidString);
    	    				ArrayList<Bid> bids = newBid(allBids, um);
    	    				AuctionListing auction = new AuctionListing(seller, artwork, maxbids, reserve, status, bids, winner, winPrice);
    	    				for (Bid b : auction.getBids()) {
    	    					b.setListing(auction);
							}
    	    				lineScanner.close();
							return auction;

						//If an auction listing has no bids yet, we use the alternative constructor to read it in
    	    			} else {
							AuctionListing auction = new AuctionListing(seller, artwork, maxbids,reserve);
							auction.setStatus(status);
							lineScanner.close();
							return auction;
						}
    	    		}
    	    	}
    		}
    	}
    	lineScanner.close();
    	return null;
    }

	/**
	 * Method used to read in a bid placed on an auction listing
	 * @param lineScanner Line containing information about a single bid
	 * @param um The userManager containing all of the users on the system
	 * @return The bid that is read in
	 */
	private static ArrayList<Bid> newBid (Scanner lineScanner, UserManager um) {
    	lineScanner.useDelimiter(";");
		ArrayList<Bid> bidlist = new ArrayList<Bid>();
		while(lineScanner.hasNext()) {
			String bidderName = lineScanner.next();
			for(User u : um.getAllUsers()) {
	    		if (u.getUsername().equals(bidderName)) {
	    			User bidder = u;
	    			int amount = lineScanner.nextInt();
	    			String status = lineScanner.next();
	    			String bidPlaced = lineScanner.next();
	    	        LocalDateTime bidPlacedDate = LocalDateTime.parse(bidPlaced);
	    	        Bid bid = new Bid(bidder,amount,status,bidPlacedDate);
	    	        bidlist.add(bid);
	    		}
	    	}
		}
		return bidlist;
	}

	/**
	 * Method used to add a user to another users favourite list
	 * @param in Line containing information about a favourite relationship
	 * @param allUsers List of all users on the system
	 */
    private static void addFavourites(Scanner in, ArrayList<User> allUsers) {
    	while (in.hasNext()) {
    		String nextLine = in.nextLine ();
			Scanner lineScanner = new Scanner (nextLine);
			lineScanner.useDelimiter(",");
			String profile1 = lineScanner.next();
			String profile2 = lineScanner.next();
			for (User u : allUsers) {
				if(u.getUsername().equals(profile1)) {
					for (User z : allUsers) {
						if(z.getUsername().equals(profile2) && !z.getUsername().equals(profile1)) {
							u.addFavouriteUser(z);
						}
					}
				}
			}
			lineScanner.close();
    	}
    }

	/**
	 * Method used to read a file made up of favourite relationships between users
	 * @param filename the name of the file
	 * @param um the usermanager containing all the users
	 */
	public static void setFavouriteUsers(String filename, UserManager um) {
    	File inputFile = new File (filename);
    	Scanner in = null;
    	try {
    		in = new Scanner (inputFile);
    	}
    	catch (FileNotFoundException e) {
    		System.out.println(filename + " file not found");
    		System.exit (0);
    	}
    	addFavourites(in, um.getAllUsers());
    }

	/**
	 * Method used to read the data file used by the program for storing users
	 * @param in The scanner of the file
	 * @return List of all the users read from the file
	 */
	private static ArrayList<User> readUserDataFile (Scanner in) {
		ArrayList<User> allUsers = new ArrayList<User>();
		while (in.hasNext()) {
				String nextLine = in.nextLine();
				allUsers.add(newUser(nextLine));
		}
		in.close();
		return allUsers;
	}

	/**
	 * Method used to read the data file used by the program for storing auctions
	 * @param in The scanner of the file
	 * @param um The user manager containing all users stored in the system
	 * @return List of all the auctions read from the file
	 */
	private static ArrayList<AuctionListing> readAuctionDataFile (Scanner in, UserManager um) {
		ArrayList<AuctionListing> allAuctions = new ArrayList<AuctionListing>();
		while (in.hasNext()) {
				String nextLine = in.nextLine();
				allAuctions.add(newListing(nextLine, um));
		}
		in.close();
		return allAuctions;
	}

	/**
	 * This method is used to read a file made up of users
	 * @param filename the name of the file
	 * @return the list of users stored in the file
	 */
	public static ArrayList<User> readUsers (String filename) {
		File inputFile = new File (filename);
		Scanner in = null;
		try {
			in = new Scanner (inputFile);
		}
		catch (FileNotFoundException e) {
			System.out.println(filename + " file not found");
			System.exit (0);
		}
		return FileReader.readUserDataFile(in);
	}

	/**
	 * This method is used to read a file made up of auctions
	 * @param filename the name of the file
	 * @param um the user manager storing all of the users on the system
	 * @return the list of users stored in the file
	 */
	public static ArrayList<AuctionListing> readAuctions (String filename, UserManager um) {
		File inputFile = new File (filename);
		Scanner in = null;
		try {
			in = new Scanner (inputFile);
		}
		catch (FileNotFoundException e) {
			System.out.println(filename + " file not found");
			System.exit (0);
		}
		return FileReader.readAuctionDataFile(in,um);
	}
}
