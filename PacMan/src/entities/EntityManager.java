package entities;

import java.util.ArrayList;

public class EntityManager {
	
	private ArrayList<GameEntity> entities = new ArrayList<>();
	
	public void addEntity(GameEntity entity)
	{
		entities.add(entity);
	}
	
	public GameEntity getEntity(int index)
	{
		return entities.get(index);
	}
	
	public GameEntity getEntity(String entityName)
	{
		for (GameEntity entity : entities)
		{
			if (entity.getName().equals(entityName)) {
				return entity;
			}
		}
		
		return null;
	}
	
	public void deleteEntity(GameEntity entity)
	{
		entities.remove(entity);
	}
	
	public void clear()
	{
		entities.clear();
	}
	
	public int count()
	{
		return entities.size();
	}
}
