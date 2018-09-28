package threads;

public enum MessageEnum {
	COLLISION, DOWN, RIGHT, LEFT, UP, EATEN, KILLED, GUMPOINTS(10), SUPERGUMPOINTS(50);
	
	private int points = 0;
	
	MessageEnum() {}
	
	MessageEnum(int value) {
		this.points = value;
	}
	
	public int getPoints() {
		return points;
	}
}
