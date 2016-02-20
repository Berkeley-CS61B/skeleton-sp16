import javafx.application.Application;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

/**
 * Prints the current clipboard contents, and then replaces the current clipboard with
 * "I love 61B."
 */
public class ClipboardExample extends Application {
    private static final String NEW_CLIPBOARD_CONTENT = "I love 61B";

    @Override
    public void start(Stage primaryStage) {
        // Get the system clipboard (which will include anything that was copied in a different
        // application).
        Clipboard clipboard = Clipboard.getSystemClipboard();
        String stringContents = clipboard.getString();
        if (stringContents == null) {
            System.out.println("Nothing on clipboard!");
        } else {
            System.out.println("Old clipboard was: " + clipboard.getString());
        }

        // To set the clipboard content, you need to create a ClipboardContent object.
        ClipboardContent newContent = new ClipboardContent();
        newContent.putString(NEW_CLIPBOARD_CONTENT);

        // After calling setContent() on the system clipboard, "pasting" in another application
        // should paste the new contents of the clipboard (here, "I love 61B").
        clipboard.setContent(newContent);
        System.out.println("Set clipboard to be: " + NEW_CLIPBOARD_CONTENT);
    }

    public static void main(String[] args) {
        launch(args);
    }
}