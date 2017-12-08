package UI;
	
import Classes.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import UI.*;

import java.time.LocalDateTime;

/**
 * @author Morgan Jones 904410
 * @version 1.0//
 */

public class Main extends Application
{
    public static Stage window;
    public static Stage popup;
    public static UserManager admin;
    public static Auctioneer auctioneer;
    public static AuctionListing selectedAuction;
    public static boolean isReloaded;

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
	
	public static void main(String[] args)
    {
		launch(args);
	}

	public void SetupApplication()
    {
        isReloaded = false;
        admin = new UserManager();
	    auctioneer = new Auctioneer();
        selectedAuction = null;

        admin.populateArray();
        auctioneer.populateArray();
    }

    public static void closeApplication()
    {
        admin.writeFiles();
        auctioneer.writeFiles();
    }
}
