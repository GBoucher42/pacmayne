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
final Image imagepacman = new Image("file:ressource/sprites/life.png");
ImageView img1 =new ImageView(imagepacman);
ImageView img2 =new ImageView(imagepacman);
ImageView img3 =new ImageView(imagepacman);
//private List<ImageView> livesimage ;
private HBox hbox ;
private Pane pane;
ImageView [] tabimg= {img1,img2,img3};

public LivesImages(Pane pane) {
	hbox = new HBox();
for (int i = 0; i < tabimg.length; i++) {
	
	hbox.getChildren().add(tabimg[i]);
	
}
pane.getChildren().add(hbox);

}
public void hideImage(ImageView iv) {
	 hbox.getChildren().remove(iv);
}
public ImageView getImg1() {
	return img1;
}
public ImageView getImg2() {
	return img2;
}
public ImageView getImg3() {
	return img3;
}

}
