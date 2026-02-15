package ak.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * An example of a custom control using FXML. This control represents a dialog
 * box consisting of an ImageView to represent the speaker's face and a
 * formatted text area containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private TextFlow dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        buildTextFlow(text);
        displayPicture.setImage(img);

        // Crop image to a square (focusing on top/center) to avoid stretching
        double width = img.getWidth();
        double height = img.getHeight();
        double size = Math.min(width, height);
        double x = (width - size) / 2;
        double y = 0; // Top alignment for portraits as requested

        displayPicture.setViewport(new javafx.geometry.Rectangle2D(x, y, size, size));

        // Clip the image to a circle
        Circle clip = new Circle(50, 50, 50);
        displayPicture.setClip(clip);
    }

    private void buildTextFlow(String text) {
        String[] parts = text.split("\\*\\*");
        for (int i = 0; i < parts.length; i++) {
            Text textNode = new Text(parts[i]);
            textNode.getStyleClass().add("dialog-text");
            // If index is odd, it's inside **...**, so make it bold.
            // Wait, split works like this:
            // "Hello **Name**!" -> ["Hello ", "Name", "!"]
            // 0: normal, 1: bold, 2: normal.
            // If string starts with **, e.g. "**Name** hello", -> ["", "Name",
            // " hello"]
            // 0: empty (normal), 1: bold, 2: normal.
            // This logic holds for standard markdown usage where ** is a
            // toggle.
            if (i % 2 == 1) {
                textNode.setStyle("-fx-font-weight: bold;");
            }
            dialog.getChildren().add(textNode);
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on
     * the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.getStyleClass().add("user-label");
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.getStyleClass().add("duke-label");
        return db;
    }

    public static DialogBox getDukeErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.dialog.getStyleClass().add("error-label");
        return db;
    }
}
