package com.tetris.controller;

import com.tetris.controller.Strategy.Velocity;
import com.tetris.controller.Strategy.LevelVel;
import com.tetris.controller.Strategy.VelocityMore;
import com.tetris.view.GuiController;
import com.tetris.model.logic.*;
import com.tetris.model.music.ReproduceAudio;

import java.io.IOException;

import com.tetris.model.Observer.DatosObservados;
//import com.quirko.app.DatosObservados;
//import com.quirko.logic.Board;
//import com.quirko.logic.ClearRow;
//import com.quirko.logic.DownData;
//import com.quirko.logic.SimpleBoard;
//import com.quirko.logic.ViewData;
import com.tetris.model.events.EventSource;
import com.tetris.model.events.InputEventListener;
import com.tetris.model.events.MoveEvent;


public class GameController implements InputEventListener { //clase que envia actualizaciones a DatosObservados mediante el objeto observador

    private int Dificultad;
    private DatosObservados observador;
    private ReproduceAudio ReproAudio;
    private Velocity velBonus;

    private Board board = new SimpleBoard(25, 10);


    private final GuiController viewGuiController;

    public GameController(GuiController c, DatosObservados observador, ReproduceAudio ReproAudio) {
        this.ReproAudio = ReproAudio;
        this.observador = observador;
        viewGuiController = c;
        board.createNewBrick();
        viewGuiController.setEventListener(this);
        viewGuiController.initGameView(board.getBoardMatrix(), board.getViewData(), Dificultad);
        viewGuiController.bindScore(board.getScore().scoreProperty());
        velBonus = new LevelVel();
    }

    @Override
    public DownData onDownEvent(MoveEvent event) {
        boolean canMove = board.moveBrickDown();
        ClearRow clearRow = null;
        if (!canMove) {
            board.mergeBrickToBackground();
            clearRow = board.clearRows();
            if (clearRow.getLinesRemoved() > 0) {
                board.getScore().add(clearRow.getScoreBonus());
                observador.setEstadoBonus(clearRow.getScoreBonus());
                viewGuiController.Dificultad -= 30;
                velBonus.changeVelocity(viewGuiController);

                ReproAudio.Fx(1);
            }
            if (board.createNewBrick()) {
                viewGuiController.gameOver();
                ReproAudio.Fx(6);
            }
            viewGuiController.refreshGameBackground(board.getBoardMatrix());
        } else {
            if (event.getEventSource() == EventSource.USER) {
                board.getScore().add(1);
                ReproAudio.Fx(5); 
            }
        }
        return new DownData(clearRow, board.getViewData());
    }

    @Override
    public ViewData onLeftEvent(MoveEvent event) {
        board.moveBrickLeft();
        return board.getViewData();
    }

    @Override
    public ViewData onRightEvent(MoveEvent event) {
        board.moveBrickRight();
        return board.getViewData();
    }

    @Override
    public ViewData onRotateEvent(MoveEvent event) {
        board.rotateLeftBrick();
        return board.getViewData();
    }

    
    public void setDificultad(int d) {
        Dificultad = d;
    }

    @Override
    public void createNewGame() {
        board.newGame();
        viewGuiController.refreshGameBackground(board.getBoardMatrix());
    }
}
