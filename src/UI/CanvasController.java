
package UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.ColorPicker;
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
    private void handleCancel()
    {
        Main.popup.close();
    }

    private void drawCircle(){

    }

    @FXML
    private void handleLineClick()
    {
        drawLine();
    }

    @FXML
    private void handleCircleClick()
    {
        //drawCircle;
    }

    private void drawLine(){
        //colorPicker = new ColorPicker();

        GraphicsContext gc = canvasPane.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        canvasPane.setOnMousePressed(e -> {
            gc.beginPath();
            gc.lineTo(e.getSceneX() - 185, e.getSceneY() - 70);
            gc.stroke();
        });

        canvasPane.setOnMouseDragged(e -> {
            gc.lineTo(e.getSceneX() - 185, e.getSceneY() - 70);
            gc.stroke();
        });


        colorPicker.setValue(Color.BLACK);
        colorPicker.setOnAction(e -> {
            gc.setStroke(colorPicker.getValue());
        });
    }

    private void drawRectangle(){

    }
}
