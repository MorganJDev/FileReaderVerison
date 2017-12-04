package UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddPhotosController
{
    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }
}
