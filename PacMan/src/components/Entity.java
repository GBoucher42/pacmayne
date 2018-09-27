package components;

public class Entity {
	private final int id;
	
	public Entity(int id) {
		this.id = id;
	}
	
	public String toString() {
		return Integer.toString(id);
	}
	
}
