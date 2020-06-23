package com.tetris.view;


//import	com.quirko.gui.GuiController;
import java.net.URL;
import java.util.ResourceBundle;

import com.tetris.controller.GameController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class GuiMenu implements Initializable {
   @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Ayuda;

    @FXML
    private Button Comenzar;

    @FXML
    private RadioButton Dificil;

    @FXML
    private RadioButton Facil;

    @FXML
    private RadioButton Medio;

    @FXML
    private ToggleButton Sonido;
    // Metodos extras //
    private GuiController gui ;
    
    private Stage stage; 
    
    private Stage primarystage;
    
    private int dificultad;
    
    private Stage ayuda;
    
    private GameController game;
    
    
    
    @FXML
    void Ayuda(ActionEvent event) {
    	ayuda.show();
    }

    @FXML
    public void Comenzar(ActionEvent event) {
    //	game.setDificultad(dificultad);
    	gui.setDificultad(dificultad);
    	gui.newGame(event);
        
        stage.close();
    	
//1.-   game.inicia();
    	ayuda.close();
//        GameController game = new GameController(gui);
    	primarystage.show();

    
    }

    @FXML
    void Sonido(ActionEvent event) {
    }
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
	     
    }
    public void GUI(GuiController c ,Stage stage1 ,Stage primaryStage ,Stage help  ) {
    	gui= c;
      	stage = stage1;
        primarystage = primaryStage;
        ayuda = help;
        
    }
    
    public void Game(GameController juego) {
    	game = juego;
    }
    /* public void dificultad(RadioButton Dificil ,RadioButton Medio,RadioButton Facil ) {
    	 this.Dificil = Dificil;
    	 this.Medio = Medio;
    	 this.Facil = Facil;
    	 if(Dificil==null) {
    		 System.out.println("Dificil");
    	 }
     }*/
    @FXML
    void Dificil(ActionEvent event) {
         dificultad = 100;
     //    gui.setDificultad(dificultad);
    } 
    @FXML
    void Facil(ActionEvent event) {
    	dificultad = 400; 
    //	gui.setDificultad(dificultad);
    }

    @FXML
    void Medio(ActionEvent event) {
    	dificultad = 300; 
    //	gui.setDificultad(dificultad);
    }
   
  
  

}