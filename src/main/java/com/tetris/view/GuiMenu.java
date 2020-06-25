package com.tetris.view;

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
    static boolean flag = true;
    static boolean externFlag;
    ReproduceMusic ReproMusic;
    ReproduceAudio audio;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button helpButton;
    @FXML
    private Button startButtom;
    @FXML
    private RadioButton hardButton;
    @FXML
    private RadioButton easyButton;
    @FXML
    private RadioButton middleButton;
    @FXML
    private ToggleButton soundButton;

    private GuiController gui;
    private Stage stage;
    private Stage primaryStage;
    private int difficult = 300;
    private Stage help;
    private GameController game;

    public static boolean EstadoMusic() {

        externFlag = !flag;
        return externFlag;
    }

    @FXML
    void help(ActionEvent event) {
        help.show();
    }

    @FXML
    public void start(ActionEvent event) {
        audio.Fx(3);
        gui.setDifficult(difficult);
        gui.newGame(event);
        stage.close();
        help.close();
        primaryStage.show();

    }

    @FXML
    void sound(ActionEvent event) {

        if (flag) {
            ReproMusic.ReproduceMusic(0);
            flag = false;
        } else {
            ReproMusic.stopMusic();
            flag = true;
        }

    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void GUI(GuiController c, Stage stage1, Stage primaryStage, Stage help, ReproduceMusic reproMusic, ReproduceAudio audio) {
        gui = c;
        stage = stage1;
        this.primaryStage = primaryStage;
        this.help = help;
        this.ReproMusic = reproMusic;
        this.audio = audio;

    }

    public void Game(GameController game) {
        this.game = game;
    }


    @FXML
    void hard(ActionEvent event) {
        difficult = 100;
        audio.Fx(3);
    }

    @FXML
    void easy(ActionEvent event) {
        difficult = 400;
        audio.Fx(3);
    }

    @FXML
    void middle(ActionEvent event) {
        difficult = 300;
        audio.Fx(3);
    }

}