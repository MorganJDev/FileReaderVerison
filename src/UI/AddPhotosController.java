package UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Morgan Jones 904410
 */
public class AddPhotosController
{
    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }
}
