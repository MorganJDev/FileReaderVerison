package Classes;

import java.util.ArrayList;
/**
 * This class represents a single sculpture
 * @author Luke Thomas
 * @version 1.1
 */
public class Sculpture extends Artwork {
    private int width;
    private int height;
    private int depth;
    private String material;

    /**
    * Creates a new sculpture with already specified information
    * @param title The title of the sculpture
    * @param desc A description of the sculpture
    * @param creator The name of the sculptures creator
    * @param creationYear The year the sculpture was created
    * @param photo The images of the sculpture
    * @param width The sculptures width
    * @param height The sculptures height
    * @param depth The sculptures depth
    * @param material The main material of the sculpture
    */
    public Sculpture (String title, String desc, String creator, int creationYear, ArrayList<String> photos,
                      int width, int height, int depth, String material) {
        super(title,desc,creator,creationYear,photos);
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.material = material;
    }

    /**
    * Get the width of the sculpture
    * @return The sculptures width
    */
    public int getWidth() {
        return this.width;
    }

    /**
     * Set the width of the sculpture
     * @param width The sculptures width
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
    * Get the height of the sculpture
    * @return The sculptures height
    */
    public int getHeight() {
        return this.height;
    }

    /**
     * Set the height of the sculpture
     * @param height The sculptures height
     */
    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
    * Get the depth of the sculpture
    * @return The sculptures depth
    */
    public int getDepth() {
        return this.depth;
    }

    /**
     * Set the depth of the sculpture
     * @param depth The sculptures depth
     */
    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    /**
    * Get the main material of the sculpture
    * @return The sculptures main material
    */
    public String getMaterial() {
        return this.material;
    }

    /**
     * Set the main material of the sculpture
     * @param material The sculptures main material
     */
    public void setMaterial(String material)
    {
        this.material = material;
    }
    
    /**�
     * Converts a sculpture into a string�
     */
    public String toString() {
         return super.toString() + "Width: " + this.getWidth() + "\n" + "Height: " + this.getHeight() + "\n"
             + "Depth: " + this.getDepth() + "\n" + "Material: " + this.getMaterial() + "\n";
     }
}
