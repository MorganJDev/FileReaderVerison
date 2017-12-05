package UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;
import java.awt.Canvas;
import javafx.scene.paint.Color;

import java.awt.*;

/**
 * @author Georgi  Georgiev
 */

public class CanvasController
{
    ColorPicker cp;
    @FXML javafx.scene.canvas.Canvas canvasPane;
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
    private void handleDraw()
    {
        //drawLine();
    }

    private void drawLine(){
        cp = new ColorPicker();

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


        cp.setValue(Color.BLACK);
        cp.setOnAction(e -> {
            gc.setStroke(cp.getValue());
        });
    }

    private void drawReactangle(){

    }
}
