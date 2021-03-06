import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load the font

        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));

        primaryStage.setTitle("Battlefield");
        primaryStage.setScene(new Scene(root, 960, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
