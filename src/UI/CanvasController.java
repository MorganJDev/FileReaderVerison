package UI;

import javafx.fxml.FXML;

/**
 * @author Georgi Georgiev
 */

public class CanvasController
{
    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }
}
