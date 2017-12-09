
package UI;

import Classes.Custom;
import Classes.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.ColorPicker;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.Canvas;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.awt.image.renderable.RenderableImageOp;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Georgi  Georgiev
 */

public class CanvasController
{
    private final int CIRCLE_DIMENSIONS = 25;
    private final int RECTANGLE_X = 30;
    private final int RECTANGLE_Y = 20;
    private final int CURSOR_X = 185;
    private final int CURSOR_Y = 70;
    private final int SHAPE_X = 197;
    private final int SHAPE_Y = 77;

    @FXML
    javafx.scene.canvas.Canvas canvasPane;
    @FXML
    javafx.scene.control.ColorPicker colorPicker;
    @FXML
    CheckBox fillCheckBox;

    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }

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

    private User findUser() {
        for (User i : Main.admin.getAllUsers()) {
            if (i.getUsername().equals(Main.admin.getCurrentUser().getUsername())) {
                return i;
            }
        }

        return null;
    }

    private void resetDraw()
    {
        canvasPane.setOnMousePressed(null);
        canvasPane.setOnMouseReleased(null);
        canvasPane.setOnMouseDragged(null);
    }

    @FXML
    private void handleBrushClick()
    {
        drawBrush();
    }

    @FXML
    private void handleLineClick()
    {
        drawLine();
    }

    @FXML
    private void handleCircleClick()
    {
        drawCircle();
    }

    @FXML
    private void handleRectangleClick(){
        drawRectangle();
    }

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
