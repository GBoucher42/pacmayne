package observer;

import java.util.ArrayList;

public class Observable {

	private ArrayList<IObserver> ObserverList = new ArrayList<IObserver>();
	
	public void register(IObserver observer) {
		if(!ObserverList.contains(observer)) {
			ObserverList.add(observer);
		}
	}
	
	public void unregister(IObserver observer) {
		if(ObserverList.contains(observer)) {
			ObserverList.remove(observer);
		}
	}
	
	public void notify(IObserver observer) {
		observer.update();
	}
	
	public void notifyAllObservers() {
		for (IObserver observer: ObserverList) {
			observer.update();
		}
	}
}
