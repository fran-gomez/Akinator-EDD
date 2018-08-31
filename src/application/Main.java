package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * Class Main Clase principal del juego
 * @author fgvol
 *
 */
public class Main extends Application {
	
	public void start(Stage primaryStage) {
		try {

			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Akinator.fxml"));
            
			Pane root = (Pane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Akinator");
			
            primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
