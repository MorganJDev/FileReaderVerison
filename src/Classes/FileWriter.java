package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
//
public class FileWriter {
	
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
				profileImage = u.getProfileImage();
			}
			out.println(u.getForename() + "%" + u.getSurname() + "%" + u.getUsername() + "%" +
					u.getTelephoneNumber() + "%" + u.getAddressLineOne() + "%" + u.getAddressLineTwo() + "%" +
					u.getCity() + "%" + u.getCounty() + "%" + u.getPostcode() + "%" + u.getLastLogin() +
					profileImage);
			profileImage = "%";
		}
		out.close();
	}
	
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
		for (AuctionListing al : a.getAuctionListings()) {
			Artwork art = al.getArtwork();
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
			out.print(al.getSellerUsername() + "%" + al.getMaxBids() + "%" + al.getReservePrice() + "%");
			if (al.getBids().size() != 0) {
				out.print(al.getWinningBidder().getUsername() + "%" + al.getStatus() + "%" +
						al.getWinningPrice() + "%");
				for (Bid b : al.getBids()) {
					out.print(b.getBidder().getUsername() + ";" + b.getAmount() + ";" +
							b.getStatus() + ";" + b.getBidPlaced());
				}
			}
			out.println("");
		}

		out.close();
	}

}
