
package UI;

import Classes.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
/**
 * This class handles the interface for creating a custom drawn image.
 * It allows the user to free draw and draw coloured lines/shapes.
 * It also saves the drawing to their profile image
 *
 * @author Georgi Georgiev [student number]
 * @version 1.0
 */

public class CanvasController
{
    // Constants for canvas dimensions + shapes
    private final int CIRCLE_DIMENSIONS = 25;
    private final int RECTANGLE_X = 30;
    private final int RECTANGLE_Y = 20;
    private final int CURSOR_X = 185;
    private final int CURSOR_Y = 70;
    private final int SHAPE_X = 197;
    private final int SHAPE_Y = 77;

    // GUI references
    @FXML javafx.scene.canvas.Canvas canvasPane;
    @FXML javafx.scene.control.ColorPicker colorPicker;
    @FXML CheckBox fillCheckBox;

    /**
     * This method closes the window
     */
    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }

    /**
     * This method converts the canvas pane and all the objects (lines, circles etc) into
     * an Image object. It then saves the image as a png file inside the resources folder and
     * writes said file path into the user object
     *
     * @throws Exception If file is not found
     */
    @FXML
    private void handleSave() throws Exception
    {
        FileChooser fileC = new FileChooser();

        // Extension
        FileChooser.ExtensionFilter extension =
                new FileChooser.ExtensionFilter("png files (*png)", "*png");
        fileC.getExtensionFilters().add(extension);

        // Set file path
        File file = new File("AllImages/CustomImages/" + Main.admin.getCurrentUser().getUsername() + ".png");

        if(file != null)
        {
            try
            {
                WritableImage newWI = new WritableImage((int) canvasPane.getWidth(), (int) canvasPane.getHeight());
                canvasPane.snapshot(null, newWI);
                RenderedImage newRI = SwingFXUtils.fromFXImage(newWI, null);
                ImageIO.write(newRI, "png", file);

                findUser().setProfileImage("/CustomImages/" + Main.admin.getCurrentUser().getUsername() + ".png");

                reloadMain();
            }
            catch (IOException ex)
            {
                System.out.println("Error saving");
            }
        }
    }

    /**
     * This method closes the window and reloads the main menu with all its new data
     *
     * @throws Exception If file is not found
     */
    private void reloadMain() throws Exception
    {
        // Sets a Main static variable that's referenced on MainMenu initialisation
        Main.reloadedOn = "Profile";

        Parent current = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

        Main.window.close();
        Main.window.setScene(new Scene(current));
        Main.window.show();

        Main.window.setOnCloseRequest(e -> Main.closeApplication());

        Main.popup.close();
    }

    /**
     * This method finds the current user from the static list of all users
     *
     * @return The user object
     */
    private User findUser() {
        for (User i : Main.admin.getAllUsers()) {
            if (i.getUsername().equals(Main.admin.getCurrentUser().getUsername())) {
                return i;
            }
        }

        return null;
    }

    /**
     * This method clears all the mouse click events for the canvas pane.
     * This is so circles are not still drawn when line is clicked etc
     */
    private void resetDraw()
    {
        canvasPane.setOnMousePressed(null);
        canvasPane.setOnMouseReleased(null);
        canvasPane.setOnMouseDragged(null);
    }

    /**
     * This calls the free draw method
     * @see #drawBrush()
     */
    @FXML
    private void handleBrushClick()
    {
        drawBrush();
    }

    /**
     * This calls the draw line method
     * @see #drawLine()
     */
    @FXML
    private void handleLineClick()
    {
        drawLine();
    }

    /**
     * This calls the draw circle method
     * @see #drawCircle()
     */
    @FXML
    private void handleCircleClick()
    {
        drawCircle();
    }

    /**
     * This calls the draw rectangle method
     * @see #drawRectangle()
     */
    @FXML
    private void handleRectangleClick(){
        drawRectangle();
    }

    /**
     * This method controls the canvas onClick and onDrag methods
     * It sets the position and size of the a circle shape using
     * values stored in constant variables
     */
    private void drawCircle(){

        resetDraw();

        GraphicsContext circle = canvasPane.getGraphicsContext2D();

        canvasPane.setOnMousePressed(e -> {

            if (fillCheckBox.isSelected()) {
                circle.fillOval(e.getSceneX() - SHAPE_X, e.getSceneY() - SHAPE_Y,
                        CIRCLE_DIMENSIONS, CIRCLE_DIMENSIONS);
            } else {
                circle.strokeOval(e.getSceneX() - SHAPE_X, e.getSceneY() - SHAPE_Y,
                        CIRCLE_DIMENSIONS, CIRCLE_DIMENSIONS);
            }
        });

        colorPicker.setValue(Color.BLACK);
        colorPicker.setOnAction(e -> {
            if (fillCheckBox.isSelected()) {
                circle.setFill(colorPicker.getValue());
            } else {
                circle.setStroke((colorPicker.getValue()));
            }
        });
    }

    /**
     * This method controls the canvas onClick and onDrag methods
     * It sets the position and size of the a line shape using
     * values stored in constant variables
     */
    private void drawLine(){

        resetDraw();

        GraphicsContext gc = canvasPane.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        canvasPane.setOnMousePressed(e -> {
            gc.beginPath();
            gc.lineTo(e.getSceneX() - CURSOR_X, e.getSceneY() - CURSOR_Y);
            gc.stroke();
        });

        canvasPane.setOnMouseReleased(e -> {
            gc.lineTo(e.getSceneX()- CURSOR_X, e.getSceneY() - CURSOR_Y);
            gc.stroke();
        });

        colorPicker.setValue(Color.BLACK);
        colorPicker.setOnAction(e -> {
            gc.setStroke(colorPicker.getValue());
        });
    }

    /**
     * This method controls the canvas onClick and onDrag methods
     * It allows a continuous line to be drawn free handed
     * values stored in constant variables
     */
    private void drawBrush() {

        resetDraw();

        GraphicsContext gc = canvasPane.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        canvasPane.setOnMousePressed(e -> {
            gc.beginPath();
            gc.lineTo(e.getSceneX() - CURSOR_X, e.getSceneY() - CURSOR_Y);
            gc.stroke();
        });

        canvasPane.setOnMouseDragged(e -> {
            gc.lineTo(e.getSceneX() - CURSOR_X, e.getSceneY() - CURSOR_Y);
            gc.stroke();
        });

        colorPicker.setValue(Color.BLACK);
        colorPicker.setOnAction(e -> {
            gc.setStroke(colorPicker.getValue());
        });
    }

    /**
     * This method controls the canvas onClick and onDrag methods
     * It sets the position and size of the a rectangle shape using
     * values stored in constant variables
     */
    private void drawRectangle(){

        resetDraw();

        GraphicsContext rect = canvasPane.getGraphicsContext2D();
        canvasPane.setOnMousePressed(e -> {
            if (fillCheckBox.isSelected()) {
                rect.fillRect(e.getSceneX() - SHAPE_X, e.getSceneY() - SHAPE_Y, RECTANGLE_X, RECTANGLE_Y);
            } else {
                rect.strokeRect(e.getSceneX() - SHAPE_X, e.getSceneY() - SHAPE_Y, RECTANGLE_X, RECTANGLE_Y);
            }
        });

        colorPicker.setValue(Color.BLACK);
        colorPicker.setOnAction(e -> {
            if (fillCheckBox.isSelected()) {
                rect.setFill(colorPicker.getValue());
            } else {
                rect.setStroke((colorPicker.getValue()));
            }
        });
    }
}
