package states;

import gameThreads.Game;
import javafx.scene.input.KeyCode;

//DESIGN PATTERN : State
public class InPlayGameState implements IState{

	private Game game;
	
	public InPlayGameState(Game game) {
		this.game = game;
	}
	
	@Override
	public void loadResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleInput(KeyCode key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}
}
