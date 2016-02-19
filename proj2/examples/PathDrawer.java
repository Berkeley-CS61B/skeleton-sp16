import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * A JavaFX application that illustrates how to handle mouse drag events. When the mouse is dragged,
 * this application displays the path of the mouse.
 *
 * You'll only need to use this example if you implement selection.
 */
public class PathDrawer extends Application {

    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 500;

    /** An event handler that draws the path the mouse has taken while pressed. */
    private class MouseEventHandler implements EventHandler<MouseEvent> {
        Group root;

        /**
         * X position of the last mouse event in the current drag (not valid when the mouse is not
         * pressed).
         */
        double lastPositionX;
        /**
         * Y position of the last mouse event in the current drag (not valid when the mouse is not
         * pressed).
         */
        double lastPositionY;

        /**
         * Lines that were drawn along the mouse's path. Saved so we can remove the path when the
         * mouse is released.
         */
        ArrayList pathLines = new ArrayList<Line>();

        MouseEventHandler(Group root) {
            this.root = root;
            pathLines = new ArrayList<Line>();
        }


        @Override
        public void handle(MouseEvent mouseEvent) {
            double mousePressedX = mouseEvent.getX();
            double mousePressedY = mouseEvent.getY();
            EventType eventType = mouseEvent.getEventType();
            if (eventType == MouseEvent.MOUSE_PRESSED) {
                lastPositionX = mousePressedX;
                lastPositionY = mousePressedY;
            } else if (eventType == MouseEvent.MOUSE_DRAGGED) {
                // Draw a line for the path between the last mouse position and the current
                // position.
                Line path = new Line(lastPositionX, lastPositionY, mousePressedX, mousePressedY);
                root.getChildren().add(path);
                pathLines.add(path);

                lastPositionX = mousePressedX;
                lastPositionY = mousePressedY;
            } else if (eventType == MouseEvent.MOUSE_RELEASED) {
                // Remove the circles showing the path from the root.
                root.getChildren().removeAll(pathLines);
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a Node that will be the parent of all things displayed on the screen.
        Group root = new Group();
        // The Scene represents the window: its height and width will be the height and width
        // of the window displayed.
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);

        // Make an EventHandler that will be called anytime the mouse is pressed, dragged, or
        // released. Note that when the mouse is pressed and released on the same node, a
        // MOUSE_CLICKED event will be generated in addition to the MOUSE_PRESSED and MOUSE_RELEASED
        // events.
        MouseEventHandler mouseEventHandler = new MouseEventHandler(root);
        scene.setOnMousePressed(mouseEventHandler);
        scene.setOnMouseDragged(mouseEventHandler);
        scene.setOnMouseReleased(mouseEventHandler);

        // This is boilerplate, necessary to setup the window where things are displayed.
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
