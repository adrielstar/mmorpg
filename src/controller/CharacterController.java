package controller;

/**
 * Created by Adriel on 10/8/2015.
 */
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import models.Character;
import models.User;
import service.UserService;
import service.UserServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class CharacterController implements Initializable {

    //region UI controls

    @FXML
    private Button backBtn;
    @FXML
    private Label slotLabel;
    @FXML
    private FlowPane characterList;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox addCharacterBox;
    @FXML
    private VBox characterInfoBox;
    @FXML
    private Button addBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private ComboBox classBox;
    @FXML
    private ComboBox raceBox;
    @FXML
    private TextField levelField;
    @FXML
    private TextField nameField;
    @FXML
    private Label title;
    @FXML
    private Label characterName;
    @FXML
    private Label characterClass;
    @FXML
    private Label characterLevel;
    @FXML
    private Label characterRace;
    @FXML
    private ImageView avatarImg;

    //endregion

    //region Fields

    private User mUser;
    private final UserService mUserService = new UserServiceImpl();

    //endregion

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        final String backBtnImagePath = "/images/back_button.png";
        Image backImg = new Image(getClass().getResourceAsStream(backBtnImagePath));
        ImageView imageView = new ImageView(backImg);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        backBtn.setGraphic(imageView);

        addBtn.setOnAction(event -> {

            addCharacterToUser();

            showWindow(true, false, false);
            showTitle("Characters".toUpperCase());
        });

        cancelBtn.setOnAction(event -> {
            showWindow(true, false, false);
            showTitle("Characters".toUpperCase());
        });

        levelField.setEditable(false);

        classBox.setItems(FXCollections.observableArrayList("Guardin", "Assassin", "Archmage", "Necromancer", "Prophet", "Shaman", "Druid", "Ranger"));
        raceBox.setItems(FXCollections.observableArrayList("Human", "Gnome", "Dwarf", "Elf", "Eladin", "Tiefling", "Deva", "Goliath"));
    }

    public void loadData(User user) {

        mUser = user;

        Integer characterSlotsAvailable = mUser.getCharacterSlots();
        Integer characterSlotsUsed = mUser.getCharacters() == null ? 0 : mUser.getCharacters().size();
        slotLabel.setText(String.format("SLOTS USED %s/%s", characterSlotsUsed, characterSlotsAvailable));
        loadCharacterList(characterSlotsUsed, characterSlotsAvailable);
    }

    private void addCharacterToUser() {

        String name = nameField.getText();
        Object raceSelectedItem = raceBox.getSelectionModel().getSelectedItem();
        String race = raceSelectedItem != null ? raceSelectedItem.toString() : "";
        Object classSelectedItem = classBox.getSelectionModel().getSelectedItem();
        String characterClass = classSelectedItem != null ? classSelectedItem.toString() : "";
        Integer level = Integer.parseInt(levelField.getText());

        Character character = new Character(name, characterClass, race, level);

        mUser.setCharacter(character);
        boolean isAdded = mUserService.updateUser(mUser);

        Integer maxSlots = mUser.getCharacterSlots();
        Integer slotsUsed = mUser.getCharacters().size();

        if (isAdded && slotsUsed <= maxSlots) {
            updateAddedCharacterValues(character);
        }
    }

    private void updateAddedCharacterValues(Character character) {

        Button btn = constructCharacterBtn(getRandomAvatarPath());
        btn.setText(character.getCharacterName());

        Integer maxSlots = mUser.getCharacterSlots();
        Integer slotsUsed = mUser.getCharacters().size();

        slotLabel.setText(String.format("SLOTS USED %s/%s", slotsUsed, maxSlots));

        characterList.getChildren().add(0, btn);
        characterList.getChildren().remove(characterList.getChildren().size() - 1);
    }

    private void loadCharacterList(int slotsUsed, int maxSlots) {

        int slotsAvailable = maxSlots - slotsUsed;

        for (Character character : mUser.getCharacters()) {

            String path = getRandomAvatarPath();

            Button btn = constructCharacterBtn(path);
            btn.setText(character.getCharacterName());

            btn.setOnAction(event -> {

                showTitle(character.getCharacterName());

                avatarImg.setImage(new Image(getClass().getResourceAsStream(path)));
                characterRace.setText(character.getCharacterRace());
                characterClass.setText(character.getCharacterClass());
                characterLevel.setText(String.valueOf(character.getCharacterLevel()));

                showWindow(false, false, true);
            });

            characterList.getChildren().add(btn);
        }

        for (int i = 0; i < slotsAvailable; i++) {

            final String backBtnImagePath = "/images/add.png";
            Button btn = constructCharacterBtn(backBtnImagePath);

            btn.setOnAction(event -> {
                showAddCharacterMenu();
            });

            characterList.getChildren().add(btn);
        }
    }

    private void showAddCharacterMenu() {
        showTitle("Add character".toUpperCase());
        cleanFieldValues();
        showWindow(false, true, false);
    }

    private void cleanFieldValues() {
        Random random = new Random();
        String level = String.valueOf(1 + random.nextInt(99));
        levelField.setText(level);

        nameField.setText("");
        classBox.getSelectionModel().clearSelection();
        raceBox.getSelectionModel().clearSelection();
    }

    public void handleBackBtn(ActionEvent actionEvent) {

        System.out.println("Go to main scene");

        final String fxmlPath = "/fxml/home.fxml";
        final String title = "Home";

        goToScene(actionEvent, fxmlPath, title);
    }

    private Button constructCharacterBtn(String imagePath) {
        Button btn = new Button();
        btn.setPrefWidth(125);
        btn.setPrefHeight(125);
        btn.setContentDisplay(ContentDisplay.TOP);
        btn.setTextAlignment(TextAlignment.JUSTIFY);
        btn.setStyle("-fx-alignment: center;");

        Image backImg = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(backImg);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        btn.setGraphic(imageView);

        return btn;
    }

    private void showWindow(boolean showCharacterList, boolean showAddCharacterPanel, boolean showCharacterInfoBox) {
        characterInfoBox.setVisible(showCharacterInfoBox);
        scrollPane.setVisible(showCharacterList);
        addCharacterBox.setVisible(showAddCharacterPanel);
        slotLabel.setVisible(!showCharacterInfoBox);
    }

    private void showTitle(String header) {
        title.setText(header);
    }

    private void goToScene(ActionEvent actionEvent, String fxmlPath, String title) {
        Parent fxml = null;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/home.fxml"));
            loader.load();
            fxml = loader.getRoot();

            if (fxml != null) {

                Scene homepageScene = new Scene(fxml, 960, 600);
                Stage homepageStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                HomeController homepageController = loader.getController();
                homepageController.setCurrentUser(mUser);
                homepageStage.show();

                homepageStage.setTitle(String.format("%s - Battlefield", title));
                homepageStage.setScene(homepageScene);
                homepageStage.setResizable(false);
                homepageStage.show();
            } else {
                System.out.printf("Cannot navigate to the %s scene", title);
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

    private String getRandomAvatarPath() {
        Random randomAvatar = new Random();
        int avatarNr = 1 + randomAvatar.nextInt(9);

        return String.format("/images/avatars/%s.jpg", avatarNr);
    }
}