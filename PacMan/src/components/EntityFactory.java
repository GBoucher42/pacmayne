package components;

import entities.Animatable;
import entities.Direction;
import entities.PacMan;
import entities.Velocity;

import static configs.GameConfig.TILE_SIZE;
import image.ImageRepository;

public class EntityFactory {
	private EntityManager entityManager;
	
	public EntityFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Entity createPacMan(int x, int y, Direction direction) {
		Entity entity = entityManager.CreateEntity();
		entityManager.addComponent(new PhysicsComponent("Pacman"), entity);
		GraphicComponent graphic = new GraphicComponent(Direction.RIGHT, ImageRepository.getImages("pacman", Direction.RIGHT), x * TILE_SIZE, y * TILE_SIZE);
		graphic.addAnimation(Direction.LEFT, ImageRepository.getImages("pacman", Direction.LEFT));
		graphic.addAnimation(Direction.UP, ImageRepository.getImages("pacman", Direction.UP));
		graphic.addAnimation(Direction.DOWN, ImageRepository.getImages("pacman", Direction.DOWN));
		entityManager.addComponent(graphic, entity);
		entityManager.addComponent(new MoveComponent(x,y,direction), entity);
		return entity;
	}
	
	public Entity createGum(int x, int y) {
		Entity entity = entityManager.CreateEntity();
		entityManager.addComponent(new PhysicsComponent("Gum"), entity);
		entityManager.addComponent(new GraphicComponent(ImageRepository.getImages("gum", Direction.NONE).get(0), x * TILE_SIZE, y * TILE_SIZE), entity);
		return entity;
	}
	
	public Entity createSuperGum(int x, int y) {
		Entity entity = entityManager.CreateEntity();
		entityManager.addComponent(new PhysicsComponent("Supergum"), entity);
		entityManager.addComponent(new GraphicComponent(Direction.NONE, ImageRepository.getImages("gum", Direction.NONE), x * TILE_SIZE, y * TILE_SIZE), entity);
		return entity;
	}

}
