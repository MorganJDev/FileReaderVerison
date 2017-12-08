package UI;
	
import Classes.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * This is the main method for the whole program. It loads the GUI and
 * initialises manager classes e.g. Auctioneer, UserManager
 *
 * @author Morgan Jones 904410
 * @version 1.0
 */

public class Main extends Application
{
    // Static variables to store current windows
    public static Stage window;
    public static Stage popup;

    // Static variables to store singular iterations of classes
    public static UserManager admin;
    public static Auctioneer auctioneer;
    public static AuctionListing selectedAuction;

    // Referenced in main menu initialisation
    public static String reloadedOn;

    /**
     * This overwrites an application method and loads the first screen (Login)
     * It also runs a user created setup method
     * @param primaryStage The current display
     * @throws Exception If file not found
     */
	@Override
	public void start(Stage primaryStage) throws Exception
    {
        SetupApplication();

        Stage newWindow = primaryStage;

        Parent current = FXMLLoader.load(getClass().getResource("Login.fxml"));
        newWindow.setTitle("Artatawe");

        newWindow.setScene(new Scene(current));
        Main.window = newWindow;
        newWindow.show();

        window.setOnCloseRequest(e -> closeApplication());
	}

    /**
     * This is the method which is run upon startup
     * @param args
     */
	public static void main(String[] args)
    {
		launch(args);
	}

    /**
     * This initialises all the static variables.
     * It also reads in data from all files and populates the static arrays
     */
	public void SetupApplication()
    {
        reloadedOn = "";
        admin = new UserManager();
	    auctioneer = new Auctioneer();
        selectedAuction = null;

        admin.populateArray();
        auctioneer.populateArray();
    }

    /**
     * This is run when the program is closed, including the X button on the form
     * It writes all the data from the static arrays into the file
     */
    public static void closeApplication()
    {
        admin.writeFiles();
        auctioneer.writeFiles();
    }
}
