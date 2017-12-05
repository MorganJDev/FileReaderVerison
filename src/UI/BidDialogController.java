package UI;

import javafx.fxml.FXML;

/**
 * @author Morgan Jones 904410
 */

public class BidDialogController
{
    @FXML
    private void handleCancel()
    {
        Main.popup.close();
    }
}
