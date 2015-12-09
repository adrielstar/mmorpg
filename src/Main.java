import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import init.StanderHelper;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Font.loadFont(getClass().getResource(StanderHelper.DEFAULT_TEXT_FONT_PATH).toExternalForm(), StanderHelper.DEFAULT_FONT_TEXT_SIZE);

        showScene(primaryStage, StanderHelper.FXML_LOGINPATH, StanderHelper.LOGINHEADER);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void showScene(Stage primaryStage, String fxmlPath, String header) {
        URL navigationUrl = getClass().getResource(fxmlPath);

        Parent root = null;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(navigationUrl);

        try {
            loader.load();
            root = loader.getRoot();
        } catch (IOException e) {
            System.out.printf("\n--- %s ---\n", e.getMessage());
            e.printStackTrace();
        }

        if (root != null) {
            Scene rootScene = new Scene(root, StanderHelper.APPLICATION_WIDTH, StanderHelper.APPLICATION_HEIGHT);
            String title = String.format("%s - %s", header, StanderHelper.APPLICATION_NAME);

            primaryStage.setTitle(title);
            primaryStage.setScene(rootScene);
            primaryStage.setResizable(false);

            primaryStage.show();
        }
    }
}