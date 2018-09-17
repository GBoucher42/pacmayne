package entities;

public class Tile {
	
	private int x;
	private int y;
	private Collectable item;
	private GameEntity entity;
	private TileType type;
	
	public Tile(int x, int y, TileType type)
	{
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public Collectable getCollectable()
	{
		return item;
	}
	
	public void setCollectable(Collectable item)
	{
		this.item = item;
	}
	
	public GameEntity getGameEntity()
	{
		return entity;
	}
	
	public TileType getType()
	{
		return type;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
}
