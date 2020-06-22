package com.tetris.app;

import com.tetris.view.GuiAyuda;
import com.tetris.view.GuiMenu;
import com.tetris.controller.GameController;
import com.tetris.model.Observer.DatosObservados;
import com.tetris.model.Observer.GuiPuntuacionTotalObservador;
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

        public static void main(String[] args) {
                launch(args);
        }


        @Override
        public void start(Stage StageMenu) throws Exception { //comienza con stage de menu

                //Archivos FXML
                URL locationMenu = getClass().getClassLoader().getResource("Menu.fxml");
                URL locationTetris = getClass().getClassLoader().getResource("gameLayout.fxml");
                URL locationGrafica = getClass().getClassLoader().getResource("NuevaVentana.fxml");
                URL locationAyuda = getClass().getClassLoader().getResource("Ayuda.fxml");
                URL locationPuntuacionTotal = getClass().getClassLoader().getResource("PuntuacionTotal.fxml");
                ResourceBundle resources = null; //recurso de lenguaje null para todos

                //objetos FXML
                FXMLLoader fxmlLoaderMenu = new FXMLLoader(locationMenu, resources);
                FXMLLoader fxmlLoaderTetris = new FXMLLoader(locationTetris, resources);
                FXMLLoader fxmlLoaderGrafica = new FXMLLoader(locationGrafica, resources);
                FXMLLoader fxmlLoaderAyuda = new FXMLLoader(locationAyuda, resources);
                FXMLLoader fxmlLoaderPuntuacionTotal = new FXMLLoader(locationPuntuacionTotal, resources);

                //Root
                Parent rootMenu = fxmlLoaderMenu.load();
                Parent rootTetris = fxmlLoaderTetris.load();
                Parent rootGrafica = fxmlLoaderGrafica.load();
                Parent rootAyuda = fxmlLoaderAyuda.load();
                Parent rootPuntuacionTotal = fxmlLoaderPuntuacionTotal.load();

                //Controladores de Fxml
                GuiMenu menu = fxmlLoaderMenu.getController();
                GuiController c = fxmlLoaderTetris.getController();
                GuiStatsObservador s = fxmlLoaderGrafica.getController();
                GuiAyuda ayuda = fxmlLoaderAyuda.getController();
                GuiPuntuacionTotalObservador puntuacion = fxmlLoaderPuntuacionTotal.getController();

                ///////////////////////////////////////////////INTERFAZ MENU////////////////////////////////////////////////////////

                StageMenu.setTitle("Menu");
                Scene sceneMenu = new Scene(rootMenu, 620, 450);
                StageMenu.setScene(sceneMenu);
                StageMenu.show();

                ////////////////////////////////////////////////INTERFAZ DE TETRIS////////////////////////////////////////////////////

                Stage StageTetris = new Stage();
                StageTetris.setTitle("TetrisFULL");
                Scene SceneTetris = new Scene(rootTetris, 800, 610);
                StageTetris.setScene(SceneTetris);

                //////////////////////////////////////////////INTERFAZ GRAFICA (Observador)////////////////////////////////////////////

                Stage StageGrafico = new Stage();
                StageGrafico.setTitle("Grafico de estadistica Tetris");
                Scene sceneGrafica = new Scene(rootGrafica, 620, 450);
                StageGrafico.setScene(sceneGrafica);

                c.Grafico(StageGrafico);
                DatosObservados statObservador = new DatosObservados();
                GuiStatsObservador ObservadorDisplay = new GuiStatsObservador(statObservador, s); // le paso referencia a OBSERVADOR de la clase a observar y le paso su controlador para que lo agregue a la lista de observadores del observador
                GameController ControladorTetris = new GameController(c,statObservador);

                ////////////////////////////////////////////////////INTERFAZ AYUDA/////////////////////////////////////////////////////

                Stage Stagehelp = new Stage();
                Stagehelp.setTitle("Ayuda");
                Stagehelp.setScene(new Scene(rootAyuda, 620, 450));


                /////////////////////////////////////////////////INTERFAZ GRAFICA PUNTUACION (Observador)////////////////////////////////////

                Stage puntos = new Stage();
                puntos.setTitle("Puntuacion");
                puntos.setScene(new Scene(rootPuntuacionTotal, 620, 450));
                c.Puntuacion(puntos);

                GuiPuntuacionTotalObservador ObservadorDisplayTotal = new  GuiPuntuacionTotalObservador(statObservador, puntuacion);

                //////////////////////////////////////////////////////INICIO MENU//////////////////////////////////////////////////////////


                menu.GUI(c , StageMenu ,StageTetris, Stagehelp); // inicio menu con los stage que puede iniciar y el controller tetris
        }

}
