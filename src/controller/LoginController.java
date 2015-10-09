package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.Server;
import models.User;
import units.Constants;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Adriel on 10/8/2015.
 */


public class LoginController extends MainController implements Initializable {

    @FXML
    public Label errorsLabel;

    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;

    public LoginController() {

        List<User> users = getUserService().userList();

        if (users != null && !users.isEmpty()) {
            ObservableList<User> list = FXCollections.observableList(users);
            setUserList(list);
        } else {
            System.out.println("Table user is empty!");
        }

        createServers();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameField.setText("adriel");
        passwordField.setText("password");
    }

    public void signBtn(ActionEvent actionEvent) {

        String userNameInput = usernameField.getText();
        String userPasswordInput = passwordField.getText();

        boolean userSingIn = userSignIn(userNameInput, userPasswordInput);

        if (userSingIn) {
            Node node = (Node) actionEvent.getSource();
            showScenery(node, Constants.FXML_HOMEPATH, Constants.HOMEHEADER, getUser());
        }
    }

    public void registerBtn(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        showScenery(node, Constants.FXML_REGISTERPATH, Constants.REGISTERHEADER, getUserList());
    }

    private boolean userSignIn(String userName, String userPassword) {

        boolean userSignedIn = false;
        User user = findUser(userName, userPassword);

        if (user != null) {
            userSignedIn = true;
            setUser(user);
        }

        errorsLabel.setText(user == null ? "Username or Password is not Correct!" : Constants.No_Value_STRING);

        return userSignedIn;
    }

    private void createServers() {

        List<Server> serverList = getServerService().ServerList();
        int totalServer = serverList == null ? 0 : serverList.size();

        if (totalServer <= 0) {

            Server server1 = new Server("189.30.62.1", "Server 1", "Japan", 100, 66);
            Server server2 = new Server("195.22.55.2", "Server 2", "China", 100, 100);
            Server server3 = new Server("192.98.48.3", "Server 3", "Usa", 100, 90);
            Server server4 = new Server("188.11.30.1", "Server 4", "Europe", 100, 25);

            boolean addServer;

            addServer = getServerService().addServer(server1);
            System.out.printf("Server 1 created: %s\n", addServer);
            addServer = getServerService().addServer(server2);
            System.out.printf("Server 2 created: %s\n", addServer);
            addServer = getServerService().addServer(server3);
            System.out.printf("Server 3 created: %s\n", addServer);
            addServer = getServerService().addServer(server4);
            System.out.printf("Server 4 created: %s\n", addServer);
        }
    }

    private User findUser(String userNameInput, String userPasswordInput) {

        if (getUserList() == null) {
            return null;
        }

        for (User user : getUserList()) {

            String userName = user.getUsername();
            String userPassword = user.getPassword();

            boolean userNameMatched = userNameInput.equals(userName);
            boolean userPasswordMatched = userPasswordInput.equals(userPassword);

            if (userNameMatched && userPasswordMatched) {
                return user;
            }
        }

        return null;
    }
}