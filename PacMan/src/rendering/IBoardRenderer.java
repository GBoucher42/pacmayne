package rendering;

import entities.EntityManager;
import entities.Maze;

public interface IBoardRenderer {

	void drawMaze(Maze map);
	
	void spawnAnimatables(EntityManager entityManager);
	
	void spawnStaticEntities(EntityManager entityManager);
	
	void refreshView();
}
