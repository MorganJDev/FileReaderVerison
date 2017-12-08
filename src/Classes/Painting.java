package Classes;

import java.util.ArrayList;

/**
 * This class represents a single painting
 * @author Luke Thomas
 * @version 1.1
 */
public class Painting extends Artwork {
    private int width;
    private int height;

    /**
    * Creates a new painting with already specified information
    * @param title The title of the painting
    * @param desc A description of the painting
    * @param creator The name of the paintings creator
    * @param creationYear The year the painting was created
    * @param width The paintings width
    * @param height The paintings height
    */
    public Painting(String title, String desc, String creator, int creationYear,
                    ArrayList<String> photos, int width, int height, String type) {
        super(title,desc,creator,creationYear,photos,type);
        this.width = width;
        this.height = height;
    }

    /**
    * Get the width of the painting
    * @return The paintings width
    */
    public int getWidth() {
        return this.width;
    }

    /**
     * Set the width of the painting
     * @param width The paintings width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
    * Get the height of the painting
    * @return The paintings height
    */
    public int getHeight()
    {
        return this.height;
    }

    /**
     * Set the height of the painting
     * @param height The paintings height 
     */
    public void setHeight(int height)
    {
        this.height = height;
    }
    
    /**�
    * Converts a painting into a string�
    */
    public String toString() {
        return super.toString() + "Width: " + this.getWidth() + "\n" + "Height: " + this.getHeight() + "\n";
    }
}