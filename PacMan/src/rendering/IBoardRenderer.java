package rendering;

import java.util.List;
import components.Sprite;

public interface IBoardRenderer {

	void drawMaze(List<Sprite> sprites);
	
	void loadSounds();
	
	void spawnAnimatables(List<Sprite> sprites);
}
