package rendering;

import gameThreads.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static configs.GameConfig.GAME_SIZE;

public class RenderSystem extends Application {
	
	public RenderSystem()
	{}
	
	public Stage initStage(Stage primaryStage, int width, int height) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, width, height, Color.BLACK);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return primaryStage;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage = initStage(primaryStage, GAME_SIZE, GAME_SIZE);
		Game gameInstance = new Game(primaryStage);
 		gameInstance.run();
	}
}
