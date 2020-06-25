package com.tetris.app;

import com.tetris.model.Observer.ObserverData;
import com.tetris.view.GuiHelp;
import com.tetris.view.GuiMenu;

import com.tetris.controller.GameController;
import com.tetris.model.Observer.guiPuntuationObserver;
import com.tetris.model.Observer.GuiStatsObservador;
import com.tetris.model.music.ReproduceAudio;
import com.tetris.model.music.ReproduceMusic;
import com.tetris.view.GuiController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;


public class Main extends Application {


    private ReproduceMusic ReproMusic = new ReproduceMusic();

    private ReproduceAudio ReproAudio = new ReproduceAudio();

    public static void main(String[] args) {


        launch(args);
    }

    @Override
    public void start(Stage StageMenu) throws Exception {

        //Archivos FXML
        URL locationMenu = getClass().getClassLoader().getResource("menuView.fxml");
        URL locationTetris = getClass().getClassLoader().getResource("gameLayout.fxml");
        URL locationGrafica = getClass().getClassLoader().getResource("newView.fxml");
        URL locationAyuda = getClass().getClassLoader().getResource("helpView.fxml");
        URL locationPuntuacionTotal = getClass().getClassLoader().getResource("punctuationTotalView.fxml");
        ResourceBundle resources = null;
        Image ico = new Image("icon.png");
        //
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

        GuiMenu menu = fxmlLoaderMenu.getController();
        GuiController c = fxmlLoaderTetris.getController();
        GuiStatsObservador s = fxmlLoaderGrafica.getController();
        GuiHelp help = fxmlLoaderAyuda.getController();
        guiPuntuationObserver puntuacion = fxmlLoaderPuntuacionTotal.getController();


        ///////////////////////////////////////////////INTERFAZ MENU////////////////////////////////////////////////////////

        StageMenu.setTitle("Menu");
        Scene sceneMenu = new Scene(rootMenu, 625, 493);
        StageMenu.setScene(sceneMenu);
        StageMenu.show();
        StageMenu.getIcons().add(ico);
        ////////////////////////////////////////////////INTERFAZ DE TETRIS////////////////////////////////////////////////////

        Stage StageTetris = new Stage();
        StageTetris.setTitle("TetrisFULL");
        Scene SceneTetris = new Scene(rootTetris, 420, 508);
        StageTetris.setScene(SceneTetris);
        StageTetris.getIcons().add(ico);
        //////////////////////////////////////////////INTERFAZ GRAFICA (Observador)////////////////////////////////////////////

        //   Stage StageGrafic = new Stage();
        Stage StageGrafic = new Stage();
        StageGrafic.setTitle("Grafico de estadistica Tetris");
        Scene sceneGrafica = new Scene(rootGrafica, 620, 450);
        StageGrafic.setScene(sceneGrafica);
        StageGrafic.getIcons().add(ico);
        c.graph(StageGrafic);
        ObserverData statObservador = new ObserverData();
        GuiStatsObservador ObserverDisplay = new GuiStatsObservador(statObservador, s);
        GameController tetrisController = new GameController(c, statObservador, ReproAudio);
        c.setMusic(ReproMusic, ReproAudio);

        ////////////////////////////////////////////////////INTERFAZ AYUDA/////////////////////////////////////////////////////

        Stage Stagehelp = new Stage();
        Stagehelp.setTitle("Ayuda");
        Stagehelp.setScene(new Scene(rootAyuda, 620, 450));
        Stagehelp.getIcons().add(ico);

        /////////////////////////////////////////////////INTERFAZ GRAFICA PUNTUACION (Observador)////////////////////////////////////

        Stage score = new Stage();
        score.setTitle("Puntuacion");
        score.setScene(new Scene(rootPuntuacionTotal, 400, 200));
        c.punctuationMethod(score);
        score.getIcons().add(ico);

        guiPuntuationObserver ObservadorDisplayTotal = new guiPuntuationObserver(statObservador, puntuacion);

        //////////////////////////////////////////////////////INICIO MENU//////////////////////////////////////////////////////////


        menu.GUI(c, StageMenu, StageTetris, Stagehelp, ReproMusic, ReproAudio); // inicio menu con los stage que puede iniciar y el controller tetris

        StageTetris.setOnCloseRequest(e -> Platform.exit());
    }

    @Override
    public void stop() {
        ReproMusic.stopMusic();
        guiPuntuationObserver.saveData();
    }
}
