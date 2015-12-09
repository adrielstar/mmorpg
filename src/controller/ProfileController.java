package controller;

import init.Helper;
import init.StanderHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.User;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Adriel on 10/8/2015.
 */


public class ProfileController extends MainController {

    @FXML
    public Label errorsLabel;
    @FXML
    public Button cancelBtn;

    @FXML
    public Label username_Label;
    @FXML
    public Label balance_Label;
    @FXML
    public Label balanceEuro_Label;
    @FXML
    public Label iban_Label;
    @FXML
    public Label characterSlots_Label;
    @FXML
    public Label monthsPayed_Label;

    @FXML
    public TextField balance_Field;
    @FXML
    public ChoiceBox characterBox;
    @FXML
    public ChoiceBox subscrBox;



    @FXML
    public void initialize() {

    }

    @Override
    public void load() {

        String username = getUser().getUsername();
        String userFirstName = getUser().getFirstName();
        String userLastName = getUser().getLastName();
        String iban = getUser().getIban();
        String balance = String.valueOf(getUser().getBalance());
        String userCharacterSlots = String.valueOf(getUser().getCharacterSlots());
        String monthsPayed = String.valueOf(getUser().getMonthsPayed());
        String balanceEuro = "\u20ac";

        username_Label.setText(username);
        balance_Label.setText(balance);
        balanceEuro_Label.setText(balanceEuro);
        iban_Label.setText(iban);
        characterSlots_Label.setText(userCharacterSlots);
        monthsPayed_Label.setText(monthsPayed);

        String header = String.format("Hi, %s %s!", userFirstName, userLastName);
        setTitle(header);
    }


    public void cancelBtn(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        showScenery(node, StanderHelper.FXML_HOMEPATH, StanderHelper.HOMEHEADER, getUser());
    }

    public void moneyBtn(ActionEvent actionEvent) {

        String amountInput = balance_Field.getText();
        String warning = validateBalance(amountInput);

        if (warning == null) {
            double userBalance = getUser().getBalance();
            double amount = Double.parseDouble(amountInput);
            double newBalance = Math.round((userBalance + amount)) * 100.0 / 100.0;

            getUser().setBalance(newBalance);

            boolean isUpdated = updateUser(getUser());
            if (isUpdated) {
                balance_Label.setText(String.valueOf(newBalance));
            }
        }

        errorsLabel.setText(warning != null ? warning : StanderHelper.No_Value_STRING);
        balance_Field.clear();
    }

    public void slotBtn(ActionEvent actionEvent) {

        String selectedItem = (String) characterBox.getSelectionModel().getSelectedItem();
        int amount;

        switch (selectedItem) {
            case "1 Slot":
            default:
                amount = 1;
                break;
            case "2 Slots":
                amount = 2;
                break;
            case "3 Slots":
                amount = 3;
                break;
            case "4 Slots":
                amount = 4;
                break;
            case "5 Slots":
                amount = 5;
                break;
        }

        if (getUser().getBalance() >= amount) {
            int newCharacterSlots = getUser().getCharacterSlots() + amount;

            Double newBalance = getUser().getBalance() - amount;
            getUser().setCharacterSlots(newCharacterSlots);
            getUser().setBalance(newBalance);

            boolean isUpdated = updateUser(getUser());

            if (isUpdated) {
                balance_Label.setText(String.valueOf(newBalance));
                characterSlots_Label.setText(String.valueOf(newCharacterSlots));
            }
        }

        errorsLabel.setText(getUser().getBalance() < amount ? "You don't have money on your account" : StanderHelper.No_Value_STRING);
    }

    public boolean updateUser(User user) {
        return getUserService().updateUser(user);
    }

    private void addAmount(int months, int balance) {
        if (getUser().getBalance() >= balance) {
            int monthsPayed = getUser().getMonthsPayed() + months;
            double newBalance = getUser().getBalance() - balance;
            Timestamp lastPayment = new Timestamp(new Date().getTime());

            getUser().setMonthsPayed(monthsPayed);
            getUser().setLastPayment(lastPayment);
            getUser().setBalance(newBalance);

            boolean isUpdated = updateUser(getUser());
            if (isUpdated) {
                monthsPayed_Label.setText(String.valueOf(monthsPayed));
                balance_Label.setText(String.valueOf(newBalance));
            }
        }

        errorsLabel.setText(getUser().getBalance() < balance ? "You don't have enough money on your account" : StanderHelper.No_Value_STRING);
    }

    public void subscriptionBtn() {
        String selectedItem = (String) subscrBox.getSelectionModel().getSelectedItem();

        int months;
        int balance;

        switch (selectedItem) {
            case "1 Month":
            default:
                months = 1;
                balance = 5;
                break;
            case "2 Months":
                months = 2;
                balance = 8;
                break;
            case "3 Months":
                months = 3;
                balance = 10;
                break;
            case "1 Year":
                months = 12;
                balance = 35;
                break;
        }

        addAmount(months, balance);
    }

    private String validateBalance(String value) {
        if (value == null || value.isEmpty()) {
            return "Add amount to your bank account!";
        } else if (!Helper.asDouble(value)) {
            return "Please enter a number!";
        } else if (Double.parseDouble(value) <= 0) {
            return "Please enter a number greater than zero!";
        }

        return null;
    }
}