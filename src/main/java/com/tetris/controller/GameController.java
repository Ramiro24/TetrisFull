package com.tetris.controller;

import com.tetris.view.GuiController;
import com.tetris.model.logic.*;

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
    /*
     * Posiblemente se crea en tiempo de ejecucion
     * Este es el sujeto a observar deberia tener una lista de observadores
     */
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//<<<<<<< HEAD
//public class GameController implements InputEventListener{ //clase que envia actualizaciones a DatosObservados mediante el objeto observador
/*
 * Posiblemente se crea en tiempo de ejecucion
 * Este es el sujeto a observar deberia tener una lista de observadores
 */
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	 private int Dificultad;
	 DatosObservados observador;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//=======
  

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//>>>>>>> f9e4a3f01ed480285b308815d74e304a2c33199a

    private Board board = new SimpleBoard(25, 10);


    private final GuiController viewGuiController;

    public GameController(GuiController c, DatosObservados observador) {
        this.observador = observador;
        viewGuiController = c;
        board.createNewBrick();
        viewGuiController.setEventListener(this);
//<<<<<<< HEAD
        viewGuiController.initGameView(board.getBoardMatrix(), board.getViewData(),Dificultad);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        viewGuiController.bindScore(board.getScore().scoreProperty());
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
 
//=======
 


    

    /* public void inicia() {
      viewGuiController.setEventListener(this);
      viewGuiController.initGameView(board.getBoardMatrix(), board.getViewData(),Dificultad);
      viewGuiController.bindScore(board.getScore().scoreProperty());
     }*/
//>>>>>>> f9e4a3f01ed480285b308815d74e304a2c33199a
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
            }
            if (board.createNewBrick()) {
                viewGuiController.gameOver();
            }
            viewGuiController.refreshGameBackground(board.getBoardMatrix());
        } else {
            if (event.getEventSource() == EventSource.USER) {
                board.getScore().add(1);
                // probando si cambia el estado en el que observa
                observador.setEstado();
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

    //  @Override
    //public void nuevaVentana() throws IOException {
    
    
    /*	 Parent root;
        try {
           
        	root = FXMLLoader.load(getClass().getClassLoader().getResource("NuevaVentana.fxml"));
        	ResourceBundle resources = null ;
//---------------------------------------------------------------------------------
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }*/
    //---------------------------------------------------------------------------------

    // Set the persons into the controller.
    //   BirthdayStatisticsController controller = Loader.getController();
    //   controller.setPersonData(personData);

    // Set the dialog icon.
    //     dialogStage.getIcons().add(new Image("file:resources/images/calendar.png"));

    //   dialogStage.show();

      /*  } catch (IOException e) {
            e.printStackTrace();
        }*/


    // }
    public void setDificultad(int d) {
        Dificultad = d;
    }

    @Override
    public void createNewGame() {
 //   	observador.BorrarEstado();
        board.newGame();
        viewGuiController.refreshGameBackground(board.getBoardMatrix());
    }
}
