package com.tetris.model.Observer;
//import java.util.Observer;

public interface Subject {  //subject que implementa ObserverData
	public void registerObserver(Observer o); 
	public void removeObserver(Observer o);            
	public void notifyObservers();
		
}
