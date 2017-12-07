package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Georgi Georgiev
 */
public class FileReader {

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
    //
    private static AuctionListing newListing(String line, UserManager um) {
        AuctionListing newListing;
    	ArrayList<String> images = new ArrayList<String>();
    	Artwork artwork = null;
    	Scanner lineScanner = new Scanner(line);
        lineScanner.useDelimiter("%");
    	String type = lineScanner.next();
    	String title = lineScanner.next();
		String desc = lineScanner.next();
		String creator = lineScanner.next();
		int creationYear = lineScanner.nextInt();
    	if (type.equals("painting")) {
			while (!lineScanner.hasNextInt()) {
				String image = lineScanner.next();
				images.add(image);
			}
    		int width = lineScanner.nextInt();
    		int height = lineScanner.nextInt();
    		Painting painting = new Painting(title,desc,creator,creationYear,images,width,height);
    		artwork = painting;
    	} else if (type.equals("sculpture")) {
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
    	for(User u : um.getAllUsers()) {
    		if (u.getUsername().equals(lineScanner.next())) {
    			User seller = u;
    			int maxbids = lineScanner.nextInt();
    	    	int reserve = lineScanner.nextInt();
				String status = lineScanner.next();
				for(User z : um.getAllUsers()) {
					if (z.getUsername().equals(lineScanner.next())) {
						User winner = z;
						int winPrice = lineScanner.nextInt();
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
    
    private static ArrayList<Bid> newBid (Scanner lineScanner, UserManager um) {
    	lineScanner.useDelimiter(";");
		ArrayList<Bid> bidlist = new ArrayList<Bid>();
		while(lineScanner.hasNext()) {
			for(User u : um.getAllUsers()) {
	    		if (u.getUsername().equals(lineScanner.next())) {
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
    
	private static ArrayList<User> readUserDataFile (Scanner in) {
		ArrayList<User> allUsers = new ArrayList<User>();
		while (in.hasNext()) {
				String nextLine = in.nextLine();
				allUsers.add(newUser(nextLine));
		}
		in.close();
		return allUsers;
	}
	
	private static ArrayList<AuctionListing> readAuctionDataFile (Scanner in, UserManager um) {
		ArrayList<AuctionListing> allAuctions = new ArrayList<AuctionListing>();
		while (in.hasNext()) {
				String nextLine = in.nextLine();
				allAuctions.add(newListing(nextLine, um));
		}
		in.close();
		return allAuctions;
	}
	
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
