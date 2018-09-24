package components;

public class GameObject {

	private final String name;
	private final int startX, startY;
	private int currentX, currentY;
	private final IInputComponent inputComponent;
	private final IPhysicsComponent physicsComponent;
	private final IGraphicsComponent graphicsComponent;
	
	public GameObject(String name, int x, int y, IInputComponent inputComponent, IPhysicsComponent physicsComponent, IGraphicsComponent graphicsComponent) {
		this.name = name;
		this.startX = x;
		this.startY = y;
		this.currentX = x;
		this.currentY = y;
		this.inputComponent = inputComponent;
		this.physicsComponent = physicsComponent;
		this.graphicsComponent = graphicsComponent;
	}
	
	public void update() {
		if (inputComponent != null) {
			inputComponent.update(this);
		}
		
		if (physicsComponent != null) {
			physicsComponent.update(this);
		}
		
		if (graphicsComponent != null) {
			graphicsComponent.update(this);
		}
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getStartX()
	{
		return startX;
	}
	
	public int getStartY()
	{
		return startY;
	}
	
	public int getCurrentX()
	{
		return currentX;
	}
	
	public int getCurrentY()
	{
		return currentY;
	}
	
	public void setCurrentX(int x)
	{
		this.currentX = x;
	}
	
	public void setCurrentY(int y)
	{
		this.currentY = y;
	}
}
