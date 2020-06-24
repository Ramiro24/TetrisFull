package InitTest.keys;

import com.tetris.controller.GameController;
import com.tetris.model.Observer.ObserverData;
import com.tetris.model.logic.SimpleBoard;
import com.tetris.model.music.ReproduceAudio;
import com.tetris.view.GuiController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.net.URL;

public class KeyDown extends ApplicationTest {

    /*@Override public void start(Stage stage) throws IOException {
        URL location = getClass().getClassLoader().getResource("gameLayout.fxml"); //llama a gamelayut y guardar su direccion

        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = fxmlLoader.load();

        //controla el juego
        GuiController c = fxmlLoader.getController(); // no deberia ir esto aca
        ObserverData statObservador = new ObserverData();
        GameController controladorTetris = new GameController(c, statObservador);
        stage.show();


    }*/

    @Test
    public void testDownPressKey() throws Exception {
        URL location = getClass().getClassLoader().getResource("gameLayout.fxml"); //llama a gamelayut y guardar su direccion

        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = fxmlLoader.load();

        //controla el juego
        GuiController c = fxmlLoader.getController(); // no deberia ir esto aca
        ObserverData statObservador = new ObserverData();
        ReproduceAudio ReproAudio = new ReproduceAudio();
        GameController controladorTetris = new GameController(c, statObservador, ReproAudio);
        int[][] matrix = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        SimpleBoard.setCurrentStaticMatrix(matrix);


        c.downKey();

    }

}
