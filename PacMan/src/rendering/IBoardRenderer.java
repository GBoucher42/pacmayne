package rendering;

import java.util.List;

import entities.Entity;

public interface IBoardRenderer {

	void drawMaze(List<Sprite> sprites);
	
	void refreshScore(int score);
	
	void spawnAnimatables(List<Sprite> sprites);
	
	void setPacManEntity(Entity pacman);
}
