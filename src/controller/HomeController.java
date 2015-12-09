package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import models.Server;
import init.StanderHelper;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import java.util.List;

/**
 * Created by Adriel on 10/8/2015.
 */


public class HomeController extends MainController {

    @FXML
    public Label errorsLabel;

    @FXML
    public VBox serverBoxs;

    @FXML
    public void initialize() {

        List<Server> servers = getServerService().ServerList();
        servers.forEach(this::create_Servers);
    }

    private void create_Servers(Server server) {

        String server_Name = server.getServerName();
        String server_Location = server.getServerLocation();
        int online_Users = server.getServerJoinUsers();
        int totalUsers = server.getServerTotalUsers();
        boolean onlineServer = online_Users < totalUsers;
        String imgPath = onlineServer ? StanderHelper.SERVER_ONLINE_IMGPATH : StanderHelper.SERVER_OFFLINE_IMGPATH;

        Button serverBtn = create_ServerBtn(server_Name, server_Location, online_Users, totalUsers, imgPath);
        serverBtn.setDisable(!onlineServer);

        serverBtn.setOnAction(actionEvent -> {

            boolean join = joinServer(server);

            if (join) {
                Node node = (Node) actionEvent.getSource();
                showScenery(node, StanderHelper.FXML_SERVERPATH, server_Name, getServer());
            }
        });

        serverBoxs.getChildren().add(0, serverBtn);
    }

    private boolean joinServer(Server server) {

        int charactersSize = getUser().getCharacters().size();

        if (charactersSize > 0) {
            setServer(server);

            getServer().setUsers(getUser());
            int connectedUsers = getServer().getServerJoinUsers() + 1;
            getServer().setServerJoinUsers(connectedUsers);

            return getServerService().updateServer(getServer());
        }

        errorsLabel.setText("You need to create Characters first!!");

        return false;
    }

    private Button create_ServerBtn(String server_Name, String server_Location, Integer onlineUsers, Integer totalUsers, String imgPath) {
        Button serverBtn = new Button();

        serverBtn.setPrefSize(StanderHelper.SERVER_BUTTON_WIDTH, StanderHelper.SERVER_BUTTON_HEIGHT);
        serverBtn.setText(String.format("%s [%s] Users: %s/%s", server_Name, server_Location, onlineUsers, totalUsers));

        ImageView avatarImgBtnLayout = createImgeBtn(imgPath, StanderHelper.SERVER_IMAGE_WIDTH, StanderHelper.SERVER_IMAGE_HEIGHT);
        serverBtn.setGraphic(avatarImgBtnLayout);

        return serverBtn;
    }

    public void load() {
        String user_FirstName = getUser().getFirstName();
        String user_LastName = getUser().getLastName();

        String header = String.format("Hi, %s %s!", user_FirstName, user_LastName);
        setTitle(header);
    }

    public void logoutBtn(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        showScenery(node, StanderHelper.FXML_LOGINPATH, StanderHelper.LOGINHEADER, null);
    }

    public void profileBtn(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        showScenery(node, StanderHelper.FXML_PROFILEPATH, StanderHelper.PROFILEHEADER, getUser());
    }

    public void characterBtn(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        showScenery(node, StanderHelper.FXML_CHARACTERPATH, StanderHelper.CHARACTERHEADER, getUser());
    }

}