package states;

import javafx.scene.input.KeyCode;

//DESIGN PATTERN : State
public interface IState {
	void loadResources();
	
	void handleInput(KeyCode key);
	
	void update();
	
	void onEnter();
	
	void onExit();
}
