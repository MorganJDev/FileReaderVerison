package Classes;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class stores information about a user object
 * @author Morgan Jones 904410
 * @version 1.1
 */
public class User
{
    private String forename;
    private String surname;
    private String username;
    private String telephoneNumber;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String county;
    private String postcode;
    private LocalDateTime lastLogin;
    private ArrayList<User> favouriteUsers;
    private String profileImage;

    /**
     * Creates new instance of user
     * @param forename Classes.User forename
     * @param surname Classes.User surname
     * @param username The username
     * @param phone Classes.User telephone number
     * @param add1 Address line 1
     * @param add2 Address line 2
     * @param city Classes.User city
     * @param county Classes.User county
     * @param post Classes.User postcode
     */
    public User (String forename, String surname, String username, String phone, String add1, String add2,
                 String city, String county, String post,LocalDateTime time)
    {
        this.forename = forename;
        this.surname = surname;
        telephoneNumber = phone;
        addressLineOne = add1;
        addressLineTwo = add2;
        this.city = city;
        this.county = county;
        postcode = post;
        this.lastLogin = time;
        favouriteUsers = new ArrayList<>();
        this.username = username;
        profileImage = "";
    }

    /**
     * Set the photo for this user
     * @param profileImage users photo
     */
    public void setProfileImage(String profileImage)
    {
        this.profileImage = profileImage;
    }

    /**
     * Get the users profile image
     * @return users photo
     */
    public String getProfileImage()
    {
        return profileImage;
    }

    /**
     * Sets the username
     * @param username The username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Get the users username
     * @return Users username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Sets users forename
     * @param forename Classes.User forename
     */
    public void setForename(String forename)
    {
        this.forename = forename;
    }

    /**
     * Gets the forename of this user
     * @return Classes.User forename
     */
    public String getForename()
    {
        return forename;
    }

    // Surname Set/Get

    /**
     * Sets users surname
     * @param surname Classes.User surname
     */
    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    /**
     * Gets this users surname
     * @return Classes.User surname
     */
    public String getSurname()
    {
        return surname;
    }

    // Telephone Set/Get

    /**
     * Sets the telephone number
     * @param telephoneNumber Classes.User telephone number
     */
    public void setTelephoneNumber(String telephoneNumber)
    {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Get this users telephone number
     * @return Classes.User telephone number
     */
    public String getTelephoneNumber()
    {
        return telephoneNumber;
    }

    /**
     * Sets line 1 of the address
     * @param addressLineOne The first line of the Address
     */
    public void setAddressLineOne(String addressLineOne)
    {
        this.addressLineOne = addressLineOne;
    }

    /**
     * Get the first line of the users address
     * @return The first line of the Address
     */
    public String getAddressLineOne()
    {
        return addressLineTwo;
    }

    /**
     * Set line 2 of the address
     * @param addressLineTwo The second line of the address
     */
    public void setAddressLineTwo(String addressLineTwo)
    {
        this.addressLineTwo = addressLineTwo;
    }

    /**
     * Get the second line of the users address
     * @return The second line of the address
     */
    public String getAddressLineTwo()
    {
        return addressLineOne;
    }

    /**
     * Sets the city in address
     * @param city The Users city
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Get the city the user lives in
     * @return The Users city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Sets the county in address
     * @param county The users county
     */
    public void setCounty(String county)
    {
        this.county = county;
    }

    /**
     * Get the users county
     * @return the county in address
     */
    public String getCounty()
    {
        return county;
    }

    /**
     * Sets the postocde in address
     * @param postcode The users postcode
     */
    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    /**
     * Get the users postcode
     * @return the postocde in address
     */
    public String getPostcode()
    {
        return postcode;
    }

    // Last login Set/Get

    /**
     * Records the users last login dateTime
     * @param lastLogin The dateTime of last login
     */
    public void setLastLogin(LocalDateTime lastLogin)
    {
        this.lastLogin = lastLogin;
    }

    /**
     * Get the last login date of this user
     * @return users last login
     */
    public LocalDateTime getLastLogin()
    {
        return lastLogin;
    }

    // Favourite Users

    /**
     * Adds a user to this users list of favourites
     * @param favouriteUser A user object
     */
    public void addFavouriteUser(User favouriteUser)
    {
        this.favouriteUsers.add(favouriteUser);
    }

    /**
     * Get this users favourited users
     * @return a list of favourites
     */
    public ArrayList<User> getFavouriteUsers()
    {
        return favouriteUsers;
    }
}
