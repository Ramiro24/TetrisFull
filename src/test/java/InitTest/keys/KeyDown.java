package InitTest.keys;

import com.tetris.controller.GameController;
import com.tetris.model.Observer.DatosObservados;
import com.tetris.view.GuiController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.Test;

import java.net.URL;

public class KeyDown {
    @Test
    public void testDownPressKey() throws Exception {
        URL locationTetris = getClass().getClassLoader().getResource("gameLayout.fxml");
        FXMLLoader fxmlLoaderTetris = new FXMLLoader(locationTetris);
        Parent rootTetris = fxmlLoaderTetris.load();
        GuiController c = fxmlLoaderTetris.getController();
        DatosObservados statObservador = new DatosObservados();
        GameController controladorTetris = new GameController(c, statObservador);
       // c.downKey();

    }

}
