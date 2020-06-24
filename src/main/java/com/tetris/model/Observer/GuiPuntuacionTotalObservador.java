package com.tetris.model.Observer;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.ObservableList;
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
    
    private static ArrayList<Integer> puntos = new ArrayList<Integer>();

	private Subject datosObservados;
	
	private int puntuacion;
	
    
    public GuiPuntuacionTotalObservador() {}
   
    public GuiPuntuacionTotalObservador(Subject datosObservados , GuiPuntuacionTotalObservador s) {
    	this.datosObservados = datosObservados;
    	datosObservados.registerObserver((Observer) s);
    }
    
    public void initialize(URL location, ResourceBundle resources) {
    	readData();
    	Collections.sort(puntos);
    	Collections.reverse(puntos);
        lista.getItems().addAll(puntos);
      }
     /*
     * Comparar los valores 
     */
    public void Controlar() {
    	
    	if(puntuacion > puntos.get(0)) {
            puntos.set(0,puntuacion);
            lista.getItems().clear();
        	Collections.sort(puntos);
        	Collections.reverse(puntos);
            lista.getItems().addAll(puntos);
           }
    	if(puntuacion > puntos.get(1)) {
            puntos.set(1,puntuacion);
            lista.getItems().clear();
        	Collections.sort(puntos);
        	Collections.reverse(puntos);
            lista.getItems().addAll(puntos);
           }
    	if(puntuacion > puntos.get(2)) {
            puntos.set(2,puntuacion);
            lista.getItems().clear();
        	Collections.sort(puntos);
        	Collections.reverse(puntos);
            lista.getItems().addAll(puntos);
           }
    }
    public ArrayList<Integer> readData() {
		Scanner INPUT_STREAM;

		try {

			File file = new File("log1.txt");
			INPUT_STREAM = new Scanner(file);

			while (INPUT_STREAM.hasNext()) {

				String line = INPUT_STREAM.next();
				String[] temp = line.split("\n");
				StringBuffer cadena = new StringBuffer();
				for (int x = 0; x < temp.length; x++) {
					cadena = cadena.append(temp[x]);
				}
				String str = cadena.toString();
				int i = Integer.parseInt(str);
				puntos.add(i);

			}
			INPUT_STREAM.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return puntos;
	}
    public static void saveData() {
		String filepath = "log1.txt";
		String value = "";
		
		try {
			FileWriter fw = new FileWriter(filepath, true);
			fw.close();
			//BufferedWriter bw = new BufferedWriter(fw);
			File file = new File(filepath);
		    file.delete();
		    File file1 = new File(filepath);
            FileWriter fw1 = new FileWriter(filepath, true);
			BufferedWriter bw1 = new BufferedWriter(fw1);
			PrintWriter pw1 = new PrintWriter(bw1);
			for(int i=0;i<3;i++) {
			pw1.println(puntos.get(i));
			pw1.flush();
			}
			
			pw1.close();
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
    //@Override
	public void Update(int valor) {
		// TODO Auto-generated method stub
		
		puntuacion = valor;
		Controlar();
		System.out.println("valor");
	}
}
	
	
	
	
	
	


