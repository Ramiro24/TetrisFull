package com.tetris.model.Observer;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class GuiStatsObservador implements Observer, Initializable { //DISPLAY GRAFICO OBSERVADOR

    @FXML
    private LineChart<?, ?> Linechart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private Subject datosObservados;

    public GuiStatsObservador() {
    }

    public GuiStatsObservador(Subject datosObservados, GuiStatsObservador s) {
        this.datosObservados = datosObservados;
        datosObservados.registerObserver(s); //agrego controlador a la lista del observable
    }

    public void initialize(URL location, ResourceBundle resources) {

        xAxis.setLabel("Jugadores");
        yAxis.setLabel("Puntuacion");
        Linechart.setTitle("Puntuacion actual");
    }

    public void Update(int score) {

        XYChart.Series set1 = new XYChart.Series<>();
        set1.getData().add(new XYChart.Data("Player", score));
        Linechart.getData().addAll(set1);
    }

}
