package UI;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This method controls the login screen.
 * It navigates to registering a user or the main
 * menu.
 *
 * @author Morgan Jones 904410
 * @version 1.0
 */

public class LoginController
{
    // References to GUI
    public javafx.scene.control.TextField usernameBox;

    /**
     * Loads the register menu and closes current menu
     * @throws Exception If file is not found
     */
    public void handleRegister() throws Exception
    {
        Stage window = new Stage();

        Parent current = FXMLLoader.load(getClass().getResource("RegisterUser.fxml"));

        window.setScene(new Scene(current));
        window.show();

        Main.window.close();
        Main.window = window;
    }

    /**
     * Loads the main menu if the username if found
     * @throws Exception If file is not found
     */
    @FXML
    public void handleLogin() throws Exception
    {
        if(foundUser())
        {
            Parent current = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

            Main.window.close();
            Main.window.setScene(new Scene(current));
            Main.window.show();

            Main.window.setOnCloseRequest(e -> Main.closeApplication());
        }
        else
        {
            usernameBox.setText("");
        }
    }

    /**
     * Searches the static list of all users for a matching username to the input
     * @return True/False if user is found
     */
    public Boolean foundUser()
    {
        Boolean found = false;
        String input = usernameBox.getText();

        for(int i = 0; i <= (Main.admin.getAllUsers().size() - 1); i++)
        {
            if (Main.admin.getAllUsers().get(i).getUsername().equals(input))
            {
                // Sets the Main static variable for current user
                Main.admin.setCurrentUser(Main.admin.getAllUsers().get(i));
                found = true;
            }
        }

        return found;
    }
}
