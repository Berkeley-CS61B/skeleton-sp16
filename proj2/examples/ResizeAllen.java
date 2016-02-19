import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * A JavaFX application that illustrates how to determine when the window size changed.
 */
public class ResizeAllen extends Application {
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_HEIGHT = 500;
    private final int MARGIN = 10;

    private int getDimensionInsideMargin(int outsideDimension) {
        return outsideDimension - 2 * MARGIN;
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a Node that will be the parent of all things displayed on the screen.
        Group root = new Group();
        // The Scene represents the window: its height and width will be the height and width
        // of the window displayed.
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);

        // Make an image of Allen.
        final Image allenImage = new Image("allen2.jpg");
        final ImageView allenView = new ImageView(allenImage);
        allenView.setFitHeight(getDimensionInsideMargin(WINDOW_HEIGHT));
        allenView.setFitWidth(getDimensionInsideMargin(WINDOW_WIDTH));
        allenView.setX(MARGIN);
        allenView.setY(MARGIN);
        root.getChildren().add(allenView);

        // Register listeners that resize Allen when the window is re-sized.
        // We're using some new syntax here to create a ChangeListener with an overridden
        // changed() method; this is called instantiating an "anonymous class."  If you're curious
        // to learn more about this syntax, try Googling "Java anonymous class".  Beware that
        // IntelliJ sometimes collapses code blocks like this! If this happens, you can click on
        // the "+" icon that's to the left of the code (and to the right of the line numbers) to
        // expand the code again.
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldScreenWidth,
                    Number newScreenWidth) {
                // Re-compute Allen's width.
                int newAllenWidth = getDimensionInsideMargin(newScreenWidth.intValue());
                allenView.setFitWidth(newAllenWidth);
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldScreenHeight,
                    Number newScreenHeight) {
                int newAllenHeight = getDimensionInsideMargin(newScreenHeight.intValue());
                allenView.setFitHeight(newAllenHeight);
            }
        });

        // This is boilerplate, necessary to setup the window where things are displayed.
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
