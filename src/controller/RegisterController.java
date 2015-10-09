package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.User;
import units.Constants;


/**
 * Created by Adriel on 10/8/2015.
 */

public class RegisterController extends MainController {

    @FXML
    public Label errorsLabel;

    @FXML
    public TextField userName_Field;
    @FXML
    public TextField first_Name_Field;
    @FXML
    public TextField last_Name_Field;
    @FXML
    public TextField iban_Field;
    @FXML
    public PasswordField password_Field;

    @FXML
    public Button registerBtn;

    @FXML
    public Button cancelBtn;

    boolean existedUserName;


    public void registerBtn(ActionEvent actionEvent) {

        String userName = userName_Field.getText();
        String firstName = first_Name_Field.getText();
        String lastName = last_Name_Field.getText();
        String password = password_Field.getText();
        String iban = iban_Field.getText();

        Node node = (Node) actionEvent.getSource();

        boolean verifications  = verification(userName, firstName, lastName, password, iban);

        if (verifications ) {
            userRegister(node, userName, firstName, lastName, password, iban);
        }

        errorsLabel.setText(!verifications  ? "You miss filling one field" : existedUserName ? String.format(" %s Already Exist in us database", userName) : Constants.No_Value_STRING);
    }

    public void cancelBtn(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        showScene(node, Constants.FXML_LOGINPATH, Constants.LOGINHEADER, null);
    }

    private void userRegister(Node node, String userName, String firstName, String lastName, String password, String iban) {

        existedUserName = seekingUser(userName);

        if (!existedUserName) {
            User newUsers = new User(userName, firstName, lastName, iban, password);
            setUser(newUsers);

            boolean isAdded = addUsers(newUsers);
            if (isAdded) {
                showScene(node, Constants.FXML_HOMEPATH, Constants.HOMEHEADER, getUser());
            }
        }
    }

    private boolean addUsers(User user) {
        return getUserService().addUser(user);
    }

    private boolean seekingUser(String userNameInput) {

        if (getUserList() == null) {
            return false;
        }

        int userList_Size = getUserList().size();

        if (getUserList() != null && userList_Size > 0) {
            for (User user : getUserList()) {
                String userName = user.getUsername();

                boolean userNameEqual  = userNameInput.equals(userName);
                if (userNameEqual) {
                    return true;
                }
            }
        }
        return false;
    }


}