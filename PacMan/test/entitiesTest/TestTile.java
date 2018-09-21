package entitiesTest;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import entities.Animatable;
import entities.Collectable;
import entities.Gum;
import entities.IGameEntity;
import entities.SuperGum;
import entities.Tile;
import entities.TileType;
import entities.Wall;

@TestInstance(Lifecycle.PER_CLASS)
public class TestTile {
	private Collectable item;
	private IGameEntity entity;
	private TileType type;
	private Tile tile;
	private Gum gum;
	private SuperGum sgum;
	private Animatable animatable;
          
 @BeforeAll
 void setup() {
	tile=new Tile(0, 0, type.WALL);
	item =new Gum(20, 20, 150, animatable);
	item =new SuperGum(20, 200, 30, animatable);
	tile.setGameEntity(item);
 }
 
 @Test(expected =NullPointerException.class)
 public void ConsumeCollectableCoorectly(){
	 tile.setCollectable(item);
	 assertTrue(tile.hasCollectable());
	 assertNotEquals(null,tile.consumeCollectable());
	 
 }
 @Test(expected =NullPointerException.class)
 public void IswallCorrectly() {
	 assertEquals(true, tile.isWall());}
 
 @Test(expected =NullPointerException.class)
public void DontisTileGum(){
	 assertEquals(false, item.isTileGum());
	 assertEquals(false, tile.isTileGum());
	 }
 @Test(expected =NullPointerException.class)
public void isTileSuperGum(){
	 assertEquals(false, item.isTileSuperGum());
	 assertEquals(false, tile.isTileSuperGum());
	 }
 
}
