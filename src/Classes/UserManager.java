package Classes;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

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
    public User currentUser;
    public ArrayList<User> allUsers;

    /**
     * This creates a new instance of user manager
     */
    public UserManager()
    {
        currentUser = null;
        allUsers = new ArrayList<>();
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
     * @param filename The file of users
     */
    public void populateArray(String filename)
    {
        //readFile(filename);
    }
}
