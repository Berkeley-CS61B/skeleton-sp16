import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** A JavaFX application that prints out when Control+a or Control+z is pressed. */
public class ControlKeyPrinter extends Application {
    private static final int WINDOW_WIDTH = 20;
    private static final int WINDOW_HEIGHT = 20;

    @Override
    public void start(Stage primaryStage) {
        // Create a Node that will be the parent of all things displayed on the screen.
        Group root = new Group();
        // The Scene represents the window: its height and width will be the height and width
        // of the window displayed.  Make a tiny window here since we're not going to display
        // anything.
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);

        // Register the event handler to be called for all KEY_PRESSED events (the KEY_TYPED events
        // don't work well for the control key, because the character typed is empty (and the
        // KeyCode isn't set for KEY_TYPED events).
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.isControlDown()) {
                    if (keyEvent.getCode() == KeyCode.A) {
                        System.out.println("User pressed Control+a");
                    } else if (keyEvent.getCode() == KeyCode.Z) {
                        System.out.println("User pressed Control+z");
                    }
                }
            }
        });

        primaryStage.setTitle("Key Press Printer");

        // This is boilerplate, necessary to setup the window where things are displayed.
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
