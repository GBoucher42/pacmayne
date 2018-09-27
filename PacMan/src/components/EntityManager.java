package components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityManager {
	private List<Entity> entities;
	private Map<String, Map<Entity, IComponent>> componentsByClass;
	private int lowestUnassingedId;
	
	public EntityManager() {
		lowestUnassingedId = 0;
		entities = new ArrayList<>();
		componentsByClass = new HashMap<String, Map<Entity, IComponent>>();
	}
	
	public int generateNewId() {
		return lowestUnassingedId++; 
		
	}
	
	public Entity CreateEntity() {
		return new Entity(generateNewId());
	}
	
	public void addComponent(IComponent component, Entity entity) {
		String name = component.getClass().getName();
		if(!componentsByClass.containsKey(name)) {
			componentsByClass.put(name, new HashMap<Entity, IComponent>());
			componentsByClass.get(name).put(entity, component);
		} else {
			if(!componentsByClass.get(name).containsKey(entity)) {
				componentsByClass.get(name).put(entity, component);
			}
		}
	}
	
	public void removeEntity(Entity entity) {
		for (Map.Entry<String, Map<Entity, IComponent>> parentEntry : componentsByClass.entrySet()) {
			if(parentEntry.getValue().containsKey(entity)) {
				parentEntry.getValue().remove(entity);
			}
		}
		entities.remove(entity);
	}
	
	public IComponent getComponentOfClass(String className, Entity entity) {
		return componentsByClass.get(className).get(entity);
	}
	
	public List<Entity> getAllEntitiesPosessingComponentOfClass(String className) {
		List<Entity> entities = new ArrayList<>();
		for (Map.Entry<Entity, IComponent> entry : componentsByClass.get(className).entrySet()) {
			entities.add(entry.getKey());
		}
		
		return entities;
	}
	

}
