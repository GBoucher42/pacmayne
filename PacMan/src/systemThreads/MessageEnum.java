package systemThreads;

public enum MessageEnum {
	COLLISION, DOWN, RIGHT, LEFT, UP, EATEN, INVINCIBLE_START, INVINCIBLE_END, KILLED, GUMPOINTS(10), SUPERGUMPOINTS(50), VOLUME_UP, VOLUME_DOWN, MUTE;
	
	private int points = 0;
	
	MessageEnum() {}
	
	MessageEnum(int value) {
		this.points = value;
	}
	
	public int getPoints() {
		return points;
	}
}
