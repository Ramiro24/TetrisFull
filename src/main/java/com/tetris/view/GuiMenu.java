package com.tetris.view;


import java.io.File;
//import	com.quirko.gui.GuiController;
import java.net.URL;
import java.util.ResourceBundle;

import com.tetris.controller.GameController;
import com.tetris.model.music.ReproduceAudio;
import com.tetris.model.music.ReproduceMusic;

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
    
    ReproduceMusic ReproMusic;
    ReproduceAudio audio;
    boolean bandera = true;
    
    
    @FXML
    void Ayuda(ActionEvent event) {
    	ayuda.show();
    }

    @FXML
    public void Comenzar(ActionEvent event) {
    	 audio.Fx(3);
    	gui.setDificultad(dificultad);
    	gui.newGame(event);
        stage.close();
    	ayuda.close();
    	primarystage.show();

    
    }

    @FXML
    void Sonido(ActionEvent event) {
    	
    	if(bandera) {
    		ReproMusic.ReproduceMusic(0);
    		bandera = false;}
    	    
    	else {
    		ReproMusic.DetenerMusica();
    		bandera = true;}
    	
    }
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
	     
    }
    public void GUI(GuiController c ,Stage stage1 ,Stage primaryStage ,Stage help, ReproduceMusic ReproMusic, ReproduceAudio audio  ) {
    	gui= c;
      	stage = stage1;
        primarystage = primaryStage;
        ayuda = help;
        this.ReproMusic = ReproMusic;
        this.audio = audio;
        
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
         audio.Fx(3);
    } 
    @FXML
    void Facil(ActionEvent event) {
    	dificultad = 400; 
    //	gui.setDificultad(dificultad);
    	 audio.Fx(16);
    }

    @FXML
    void Medio(ActionEvent event) {
    	dificultad = 300; 
    //	gui.setDificultad(dificultad);
    	 audio.Fx(3);
    }
   
  
  

}