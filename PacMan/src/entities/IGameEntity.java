package entities;

public interface IGameEntity {
	void setIsMoving(boolean isMoving);
	
	void moveOneFrameBySpeed();
	
	Animatable getAnimatable();
	
	int getTileIndex();
	
	String getName();
	
	double getStartX();
	
	double getStartY();
	
	double getCurrentX();
	
	double getCurrentY();
	
	void setCurrentX(double x);
	
	void setCurrentY(double y);
	
	void setSpeed(double speed);
	
	double getSpeed();
	
	void setDirection(Direction direction);
	
	Direction getDirection();
	
	void setVelocity(Velocity velocity);
	
	Velocity getVelocity();

}
