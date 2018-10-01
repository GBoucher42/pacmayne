package rendering;

import static configs.GameConfig.GAME_WIDTH;
import static configs.GameConfig.HEIGTH_WINDOW;

import java.util.logging.Level;
import java.util.logging.Logger;

import gameThreads.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class RenderingSystem extends Application {
	
	private static final Logger LOGGER = Logger.getLogger( RenderingSystem.class.getName() );
	

	private Stage initStage(Stage primaryStage ,int width ,int height ) {
		try {
			primaryStage.setTitle("PacMan");
			Board root = new Board();
			
			Scene scene = new Scene(root,width,height,Color.BLACK);
			scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> root.onKeyPressed(event.getCode()));
			primaryStage.setScene(scene);
			primaryStage.setFullScreenExitHint("");
			primaryStage.setResizable(false);
			primaryStage.show();
			
			letterbox(scene, root);
			
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
		
		return primaryStage;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initStage(primaryStage,GAME_WIDTH,HEIGTH_WINDOW);
		Game gameInstance = new Game((IBoardRenderer) primaryStage.getScene().getRoot());
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

	         @Override
	         public void handle(WindowEvent event) {
	             Platform.runLater(new Runnable() {

	                 @Override
	                 public void run() {
	                	 gameInstance.stopThreads();
	                     System.exit(0);
	                 }
	             });
	         }
	     });
		primaryStage.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean > ov, Boolean t, Boolean t1) {
				gameInstance.setFocus(t1);
			}
		});
		
		primaryStage.iconifiedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean > ov, Boolean t, Boolean t1) {
				gameInstance.setInView(t);
			}
		});
 		gameInstance.run();
	}
	
	private void letterbox(final Scene scene, final Pane contentPane) {
	    final double initWidth  = scene.getWidth();
	    final double initHeight = scene.getHeight();
	    final double ratio      = initWidth / initHeight;

	    SceneSizeChangeListener sizeListener = new SceneSizeChangeListener(scene, ratio, initHeight, initWidth, contentPane);
	    scene.widthProperty().addListener(sizeListener);
	    scene.heightProperty().addListener(sizeListener);
	  }

	  private static class SceneSizeChangeListener implements ChangeListener<Number> {
	    private final Scene scene;
	    private final double ratio;
	    private final double initHeight;
	    private final double initWidth;
	    private final Pane contentPane;

	    public SceneSizeChangeListener(Scene scene, double ratio, double initHeight, double initWidth, Pane contentPane) {
	      this.scene = scene;
	      this.ratio = ratio;
	      this.initHeight = initHeight;
	      this.initWidth = initWidth;
	      this.contentPane = contentPane;
	    }

	    @Override
	    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
	      final double newWidth  = scene.getWidth();
	      final double newHeight = scene.getHeight();

	      double scaleFactor =
	          newWidth / newHeight > ratio
	              ? newHeight / initHeight
	              : newWidth / initWidth;

	      if (scaleFactor >= 1) {
	        Scale scale = new Scale(scaleFactor, scaleFactor);
	        scale.setPivotX(0);
	        scale.setPivotY(0);
	        scene.getRoot().getTransforms().setAll(scale);

	        contentPane.setPrefWidth (newWidth  / scaleFactor);
	        contentPane.setPrefHeight(newHeight / scaleFactor);
	      } else {
	        contentPane.setPrefWidth (Math.max(initWidth,  newWidth));
	        contentPane.setPrefHeight(Math.max(initHeight, newHeight));
	      }
	    }
	  }
}