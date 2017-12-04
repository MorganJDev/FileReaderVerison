package Classes;

import java.util.ArrayList;

/**
 * This class represents a single artwork
 * @author Luke Thomas
 * @version 1.1
 */
public class Artwork {
    private String title;
    private String description;
    private String creatorName;
    private int artCreationYear;
    private ArrayList<String> photos;

    /**
    * Creates a new artwork with already specified information
    * @param title The title of the artwork
    * @param desc A description of the artwork
    * @param creator The name of the artworks creator
    * @param creationYear The year the artwork was created
    * @param photo Images of the artwork
    */
    public Artwork(String title, String desc, String creator, int creationYear, ArrayList<String> photos) {
        this.title = title;
        this.description = desc;
        this.creatorName = creator;
        this.artCreationYear = creationYear;
        this.photos = photos;
    }

    /**
    * Get the title of the artwork
    * @return The artwork title
    */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets title of artwork
     * @param title The artwork title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
    * Get the description of the artwork
    * @return The artwork description
    */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the artwork
     * @param descriptionThe artwork description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
    * Get the name of the artworks creator
    * @return The artwork creators name
    */
    public String getCreatorName() {
        return this.creatorName;
    }

    /**
     * Sets the name of the artworks creator
     * @param creatorName The artwork creators name
     */
    public void setCreatorName(String creatorName)
    {
        this.creatorName = creatorName;
    }

    /**
    * Get the year the artwork was created
    * @return The artworks year of creation
    */
    public int getCreationYear() {
        return this.artCreationYear;
    }

    /**
     * Sets the year the artwork was created
     * @param artCreationYear The artworks year of creation
     */
    public void setArtCreationYear(int artCreationYear)
    {
        this.artCreationYear = artCreationYear;
    }

    /** 
     * Get images of the artworkÂ 
     * @return The artworks photosÂ 
     */
    public ArrayList<String> getPhotos() {
        return this.photos;
    }

    /**
     * Sets the images of the artwork
     * @param photos The artworks photos 
     */
    public void setPhotos(ArrayList<String> photos)
    {
        this.photos = photos;
    }
    
    /** 
    * Converts an artwork into a string
    */
    public String toString() {
        return "Title: " + this.getTitle() + "\n" + "Description: " + this.getDescription() + "\n"
            + "Creator: " + this.getCreatorName() + "\n" + "Year created: " + this.getCreationYear() + "\n";
    }
}
