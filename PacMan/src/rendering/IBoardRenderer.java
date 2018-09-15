package rendering;

import entities.EntityManager;

public interface IBoardRenderer {

	void drawMaze();
	
	void spawnAnimatables(EntityManager entityManager);
	
	void spawnStaticEntities(EntityManager entityManager);
	
	void refreshView();
	
	void animate();
}
