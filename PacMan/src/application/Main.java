package application;
	
import gameThreads.Game;
import javafx.application.Application;
import javafx.stage.Stage;
import rendering.RenderSystem;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		RenderSystem renderSystem = new RenderSystem();
		Game gameInstance = new Game();
		gameInstance.run();		
		
		launch(args); // TODO: move scene and panes to renderSystem
	}
}
