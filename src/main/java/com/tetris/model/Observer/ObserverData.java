package com.tetris.model.Observer;

import java.util.ArrayList;

//import com.quirko.gui.GuiController;
//import javafx.collections.ObservableList;


public class ObserverData implements Subject{ //CLASE A OBSERVAR
	
	private	ArrayList<Observer> observers;
	private int states;
	
	guiPuntuationObserver punctuationObserver;
	GuiStatsObservador statesObject;


	public ObserverData() {
		
	 observers = new ArrayList<Observer>();
	}
	
	public int getStates() {
		
		return states;
    }
	
	public void resetState() {
		states = 0;
	}
	
	public void setState() {
		
		states++;
		notifyObservers();
	}
	public void setStateBonus(int estado) {
		
		this.states = this.states + estado;
		notifyObservers();
	}
	@Override
	public void registerObserver(Observer observador) {

		if(observador instanceof guiPuntuationObserver){
			observers.add((guiPuntuationObserver)observador);}
	    if(observador instanceof  GuiStatsObservador){
			observers.add((GuiStatsObservador)observador);}

	}

	@Override
	public void removeObserver(Observer o) {
	 
	}
	@Override
	public void notifyObservers() {
		
		for(int i = 0; i< observers.size(); i++) {
			Observer observer = (Observer) observers.get(i);
			observer.Update(states);}
	}

}
