package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class is used to write out all of the information on the system to an output file
 * @author Luke Thomas 905557
 */
public class FileWriter {

	/**
	 * Method used to write out all of the users in the system to an output file
	 * @param filename name of the output file
	 * @param um the user manager storing all of the users in the system
	 */
	public static void writeUsers (String filename, UserManager um) {
		File outputFile = new File (filename);
		PrintWriter out = null;
		String profileImage = "%";
		try {
			out = new PrintWriter (outputFile);
		}
		catch (FileNotFoundException e) {
			System.out.println(filename + " file not found");
			System.exit (0);
		}
		for (User u : um.getAllUsers()) {
			if(!(u.getProfileImage().equals(""))) {
				profileImage += u.getProfileImage();
			}
			out.println(u.getForename() + "%" + u.getSurname() + "%" + u.getUsername() + "%" +
					u.getTelephoneNumber() + "%" + u.getAddressLineOne() + "%" + u.getAddressLineTwo() + "%" +
					u.getCity() + "%" + u.getCounty() + "%" + u.getPostcode() + "%" + u.getLastLogin() +
					profileImage);
			profileImage = "%";
		}
		out.close();
	}

	/**
	 * Method used to write out all of the favourite relationships between users in the system to an output file
	 * @param filename name of the output file
	 * @param um the user manager storing all of the users in the system
	 */
	public static void writeFavouriteUsers (String filename, UserManager um) {
		File outputFile = new File (filename);
		PrintWriter out = null;
		try {
			out = new PrintWriter (outputFile);
		}
		catch (FileNotFoundException e) {
			System.out.println(filename + " file not found");
			System.exit (0);
		}
		for (User u : um.getAllUsers()) {
			for (User u2 : u.getFavouriteUsers()) {
				out.println(u.getUsername() + "," + u2.getUsername());
			}
		}
		out.close();
	}

	/**
	 * Method used to write out all of the auctions in the system to an output file
	 * @param filename name of the output file
	 * @param a the auctioneer storing all of the auctions in the system
	 */
	public static void writeAuctions (String filename, Auctioneer a) {
		File outputFile = new File (filename);
		PrintWriter out = null;
		try {
			out = new PrintWriter (outputFile);
		}
		catch (FileNotFoundException e) {
			System.out.println(filename + " file not found");
			System.exit (0);
		}
		//First we write out the information about the artwork associated with the auction
		for (AuctionListing al : a.getAuctionListings()) {
			Artwork art = al.getArtwork();

			//If the artwork is a painting we write out it's type so file reader can identify it later
			if (art instanceof Painting) {
				out.print("painting%");
				Painting paint = (Painting) art;
				out.print(paint.getTitle() + "%" + paint.getDescription() + "%" +
						paint.getCreatorName() + "%" + paint.getCreationYear() + "%");
						if (paint.getPhotos() != null)
						{
							out.print(paint.getPhotos().get(0) + "%");
						}
						out.print(paint.getWidth() + "%" + paint.getHeight() + "%");

			//Same for sculpture
			} else if (al.getArtwork() instanceof Sculpture) {
				out.print("sculpture%");
				Sculpture sculpt = (Sculpture) art;
				out.print(sculpt.getTitle() + "%" + sculpt.getDescription() + "%" +
						sculpt.getCreatorName() + "%" + sculpt.getCreationYear() + "%");
				if (sculpt.getPhotos() != null)
				{
					for (String s : sculpt.getPhotos()) {
						out.print(s + "%");
					}
				}
				out.print(sculpt.getWidth() + "%" + sculpt.getHeight() + "%" +
						sculpt.getDepth() + "%" + sculpt.getMaterial() + "%");
			}

			//Then we write out all of the auction listings details
			out.print(al.getSellerUsername() + "%" + al.getMaxBids() + "%" + al.getReservePrice() + "%" +
				al.getStatus() + "%" + al.getWinningBidder().getUsername() + "%" + al.getWinningPrice() + "%");

			//And finally for each bid we write out it's details
			if (al.getBids().size() != 0) {
				for (Bid b : al.getBids()) {
					out.print(b.getBidder().getUsername() + ";" + b.getAmount() + ";" +
							b.getStatus() + ";" + b.getBidPlaced() + ";");
				}
			}
			out.println("");
		}

		out.close();
	}

}
