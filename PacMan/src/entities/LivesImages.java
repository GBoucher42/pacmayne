package entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class LivesImages {
	final Image imagelife = new Image("file:ressource/sprites/life.png");
	private HBox hbox;
	private ImageView[] tabimg;
	private int nblives;

	public LivesImages(Pane pane, int nblives) {
		this.hbox = new HBox();
		this.nblives = nblives;

		tabimg = new ImageView[10];

		System.out.println("++++++++++++++" + this.nblives);

		for (int i = 1; i <= this.nblives; i++) {
			tabimg[i] = new ImageView(imagelife);
			hbox.getChildren().add(tabimg[i]);

		}
		pane.getChildren().add(hbox);

	}

	public void removeLife(ImageView im) {
		hbox.getChildren().remove(im);
	}

	public ImageView getimg(int index) {
		return tabimg[index];
	}

}