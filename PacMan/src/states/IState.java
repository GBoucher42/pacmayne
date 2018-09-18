package states;

//DESIGN PATTERN : State
public interface IState {
	void onEnter();
	
	void onUpdate();
	
	void onExit();
}
