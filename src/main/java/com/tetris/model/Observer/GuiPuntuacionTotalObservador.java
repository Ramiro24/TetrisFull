package com.tetris.model.Observer;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class GuiPuntuacionTotalObservador implements  Observer , Initializable{ // OBSERVADOR DE PUNTUACION

	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane Puntuacion;

    @FXML
    private Text Titulo;

    @FXML
    private ListView<Integer> lista;
    
    private ArrayList<Integer> puntos = new ArrayList<Integer>();

	private Subject datosObservados;
    
    public GuiPuntuacionTotalObservador() {}
   
    public GuiPuntuacionTotalObservador(Subject datosObservados , GuiPuntuacionTotalObservador s) {
    	this.datosObservados = datosObservados;
    	datosObservados.registerObserver((Observer) s);
    }
    
    public void initialize(URL location, ResourceBundle resources) {
 	puntos.add(412);
 	puntos.add(200);
 	puntos.add(300);
 	Collections.sort(puntos);
 	Collections.reverse(puntos);
 	lista.getItems().addAll(puntos);
     }
   
    public void  setPrimero(String primero) {
    	//Primero.setText(primero);
    }
    public void SetLista() {
    	//Levanta el txt
    }
    

	//@Override
	public void Update(int valor) {
		// TODO Auto-generated method stub
			System.out.println(valor);
	}
}
	
	
	
	
	
	


