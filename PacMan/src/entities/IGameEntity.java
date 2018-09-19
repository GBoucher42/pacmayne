package entities;

public interface IGameEntity {
	void setIsMoving(boolean isMoving);
	
	void moveOneFrameBySpeed();
	
	Animatable getAnimatable();
	
	String getName();
	
	int getStartX();
	
	int getStartY();
	
	int getCurrentX();
	
	int getCurrentY();
	
	void setCurrentX(int x);
	
	void setCurrentY(int y);
	
	void setSpeed(double speed);
	
	double getSpeed();
	
	void setDirection(Direction direction);
	
	Direction getDirection();
	
	void setVelocity(Velocity velocity);
	
	Velocity getVelocity();

}
