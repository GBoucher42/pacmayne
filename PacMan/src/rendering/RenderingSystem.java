package rendering;

import gameThreads.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static configs.GameConfig.GAME_WIDTH;
import static configs.GameConfig.HEIGTH_WINDOW;



public class RenderingSystem extends Application {
	

	private Stage initStage(Stage primaryStage ,int width ,int height ) {
		try {
			primaryStage.setTitle("PacMan");
			Board root = new Board();
			
		
			Scene scene = new Scene(root,width,height,Color.BLACK);
			scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> root.onKeyPressed(event.getCode()));
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return primaryStage;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage = initStage(primaryStage,GAME_WIDTH,HEIGTH_WINDOW);

		Game gameInstance = new Game((IBoardRenderer) primaryStage.getScene().getRoot());
 		gameInstance.run();
	}
}
