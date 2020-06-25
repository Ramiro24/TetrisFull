package com.tetris.controller;

import com.tetris.controller.Strategy.Velocity;
import com.tetris.controller.Strategy.LevelVel;
import com.tetris.model.Observer.ObserverData;
import com.tetris.view.GuiController;
import com.tetris.model.logic.*;
import com.tetris.model.music.ReproduceAudio;

//import com.quirko.app.ObserverData;
//import com.quirko.logic.Board;
//import com.quirko.logic.ClearRow;
//import com.quirko.logic.DownData;
//import com.quirko.logic.SimpleBoard;
//import com.quirko.logic.ViewData;
import com.tetris.model.events.EventSource;
import com.tetris.model.events.InputEventListener;
import com.tetris.model.events.MoveEvent;


public class GameController implements InputEventListener { //clase que envia actualizaciones a ObserverData mediante el objeto observador

    private int difficultGameController;
    private ObserverData observer;
    private ReproduceAudio reproAudio;
    private Velocity velBonus;

    private Board board = new SimpleBoard(25, 10);


    private final GuiController viewGuiController;

    public GameController(GuiController c, ObserverData observer, ReproduceAudio ReproAudio) {
        this.reproAudio = ReproAudio;
        this.observer = observer;
        viewGuiController = c;
        board.createNewBrick();
        viewGuiController.setEventListener(this);
        viewGuiController.initGameView(board.getBoardMatrix(), board.getViewData(), difficultGameController);
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
                observer.setStateBonus(clearRow.getScoreBonus());
                viewGuiController.difficult -= 30;
                velBonus.changeVelocity(viewGuiController);

                reproAudio.Fx(1);
            }
            if (board.createNewBrick()) {
                viewGuiController.gameOver();
                reproAudio.Fx(6);
            }
            viewGuiController.refreshGameBackground(board.getBoardMatrix());
        } else {
            if (event.getEventSource() == EventSource.USER) {
                board.getScore().add(1);
                observer.setState();
                reproAudio.Fx(5);
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

    
    public void setDifficultGameController(int d) {
        difficultGameController = d;
    }

    @Override
    public void createNewGame() {
        board.newGame();
        viewGuiController.refreshGameBackground(board.getBoardMatrix());
    }
}
