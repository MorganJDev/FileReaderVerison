package Classes;

import UI.Main;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.*;

/**
 * This class manages methods to manage login and register.
 * It also stores every unique instance of Classes.User
 *
 * @author Morgan Jones 904410
 * @version 1.0
 */
public class UserManager
{
    private User currentUser;
    private ArrayList<User> allUsers;
    private ArrayList<Artwork> artworks;

    /**
     * This creates a new instance of user manager
     */
    public UserManager()
    {
        currentUser = null;
        allUsers = new ArrayList<>();
        artworks = new ArrayList<>();
    }

    public void addArtworks(Artwork artwork)
    {
        artworks.add(artwork);
    }

    public ArrayList<Artwork> getArtworks()
    {
        return artworks;
    }

    public void setCurrentUser(User user)
    {
        currentUser = user;
    }

    // Current user Set/Get

    /**
     * @return The current user
     */
    public User getCurrentUser()
    {
        return currentUser;
    }

    /**
     * This compares a String input with the Classes.User list to store the
     * current user as an object
     * @param username The inputted username
     */
    public void login(String username)
    {
        for (int i = 0; i <= (allUsers.size() - 1); i++) {
            if (username.equals(allUsers.get(i).getUsername())) {
                currentUser = allUsers.get(i);
            }
        }
    }

    // All users list Set/Get

    /**
     * This adds a new user to the list
     * @param newUser The new user
     */
    public void registerUser(User newUser)
    {
        allUsers.add(newUser);
        //writeToFile(newUser);
    }

    /**
     * @return The list of allUsers
     */
    public ArrayList<User> getAllUsers()
    {
        return allUsers;
    }

    // Possible method

    /**
     * Method to populate array with all users in file
     *
     */
    public void populateArray()
    {
        this.allUsers = (FileReader.readUsers("users.txt"));
        FileReader.setFavouriteUsers("favouriteUsers.txt",this);
    }

    public void writeFiles()
    {
        FileWriter.writeUsers("users.txt",this);
        FileWriter.writeFavouriteUsers("favouriteUsers.txt",this);
    }
}
