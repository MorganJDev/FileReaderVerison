package Classes;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class draws a custom canvas 
 * @author Georgi
 * @version 1.1
 */
public class Custom {

    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int colour;

    /**
    * Creates a new canvas
    * @param x1 The starting point of x
    * @param x2 The ending point of x
    * @param y1 The starting point of y
    * @param y2 The ending point of y
    * @param colour The colour of the canvas
    */
    public Custom(int x1, int y1, int x2, int y2, int colour) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.colour = colour;
    }

    /**
     * @return the x1
     */
    public int getX1() {
        return x1;
    }

    /**
     * @param x1 the x1 to set
     */
    public void setX1(int x1) {
        this.x1 = x1;
    }

    /**
     * @return the y1
     */
    public int getY1() {
        return y1;
    }

    /**
     * @param y1 the y1 to set
     */
    public void setY1(int y1) {
        this.y1 = y1;
    }

    /**
     * @return the x2
     */
    public int getX2() {
        return x2;
    }

    /**
     * @param x2 the x2 to set
     */
    public void setX2(int x2) {
        this.x2 = x2;
    }

    /**
     * @return the y2
     */
    public int getY2() {
        return y2;
    }

    /**
     * @param y2 the y2 to set
     */
    public void setY2(int y2) {
        this.y2 = y2;
    }

    /**
     * @return the colour
     */
    public int getColour() {
        return colour;
    }

    /**
     * @param colour the colour to set
     */
    public void setColour(int colour) {
        this.colour = colour;
    }
    
    /**
     * Converts a canvas into a string
     */
    @Override
    public String toString(){
        return toString() + "Starting point: " + this.getX1() + "\n" 
                + this.getY1() + "\n" + "Ending point: " + this.getX2() + "\n"
                + this.getY2() + "\n" + "Colour: " + this.getColour();
    }
}
