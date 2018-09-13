package entities;

import java.util.ArrayList;

public class EntityManager {
	
	private ArrayList<GameEntity> entities;
	
	public EntityManager()
	{
		entities = new ArrayList<>();
	}
	
	public void addEntity(GameEntity entity)
	{
		entities.add(entity);
	}
	
	public GameEntity getEntity(int index)
	{
		return entities.get(index);
	}
	
	public void deleteEntity(GameEntity entity)
	{
		entities.remove(entity);
	}
}
