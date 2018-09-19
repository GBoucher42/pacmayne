package entities;

public class Tile {
	
	private int x;
	private int y;
	private Collectable item;
	private IGameEntity entity;
	private TileType type;
	
	public Tile(int x, int y, TileType type)
	{
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public int consumeCollectable() {
		int result = 0;
		if (hasCollectable()) {
			result = item.getScoreValue();
			this.item = null;
		}
		
		return result;
	}
	
	public boolean isWall() {
		return type == TileType.WALL;
	}
	
	public boolean isTileGum()
	{
		return item != null && item.isTileGum();
	}
	
	public boolean isTileSuperGum()
	{
		return item != null && item.isTileSuperGum();
	}
	
	public boolean hasCollectable() {
		return item != null;
	}
	
	public Collectable getCollectable()
	{
		return item;
	}
	
	public void setCollectable(Collectable item)
	{
		this.item = item;
	}
	
	public IGameEntity getGameEntity()
	{
		return entity;
	}
	
	public void setGameEntity(IGameEntity gameEntity) {
		this.entity = gameEntity;
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
