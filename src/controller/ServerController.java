package controller;

/**
 * Created by Adriel on 10/8/2015.
 */
import javafx.event.ActionEvent;
import javafx.scene.Node;
import models.User;
import units.Constants;

public class ServerController extends MainController {

    public void disconnectServer(ActionEvent actionEvent) {

        getServer().setServerJoinUsers(getServer().getServerJoinUsers() - 1);

        User user = getServer().getUsers().iterator().next();

        getServer().getUsers().remove(user);
        boolean isUpdated = getServerService().updateServer(getServer());

        System.out.printf("Server is updated: %s", isUpdated);

        Node node = (Node) actionEvent.getSource();
        showScenery(node, Constants.FXML_HOMEPATH, Constants.HOMEHEADER, user);
    }
}
