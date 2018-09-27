package components;

public abstract class SystemBase {
	
	protected EntityManager entityManager;
	
	public SystemBase(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public abstract void update();
}
