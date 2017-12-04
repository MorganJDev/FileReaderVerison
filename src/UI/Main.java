package UI;
	
import Classes.User;
import Classes.UserManager;
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
    public static UserManager admin;

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
	}
	
	public static void main(String[] args)
    {
		launch(args);
	}

	public void SetupApplication()
    {
        admin = new UserManager();
        User newUser = new User("Dan", "Taylor","03748563859","03 Bryn Road","Brynmill",
                "Swansea", "Swansea", "SA2 0BT", "Dan98");
        User newUser2 = new User("Morgan", "Jones","03748563859","03 Bryn Road","Brynmill",
                "Swansea", "Swansea", "SA2 0BT", "Morg98");
        admin.registerUser(newUser);
        admin.registerUser(newUser2);
    }
}
