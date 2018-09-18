package entities;

import java.util.ArrayList;

public class EntityManager {
	
	private ArrayList<IGameEntity> entities = new ArrayList<>();
	
	public void addEntity(IGameEntity entity)
	{
		if (!entities.contains(entity)) {
			entities.add(entity);
		}
	}
	
	public IGameEntity getEntity(int index)
	{
		return entities.get(index);
	}
	
	public IGameEntity getEntity(String entityName)
	{
		for (IGameEntity entity : entities)
		{
			if (entity.getName().equals(entityName)) {
				return entity;
			}
		}
		
		return null;
	}
	
	public void deleteEntity(IGameEntity entity)
	{
		// TODO: should fire an event caught by the board for it to delete the associated sprite
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
