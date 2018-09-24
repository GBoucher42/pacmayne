package entitiesTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import entities.Animatable;
import entities.Collectable;
import entities.Fruit;
import entities.Gum;
import entities.IGameEntity;
import entities.Maze;
import entities.SuperGum;
import entities.Tile;
import entities.TileType;
import entities.Wall;

@TestInstance(Lifecycle.PER_CLASS)
public class TestTile {
	private Collectable itemGum, itemSgum, itemFruit;
	private TileType type;
	private Tile tile;

	@BeforeAll
	void setup() {
		tile = new Tile(1, 1, type.CORRIDOR);
		itemGum = new Gum(100, 1, 1, new Animatable("GUM"));
		itemSgum = new SuperGum(200, 1, 100, new Animatable("SUPERGUM"));
		itemFruit = new Fruit(500, 300, 200, new Animatable("SUPERGUM"));
	}

	@Test
	public void ConsumeCollectable_Gum_Coorectly() {
		tile.setGameEntity(itemGum);
		tile.setCollectable(itemGum);
		assertEquals(100, tile.consumeCollectable());
	}

	@Test
	public void ConsumeCollectable_SuperGum_Coorectly() {
		tile.setGameEntity(itemSgum);
		tile.setCollectable(itemSgum);
		assertEquals(200, tile.consumeCollectable());
	}

	@Test
	public void ConsumeCollectable_Fruit_Coorectly() {
		tile.setGameEntity(itemFruit);
		tile.setCollectable(itemFruit);
		assertEquals( 500, tile.consumeCollectable());
	}

	@Test
	public void DontIsWall() {
		assertNotEquals(tile.getType(), tile.isWall());
	}

	@Test
	public void YesitsTileGum() {
		tile.setGameEntity(itemGum);
		tile.setCollectable(itemGum);
		assertTrue( tile.isTileGum());
	}

	@Test
	public void YesitsTileSuperGum() {
		tile.setGameEntity(itemSgum);
		tile.setCollectable(itemSgum);
		assertTrue(tile.isTileSuperGum());
	}

}
