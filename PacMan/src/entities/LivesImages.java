package entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class LivesImages {
	final Image imagelife = new Image("file:ressource/sprites/life.png");
	private HBox hbox;
	private ImageView[] livesImages;
	private int nblives;

	public LivesImages(Pane pane, int nblives) {
		this.hbox = new HBox();
		this.nblives = nblives;

		livesImages = new ImageView[nblives];

		for (int i = 0; i < nblives; i++) {
			livesImages[i] = new ImageView(imagelife);
			hbox.getChildren().add(livesImages[i]);

		}
		pane.getChildren().add(hbox);

	}

	public void removeLife() {
		--nblives;
		hbox.getChildren().remove(livesImages[nblives]);
	}

}