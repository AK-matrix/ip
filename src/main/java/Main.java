import java.io.IOException;

import ak.AK;
import ak.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for AK using FXML.
 */
public class Main extends Application {

    private AK ak = new AK("./data/ak.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Tyrion - Hand of the Code");

            // Inject the AK instance
            fxmlLoader.<MainWindow>getController().setAk(ak);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
