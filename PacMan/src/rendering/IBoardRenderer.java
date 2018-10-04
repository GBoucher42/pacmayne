package rendering;

import java.util.List;

import entities.Entity;

public interface IBoardRenderer {

	void drawMaze(List<Sprite> sprites);
	
	void refreshScore(int score);
	
	void refreshLives(int lives);
	
	void spawnAnimatables(List<Sprite> sprites);
	
	void setPacManEntity(Entity pacman);
	
	boolean isRunning();
	
	void setRunning(boolean isRunning);

	void initLives(int lives);
	
	void displayPause();
	
	void hidePause();
}

