package components;

import entities.Strategy;

public class AIComponent implements IComponent{
	private Strategy strategy;
	
	public AIComponent(Strategy strategy) {
		this.strategy = strategy;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

}
