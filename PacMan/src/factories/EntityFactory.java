package factories;

import entities.Direction;
import entities.Entity;
import entities.EntityManager;

import static configs.GameConfig.TILE_SIZE;

import components.GraphicsComponent;
import components.MoveComponent;
import components.PhysicsComponent;
import components.ScoreComponent;
import components.UserInputComponent;
import image.ImageRepository;

public class EntityFactory {
	private EntityManager entityManager;
	
	public EntityFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Entity createPacMan(int x, int y, Direction direction) {
		Entity entity = entityManager.CreateEntity();
		entityManager.addComponent(new PhysicsComponent("Pacman"), entity);
		GraphicsComponent graphic = new GraphicsComponent(Direction.RIGHT, ImageRepository.getImages("pacman", Direction.RIGHT), x * TILE_SIZE, y * TILE_SIZE);
		graphic.addAnimation(Direction.LEFT, ImageRepository.getImages("pacman", Direction.LEFT));
		graphic.addAnimation(Direction.UP, ImageRepository.getImages("pacman", Direction.UP));
		graphic.addAnimation(Direction.DOWN, ImageRepository.getImages("pacman", Direction.DOWN));
		entityManager.addComponent(graphic, entity);
		entityManager.addComponent(new MoveComponent(x, y, direction), entity);
		entityManager.addComponent(new ScoreComponent(), entity);
		entityManager.addComponent(new UserInputComponent(), entity);
		return entity;
	}
	
	public Entity createGhost(int x, int y, Direction direction, String ghostName) {
		Entity entity = entityManager.CreateEntity();
		entityManager.addComponent(new PhysicsComponent(ghostName), entity);
		GraphicsComponent graphic = new GraphicsComponent(Direction.RIGHT, ImageRepository.getImages(ghostName, Direction.RIGHT), x * TILE_SIZE, y * TILE_SIZE);
		graphic.addAnimation(Direction.LEFT, ImageRepository.getImages(ghostName, Direction.LEFT));
		graphic.addAnimation(Direction.UP, ImageRepository.getImages(ghostName, Direction.UP));
		graphic.addAnimation(Direction.DOWN, ImageRepository.getImages(ghostName, Direction.DOWN));
		entityManager.addComponent(graphic, entity);
		entityManager.addComponent(new MoveComponent(x , y, direction), entity);
		return entity;
	}
	
	public Entity createGum(int x, int y) {
		Entity entity = entityManager.CreateEntity();
		entityManager.addComponent(new PhysicsComponent("Gum"), entity);
		entityManager.addComponent(new GraphicsComponent(ImageRepository.getImages("gum", Direction.NONE).get(0), x * TILE_SIZE, y * TILE_SIZE), entity);
		return entity;
	}
	
	public Entity createSuperGum(int x, int y) {
		Entity entity = entityManager.CreateEntity();
		entityManager.addComponent(new PhysicsComponent("SuperGum"), entity);
		entityManager.addComponent(new GraphicsComponent(Direction.NONE, ImageRepository.getImages("gum", Direction.NONE), x * TILE_SIZE, y * TILE_SIZE), entity);
		return entity;
	}
	
	public Entity createWall(int x, int y, int wallIndex) {
		Entity entity = entityManager.CreateEntity();
		entityManager.addComponent(new GraphicsComponent(ImageRepository.getImages("wall-" + wallIndex , Direction.NONE).get(0), x * TILE_SIZE, y * TILE_SIZE), entity);
		return entity;
	}

}
