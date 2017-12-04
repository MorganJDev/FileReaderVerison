package UI;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import UI.*;


public class Main extends Application
{
    public static Stage window;
    public static Stage popup;

	@Override
	public void start(Stage primaryStage) throws Exception
    {
        Stage newWindow = primaryStage;

        Parent current = FXMLLoader.load(getClass().getResource("Login.fxml"));
        newWindow.setTitle("Artatawe");

        newWindow.setScene(new Scene(current));
        Main.window = newWindow;
        newWindow.show();
	}
	
	public static void main(String[] args)
    {
		launch(args);
	}
}
