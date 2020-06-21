package com.tetris.app;

import com.tetris.view.GuiAyuda;
import com.tetris.view.GuiMenu;
import com.tetris.controller.GameController;
import com.tetris.model.Observer.DatosObservados;
import com.tetris.model.Observer.GuiStatsObservador;
import com.tetris.view.GuiController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application {

	private int Dificultad;
	
    @Override
    public void start(Stage StageMenu) throws Exception {
   
    	 ///////////////////////////////////////////////INTERFAZ MENU////////////////////////////////////////////////////
       
    	URL locationMenu = getClass().getClassLoader().getResource("Menu.fxml");
        ResourceBundle resources = null; //recurso de lenguaje null para todos
      
        FXMLLoader fxmlLoaderMenu = new FXMLLoader(locationMenu, resources);
        Parent rootMenu = fxmlLoaderMenu.load();
        GuiMenu menu = fxmlLoaderMenu.getController();

        StageMenu.setTitle("Menu");
        
        Scene sceneMenu = new Scene(rootMenu, 620, 450);
        StageMenu.setScene(sceneMenu);
       
        StageMenu.show();
       	
       ////////////////////////////////////////////////INTERFAZ DE TETRIS/////////////////////////////////////////////////
       
        URL locationTetris = getClass().getClassLoader().getResource("gameLayout.fxml");
     
        FXMLLoader fxmlLoaderTetris = new FXMLLoader(locationTetris, resources); //creo objeto FXML en java
        Parent rootTetris = fxmlLoaderTetris.load();                             //cargo FXML como raiz
        GuiController c = fxmlLoaderTetris.getController();                      //creo controlador de tipo gui (tetris)
       
        Stage StageTetris = new Stage();                                         //Stage de tetris
        StageTetris.setTitle("TetrisFULL");
       
        Scene SceneTetris = new Scene(rootTetris, 800, 610);                     //cargo raiz en scene
        StageTetris.setScene(SceneTetris);                                       //cargo scene en stage
        //primaryStage.show();
      //game es el sujeto a ser observado
        
      //  c.setDificultad(Dificultad);
    //    GameController ControladorTetris = new GameController(c);                   //creo controlador de juego y le paso controlador grafico de tetris
        
        //-----------------------------------------------------
       //se lo envio a StatisticController para que lo observe

     //   new StatisticController(game);
 ///////////////////////////////////////////////INTERFAZ GRAFICA (Observador)//////////////////////////////////////////////
    
      URL locationGrafica = getClass().getClassLoader().getResource("NuevaVentana.fxml");
    
        FXMLLoader fxmlLoaderGrafica = new FXMLLoader(locationGrafica, resources);  //creo objeto FXML en java
        Parent rootGrafica = fxmlLoaderGrafica.load();                              //cargo FXML como raiz
        GuiStatsObservador s = fxmlLoaderGrafica.getController();                   //lo cargo en controlador de tipo estadistica
       
        Stage StageGrafico = new Stage();                                           //Stage de grafico
        StageGrafico.setTitle("Grafico de estadistica Tetris");
        
        Scene sceneGrafica = new Scene(rootGrafica, 620, 450);                      //cargo root en scene
        StageGrafico.setScene(sceneGrafica);                                        //cargo scene en stage
     
       c.Grafico(StageGrafico);
       DatosObservados statObservador = new DatosObservados();                           //mi clase que implementa Subject
       GuiStatsObservador ObservadorDisplay = new GuiStatsObservador(statObservador, s); // le paso referencia a OBSERVADOR de la clase a observar y le paso su controlador para que lo agregue a la lista de observadores del observador 
    
 
       GameController ControladorTetris = new GameController(c,statObservador);            
       //game.setEstado(10);
 
        
   ////////////////////////////////////////////////////INTERFAZ AYUDA///////////////////////////////////////////////////////////
     
      URL location3 = getClass().getClassLoader().getResource("Ayuda.fxml");
       
        FXMLLoader fxmlLoader3 = new FXMLLoader(location3, resources);
        Parent root3 = fxmlLoader3.load();
        GuiAyuda ayuda = fxmlLoader3.getController();
        Stage help = new Stage();
       
       // Stage stage1 = new Stage();
        help.setTitle("Menu");
        help.setScene(new Scene(root3, 620, 450));
    
   //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        menu.GUI(c , StageMenu ,StageTetris, help);
     //   menu.Game(game);
}

 public static void main(String[] args) {
        launch(args);
    }
}
