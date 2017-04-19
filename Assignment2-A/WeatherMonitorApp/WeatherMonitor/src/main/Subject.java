/**
 * 
 */
package main;

import java.util.HashSet;

/**
 * @author Tom
 *
 */
abstract class Subject {

	HashSet<Observer> observers = new HashSet<Observer>();
	
	public void attach(Observer o) {
		observers.add(o);
	}
	
	public void detach(Observer o) {
		observers.remove(o);
	}
	
	public void notifyObservers() {
		for (Observer observer : observers) {
	         observer.update();
         }
	}
}