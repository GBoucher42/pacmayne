package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LivesImages {
	final Image imagelife = new Image("file:ressource/sprites/life.png");
//	ImageView img1 =new ImageView(imagelife);
//	ImageView img2 =new ImageView(imagelife);
//	ImageView img3 =new ImageView(imagelife);
	//private List<ImageView> livesimage ;
	private HBox hbox ;
	private Pane pane;
	ImageView [] tabimg=new ImageView [4] ;

	public LivesImages(Pane pane) {
		hbox = new HBox();
	for (int i = 1; i < 4; i++) {
		tabimg[i]=new ImageView(imagelife);
		hbox.getChildren().add(tabimg[i]);
		
	}
	pane.getChildren().add(hbox);

	}
	public void hideImage(ImageView im) {
		 hbox.getChildren().remove( im);
	}
	
	public ImageView getimg(int index) {
		return tabimg[index];
	}

	
	
}