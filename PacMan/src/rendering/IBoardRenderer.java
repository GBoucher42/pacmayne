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
	
	void pauseGame();
	
	void dispose();

	void refreshlevel(int i);

	void addBonusLife();
	
	void refreshFps(int fps);

	void Removesprites(List<Sprite> sprites);

	void addSpritesMoving(List<Sprite> sprites);

	void drawHeaderAndFooter();

	boolean isLevelPassed();

	void setLevelPassed(boolean b);

	boolean getBonusIsAdded();


}

