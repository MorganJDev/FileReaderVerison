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

    /**
     * This creates a new instance of user manager
     */
    public UserManager()
    {
        currentUser = null;
        allUsers = new ArrayList<>();
    }

    /**
     * Set the user currently logged in to the system
     * @param user Currently logged in user
     */
    public void setCurrentUser(User user)
    {
        currentUser = user;
    }

    /**
     * Get the user who is currently logged in
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
     * Get a list of all users on the system
     * @return The list of all users
     */
    public ArrayList<User> getAllUsers()
    {
        return allUsers;
    }

    // Possible method

    /**
     * Method to populate array with all users in file, also
     * populates the favourite list for every user
     */
    public void populateArray()
    {
        this.allUsers = (FileReader.readUsers("users.txt"));
        FileReader.setFavouriteUsers("favouriteUsers.txt",this);
    }

    /**
     * Writes all of the users stored on the system to an output file,
     * also writes all favourited users to a different file
     */
    public void writeFiles()
    {
        FileWriter.writeUsers("users.txt",this);
        FileWriter.writeFavouriteUsers("favouriteUsers.txt",this);
    }
}
