package com.tetris.view;

import com.tetris.controller.Strategy.ButtonAdd;
import com.tetris.controller.Strategy.ButtonLess;
import com.tetris.controller.Strategy.Velocity;
import com.tetris.model.logic.DownData;
import com.tetris.model.logic.ViewData;
import com.tetris.model.music.ReproduceAudio;
import com.tetris.model.music.ReproduceMusic;
//import com.quirko.gui.GameOverPanel;
//import com.quirko.gui.NotificationPanel;
//import com.quirko.logic.events.EventSource;
//import com.quirko.logic.events.EventType;
//import com.quirko.logic.events.InputEventListener;
//import com.quirko.logic.events.MoveEvent;
import com.tetris.model.events.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class GuiController implements Initializable {

    private static final int BRICK_SIZE = 20;

    private static final Boolean Boolean = null;
    /*
     * @FXML public Button closeButton;
     */
    @FXML
    private GridPane gamePanel;

    @FXML
    private Text scoreValue;

    @FXML
    private Group groupNotification;

    @FXML
    private GridPane nextBrick;

    @FXML
    private GridPane brickPanel;

    @FXML
    private ToggleButton pauseButton;

    @FXML
    private GameOverPanel gameOverPanel;

    @FXML
    private HighScorePanel highScorePanel;

    private Stage puntuacion;

    // public int Dificultad;

    ReproduceMusic audio;

    ReproduceAudio noise;

    boolean flagInPause = false;

    public double difficult;

    private Rectangle[][] displayMatrix;

    private InputEventListener eventListener;

    private Rectangle[][] rectangles;

    public Timeline timeLine;

    private Stage grafico;

    private final BooleanProperty isPause = new SimpleBooleanProperty();

    private final BooleanProperty isGameOver = new SimpleBooleanProperty();

    private ArrayList<Integer> highScore = new ArrayList<Integer>();

    private Velocity more;
    private Velocity less;
    boolean flag = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Font.loadFont(getClass().getClassLoader().getResource("digital.ttf").toExternalForm(), 38);
        gamePanel.setFocusTraversable(true);
        gamePanel.requestFocus();
        gamePanel.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (isPause.getValue() == Boolean.FALSE && isGameOver.getValue() == Boolean.FALSE) {
                    if (keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.A) {
                        leftKey();
                        keyEvent.consume();
                    }
                    if (keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.D) {
                        rightKey();
                        keyEvent.consume();
                    }
                    if (keyEvent.getCode() == KeyCode.UP || keyEvent.getCode() == KeyCode.W) {
                        upKey();
                        keyEvent.consume();

                    }
                    if (keyEvent.getCode() == KeyCode.DOWN || keyEvent.getCode() == KeyCode.S) {
                        downKey();
                        keyEvent.consume();
                    }
                }

                if (keyEvent.getCode() == KeyCode.N) {
                    newGame(null);
                }
                if (keyEvent.getCode() == KeyCode.P) {
                    pauseButton.selectedProperty().setValue(!pauseButton.selectedProperty().getValue());
                }
            }
        });
        gameOverPanel.setVisible(false);
        highScorePanel.setVisible(false);
        pauseButton.selectedProperty().bindBidirectional(isPause);
        pauseButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    timeLine.pause();
                    pauseButton.setText("Reanudar");
                } else {
                    timeLine.play();
                    pauseButton.setText("Pausa");
                }
            }
        });
        final Reflection reflection = new Reflection();
        reflection.setFraction(0.8);
        reflection.setTopOpacity(0.9);
        reflection.setTopOffset(-12);
        scoreValue.setEffect(reflection);
    }

    public void initGameView(int[][] boardMatrix, ViewData brick, int Dificultad) {

        displayMatrix = new Rectangle[boardMatrix.length][boardMatrix[0].length];
        for (int i = 2; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                displayMatrix[i][j] = rectangle;
                gamePanel.add(rectangle, j, i - 2);
            }
        }

        rectangles = new Rectangle[brick.getBrickData().length][brick.getBrickData()[0].length];
        for (int i = 0; i < brick.getBrickData().length; i++) {
            for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(getFillColor(brick.getBrickData()[i][j]));
                rectangles[i][j] = rectangle;
                brickPanel.add(rectangle, j, i);
            }
        }
        brickPanel.setLayoutX(gamePanel.getLayoutX() + brick.getxPosition() * brickPanel.getVgap()
                + brick.getxPosition() * BRICK_SIZE);
        brickPanel.setLayoutY(-42 + gamePanel.getLayoutY() + brick.getyPosition() * brickPanel.getHgap()
                + brick.getyPosition() * BRICK_SIZE);

        generatePreviewPanel(brick.getNextBrickData());
        less = new ButtonLess();
        more = new ButtonAdd();
    }

    private Paint getFillColor(int i) {
        Paint returnPaint;
        switch (i) {
            case 0:
                returnPaint = Color.TRANSPARENT;
                break;
            case 1:
                returnPaint = Color.AQUA;
                break;
            case 2:
                returnPaint = Color.BLUEVIOLET;
                break;
            case 3:
                returnPaint = Color.DARKGREEN;
                break;
            case 4:
                returnPaint = Color.YELLOW;
                break;
            case 5:
                returnPaint = Color.RED;
                break;
            case 6:
                returnPaint = Color.BEIGE;
                break;
            case 7:
                returnPaint = Color.BURLYWOOD;
                break;
            default:
                returnPaint = Color.WHITE;
                break;
        }
        return returnPaint;
    }

    private void generatePreviewPanel(int[][] nextBrickData) {
        nextBrick.getChildren().clear();
        for (int i = 0; i < nextBrickData.length; i++) {
            for (int j = 0; j < nextBrickData[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                setRectangleData(nextBrickData[i][j], rectangle);
                if (nextBrickData[i][j] != 0) {
                    nextBrick.add(rectangle, j, i);
                }
            }
        }
    }

    private void refreshBrick(ViewData brick) {
        if (isPause.getValue() == Boolean.FALSE) {
            brickPanel.setLayoutX(gamePanel.getLayoutX() + brick.getxPosition() * brickPanel.getVgap()
                    + brick.getxPosition() * BRICK_SIZE);
            brickPanel.setLayoutY(-42 + gamePanel.getLayoutY() + brick.getyPosition() * brickPanel.getHgap()
                    + brick.getyPosition() * BRICK_SIZE);
            for (int i = 0; i < brick.getBrickData().length; i++) {
                for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                    setRectangleData(brick.getBrickData()[i][j], rectangles[i][j]);
                }
            }
            generatePreviewPanel(brick.getNextBrickData());
        }
    }

    public void refreshGameBackground(int[][] board) {
        for (int i = 2; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                setRectangleData(board[i][j], displayMatrix[i][j]);
            }
        }
    }

    private void setRectangleData(int color, Rectangle rectangle) {
        rectangle.setFill(getFillColor(color));
        rectangle.setArcHeight(9);
        rectangle.setArcWidth(9);
    }

    public void moveDown(MoveEvent event) {
        if (isPause.getValue() == Boolean.FALSE) {
            DownData downData = eventListener.onDownEvent(event);
            if (downData.getClearRow() != null && downData.getClearRow().getLinesRemoved() > 0) {
                NotificationPanel notificationPanel = new NotificationPanel(
                        "+" + downData.getClearRow().getScoreBonus());
                groupNotification.getChildren().add(notificationPanel);
                notificationPanel.showScore(groupNotification.getChildren());
            }
            refreshBrick(downData.getViewData());
        }
        gamePanel.requestFocus();
    }

    public void setEventListener(InputEventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void bindScore(IntegerProperty integerProperty) {

        scoreValue.textProperty().bind(integerProperty.asString());
    }

    public void gameOver() {
        timeLine.stop();
        audio.stopMusic();
        gameOverPanel.setVisible(true);
        isGameOver.setValue(Boolean.TRUE);
        int i = Integer.parseInt(scoreValue.textProperty().getValue());
        saveData(scoreValue.textProperty().getValue());
        readData();
        isMax(highScore);
        if (i >= isMax(highScore)) {
            highScorePanel.setVisible(true);
        }

    }

    public void newGame(ActionEvent actionEvent) {
        timeLine.stop();

        if (flag) {
            audio.ReproduceMusic(0);
            flag = false;
        }
        if (!flag) {
            flag = true;
        }

        timeLine = new Timeline(new KeyFrame(Duration.millis(difficult),
                ae -> moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))));
        timeLine.setCycleCount(Timeline.INDEFINITE);

        gameOverPanel.setVisible(false);
        highScorePanel.setVisible(false);
        eventListener.createNewGame();
        gamePanel.requestFocus();
        timeLine.play();
        isPause.setValue(Boolean.FALSE);
        isGameOver.setValue(Boolean.FALSE);
    }

    public void pauseGame(ActionEvent actionEvent) {
        ///
        if (GuiMenu.EstadoMusic()) {

            if (flagInPause) {
                audio.ReproduceMusic(0);
                flagInPause = false;
            } else {
                audio.stopMusic();
                flagInPause = true;
            }

            gamePanel.requestFocus();
        }
    }

    @FXML
    public void close(ActionEvent actionEvent) throws IOException {
        isPause.setValue(Boolean.TRUE);
    }

    public void graph(Stage g) {
        grafico = g;
    }

    @FXML
    public void punctuationMethod(ActionEvent event) {
        noise.Fx(4);
        grafico.show();
    }

    @FXML
    public void PuntuacionTotal(ActionEvent event) {
        noise.Fx(4);
        puntuacion.show();
    }

    @FXML
    public void setDifficult(int difficult) {
        this.difficult = difficult;
        System.out.println("la dificultad que manejo es: " + this.difficult);
        timeLine = new Timeline(new KeyFrame(Duration.millis(this.difficult),
                ae -> moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))));
        timeLine.setCycleCount(Timeline.INDEFINITE);
    }

    @FXML
    public void velocityLess() {
        noise.Fx(5);
        difficult = difficult + 10;
        if (isPause.getValue() == Boolean.FALSE && isGameOver.getValue() == Boolean.FALSE) {
            less.changeVelocity(this);
        }

    }

    @FXML
    public void velocityMore() {
        noise.Fx(5);
        difficult = difficult - 10;
        if (isPause.getValue() == Boolean.FALSE && isGameOver.getValue() == Boolean.FALSE) {
            more.changeVelocity(this);
        }
    }

    public Timeline getTimeLine() {
        return timeLine;
    }

    public void punctuationMethod(Stage g) {
        puntuacion = g;
    }

    public void upKey() {
        refreshBrick(eventListener.onRotateEvent(new MoveEvent(EventType.ROTATE, EventSource.USER)));
    }

    public void downKey() {
        moveDown(new MoveEvent(EventType.DOWN, EventSource.USER));

    }

    public void leftKey() {
        refreshBrick(eventListener.onLeftEvent(new MoveEvent(EventType.LEFT, EventSource.USER)));
    }

    public void rightKey() {
        refreshBrick(eventListener.onRightEvent(new MoveEvent(EventType.RIGHT, EventSource.USER)));

    }

    public void setMusic(ReproduceMusic audio, ReproduceAudio ruidos) {
        this.audio = audio;
        this.noise = ruidos;
    }

    public void saveData(String value) {
        String filepath = "src/main/resources/log.txt";
        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(value);
            pw.flush();
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> readData() {
        Scanner INPUT_STREAM;

        try {

            File file = new File("src/main/resources/log.txt");
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
                highScore.add(i);

            }
            INPUT_STREAM.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return highScore;
    }

    public int isMax(ArrayList<Integer> v) {
        Integer i = Collections.max(v);
        return i;
    }
}
