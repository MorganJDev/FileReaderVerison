
package UI;

import Classes.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.ColorPicker;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.WritableImage;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.awt.Canvas;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.renderable.RenderableImage;
import java.awt.image.renderable.RenderableImageOp;

/**
 * @author Georgi  Georgiev
 */

public class CanvasController
{
    @FXML javafx.scene.canvas.Canvas canvasPane;
    @FXML javafx.scene.control.ColorPicker colorPicker;
    @FXML
    CheckBox fillCheckBox;
    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }

    @FXML
    private void handleSave()
    {
        WritableImage newWI = new WritableImage((int) canvasPane.getWidth() + 20,
                (int) canvasPane.getHeight() + 20);

        canvasPane.snapshot(null,newWI);
        RenderableImage newRI = (RenderableImage) SwingFXUtils.fromFXImage(newWI,null);

        //ImageIO.write(newRI,"png",filePath);


       // findUser().setProfileImage
    }

    private User findUser() {
        for (User i : Main.admin.getAllUsers()) {
            if (i.getUsername().equals(Main.admin.getCurrentUser().getUsername())) {
                return i;
            }
        }

        return null;
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
                circle.fillOval(e.getSceneX() - 197, e.getSceneY() - 77, 25, 25);
            } else {
                circle.strokeOval(e.getSceneX() - 197, e.getSceneY() - 77, 25, 25);
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

    private void resetDraw()
    {
        canvasPane.setOnMousePressed(null);
        canvasPane.setOnMouseReleased(null);
        canvasPane.setOnMouseDragged(null);
    }

    private void drawLine(){

        resetDraw();

        GraphicsContext gc = canvasPane.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        canvasPane.setOnMousePressed(e -> {
            gc.beginPath();
            gc.lineTo(e.getSceneX() - 185, e.getSceneY() - 70);
            gc.stroke();
        });

        canvasPane.setOnMouseReleased(e -> {
            gc.lineTo(e.getSceneX()- 185, e.getSceneY() - 70);
            gc.stroke();
        });

        colorPicker.setValue(Color.BLACK);
        colorPicker.setOnAction(e -> {
            gc.setStroke(colorPicker.getValue());
        });
    }

    private void drawBrush() {

        resetDraw();

        GraphicsContext gcontext = canvasPane.getGraphicsContext2D();
        gcontext.setStroke(Color.BLACK);
        gcontext.setLineWidth(1);

        canvasPane.setOnMousePressed(e -> {
            gcontext.beginPath();
            gcontext.lineTo(e.getSceneX() - 185, e.getSceneY() - 70);
            gcontext.stroke();
        });

        canvasPane.setOnMouseDragged(e -> {
            gcontext.lineTo(e.getSceneX() - 185, e.getSceneY() - 70);
            gcontext.stroke();
        });

        colorPicker.setValue(Color.BLACK);
        colorPicker.setOnAction(e -> {
            gcontext.setStroke(colorPicker.getValue());
        });
    }
    private void drawRectangle(){

        resetDraw();

        GraphicsContext rect = canvasPane.getGraphicsContext2D();
        canvasPane.setOnMousePressed(e -> {
            if (fillCheckBox.isSelected()) {
                rect.fillRect(e.getSceneX() - 197, e.getSceneY() - 77, 30, 20);
            } else {
                rect.strokeRect(e.getSceneX() - 197, e.getSceneY() - 77, 30, 20);
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
