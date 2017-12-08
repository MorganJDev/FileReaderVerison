
package UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.ColorPicker;
import javafx.scene.control.ColorPicker;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.awt.Canvas;
import javafx.scene.paint.Color;

import java.awt.*;

/**
 * @author Georgi  Georgiev
 */

public class CanvasController
{
    @FXML javafx.scene.canvas.Canvas canvasPane;
    @FXML javafx.scene.control.ColorPicker colorPicker;

    @FXML
    private void handleCancel()
    {
        Main.popup.close();
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

    private void drawCircle(){
        GraphicsContext circle = canvasPane.getGraphicsContext2D();
        //circle.fillOval(20,20,20);

        canvasPane.setOnMousePressed(e -> {
            circle.fillOval(e.getSceneX() - 197, e.getSceneY() - 77, 20, 20);
//            circle.setFill(Color.TRANSPARENT);
//            circle.setStroke(Color.YELLOW);
        });
        colorPicker.setValue(Color.BLACK);
        colorPicker.setOnAction(e -> {
            circle.setFill(colorPicker.getValue());
        });
    }

    private void drawLine(){

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

    }
}
