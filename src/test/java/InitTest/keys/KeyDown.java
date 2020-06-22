package InitTest.keys;

import com.tetris.controller.GameController;
import com.tetris.model.Observer.DatosObservados;
import com.tetris.view.GuiController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;
import java.net.URL;

public class KeyDown  extends ApplicationTest {

    @Override public void start(Stage stage) throws IOException {
        URL location = getClass().getClassLoader().getResource("gameLayout.fxml"); //llama a gamelayut y guardar su direccion

        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = fxmlLoader.load();

        //controla el juego
        GuiController c = fxmlLoader.getController(); // no deberia ir esto aca
        DatosObservados statObservador = new DatosObservados();
        GameController controladorTetris = new GameController(c, statObservador);
        stage.show();


    }

    @Test
    public void testDownPressKey() throws Exception {
        URL location = getClass().getClassLoader().getResource("gameLayout.fxml"); //llama a gamelayut y guardar su direccion

        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = fxmlLoader.load();

        //controla el juego
        GuiController c = fxmlLoader.getController(); // no deberia ir esto aca
        DatosObservados statObservador = new DatosObservados();
        GameController controladorTetris = new GameController(c, statObservador);



        c.downKey();

    }

}
