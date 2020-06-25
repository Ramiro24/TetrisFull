package com.tetris.model.Observer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class GuiStatsObservador implements Observer, Initializable { //DISPLAY GRAFICO OBSERVADOR
	
	 @FXML
	  private LineChart<?, ?> Linechart;

	 @FXML
	  private CategoryAxis xAxis;
             
	 @FXML
	  private NumberAxis yAxis;
	
	  private  Subject observerDataInStats;
	 
	  public GuiStatsObservador() {}
	  
	  public GuiStatsObservador(Subject observerData, GuiStatsObservador s) {
			 this.observerDataInStats = observerData;
			 observerData.registerObserver((Observer)s); //agrego controlador a la lista del observable
	  }
	 
	 public void initialize(URL location, ResourceBundle resources) {
	     
	    xAxis.setLabel("Jugadores");
	    yAxis.setLabel("Puntuacion");
	    Linechart.setTitle("Puntuacion actual");
      }
	  public void Update(int score) {
			
		    XYChart.Series set1 = new XYChart.Series<>();
		    set1.getData().add(new XYChart.Data("Player",score));
	    	Linechart.getData().addAll(set1);
	  }
	  
}
