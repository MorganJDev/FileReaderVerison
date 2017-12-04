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

	@Override
	public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;

        Parent current = FXMLLoader.load(getClass().getResource("Login.fxml"));
        window.setTitle("Artatawe");

        window.setScene(new Scene(current));
        window.show();
	}
	
	public static void main(String[] args)
    {
		launch(args);
	}
}
