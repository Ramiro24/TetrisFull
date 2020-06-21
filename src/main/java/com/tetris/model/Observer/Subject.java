package com.tetris.model.Observer;
//import java.util.Observer;

public interface Subject {  //subject que implementa DatosObservados
	// esto es debido a que trabajamos con controladores y no con objetos de guitatobservador(que implementan observer, la implementacion de la interfaz observer formaliza y obliga al observador a implementar update pero el observador al mismo tiempo debe ser un controlador).
	public void registerObserver(GuiStatsObservador o); 
	public void removeObserver(GuiStatsObservador o);            
	public void notifyObservers();
		
}
