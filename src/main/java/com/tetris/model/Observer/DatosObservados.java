package com.tetris.model.Observer;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

//import com.quirko.gui.GuiController;
//import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

public class DatosObservados implements Subject{ //CLASE A OBSERVAR
	
	private	ArrayList<Observer> observadores;
	private int estado;
	
	GuiPuntuacionTotalObservador puntuacionobjeto;
	GuiStatsObservador statobjeto;


	public DatosObservados() {
		
	 observadores = new ArrayList<Observer>();
	}
	
	public int getEstado() {
		
		return estado;
    }
	public void setEstado() {
		
		estado++;
		notifyObservers();
	}
	public void setEstadoBonus(int estado) {
		
		this.estado = this.estado + estado;
		notifyObservers();
	}
	@Override
	public void registerObserver(Observer observador) {

		if(observador instanceof  GuiPuntuacionTotalObservador){observadores.add((GuiPuntuacionTotalObservador)observador);}
	    if(observador instanceof  GuiStatsObservador){observadores.add((GuiStatsObservador)observador);}
	 //   System.out.println("soy observador " + observadores.size());
	}

	@Override
	public void removeObserver(Observer o) {
	 
	}
	@Override
	public void notifyObservers() {
		
		for(int i=0; i<observadores.size(); i++) {
			Observer observer = (Observer)observadores.get(i);
			observer.Update(estado);}
	}

}
