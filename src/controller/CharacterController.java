package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import models.Character;
import service.CharacterService;
import service.CharacterServiceImpl;
import init.StanderHelper;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.List;
import java.util.Random;



/**
 * Created by Adriel on 10/8/2015.
 */

public class CharacterController extends MainController implements Initializable {

    @FXML
    private Label errorsLabel;
    @FXML
    private Button returnBtn;
    @FXML
    private Label slot_Label;
    @FXML
    private FlowPane listOfCharacterList;
    @FXML
    private GridPane panel;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox character_Box;

    @FXML
    private Button cancelBtn;
    @FXML
    private ComboBox class_Box;
    @FXML
    private ComboBox character_Race_DropdownBox;
    @FXML
    private TextField level_Field;
    @FXML
    private TextField character_Name_Field;

    @FXML
    private Button createBtn;

    private ObservableList<Character> cCharacterList;
    private CharacterService cCharacterService;

    public CharacterService getCharactersService() {
        return cCharacterService;
    }

    public void setCharacterService(CharacterService characterService) {
        cCharacterService = characterService;
    }

    public ObservableList<Character> getCharacterList() {

        return cCharacterList;
    }

    public void setCharacterList(ObservableList<Character> mCharacterList) {
        this.cCharacterList = mCharacterList;
    }

    public CharacterController() {

        setCharacterService(new CharacterServiceImpl());

        List<Character> characters = getCharactersService().CharacterList();

        if (characters != null && !characters.isEmpty()) {
            ObservableList<Character> list = FXCollections.observableList(characters);
            setCharacterList(list);
        } else {
            System.out.println("table characters is empty");
        }
    }

    @Override
        public void initialize(URL location, ResourceBundle resources) {

            cancelBtn.setOnAction(event ->
            {
                viewWindow(true, false);
                setTitle(StanderHelper.CHARACTERHEADER.toUpperCase());
            });

        level_Field.setEditable(false);

        class_Box.setItems(FXCollections.observableArrayList("Assault", "Sniper", "Recon", "Support", "Engineer", "Medic", "Comander", "Ranger"));
        character_Race_DropdownBox.setItems(FXCollections.observableArrayList("America", "Canada", "Holland", "France", "Pakistan", "Brazil", "Venezuela", "Japan"));
    }

    @Override
    public void load() {

        Integer character_Slots = getUser().getCharacterSlots();
        int available_slots = character_Slots == null ? 0 : character_Slots;

        Set<Character> characters = getUser().getCharacters();
        int slotsUsed = characters == null ? 0 : characters.size();

        String slotLabel = String.valueOf(available_slots - slotsUsed);
        slot_Label.setText(slotLabel);

        loadCharacters();
    }

    public void createCharacterBtn(ActionEvent actionEvent) {

        Integer available_slots = getUser().getCharacterSlots();
        Integer slotsUsed = getUser().getCharacters().size();

        boolean validated;
        boolean characterNameExists;

        String characterName = character_Name_Field.getText();

        String characterRace = (String) character_Race_DropdownBox.getSelectionModel().getSelectedItem();
        String characterClass = (String) class_Box.getSelectionModel().getSelectedItem();
        String characterLevel = level_Field.getText();

        validated = verification(characterName, characterRace, characterClass, characterLevel);
        characterNameExists = findCharacter(characterName);

        if (validated && !characterNameExists) {

            setTitle(StanderHelper.CHARACTERHEADER.toUpperCase());
            viewWindow(true, false);

            Character character = new Character(characterName, characterClass, characterRace, Integer.parseInt(characterLevel));

            getUser().setCharacter(character);

            boolean created = getUserService().updateUser(getUser());

            if (created) {
                createCharacter(character);

                String slotLabelText = String.valueOf(available_slots - slotsUsed - 1);
                slot_Label.setText(slotLabelText);
            }
        }

        errorsLabel.setText(available_slots < slotsUsed ? "You dont have any empty slots!" : !validated ? "You need fill all required field!" : characterNameExists ? String.format(" %s already exist", characterName) : StanderHelper.No_Value_STRING);
    }

    private void createCharacter(Character newCharacter) {

        Button btn = createCharacterBtn(getAvatars(), StanderHelper.AVATAR_IMGWIDTH, StanderHelper.AVATAR_IMGHEIGHT);
        btn.setText(String.format("%s\nLevel: %s\nClass: %s \nRace: %s",
                newCharacter.getCharacterName(),
                newCharacter.getCharacterLevel(),
                newCharacter.getCharacterClass(),
                newCharacter.getCharacterRace()));

        listOfCharacterList.getChildren().add(0, btn);
    }

    private void loadCharacters() {

        getUser().getCharacters().forEach(this::createCharacter);
    }

    public void creatNewCharacterBtn(ActionEvent actionEvent) {

        Integer maxSlots = getUser().getCharacterSlots();
        Integer slotsUsed = getUser().getCharacters().size();

        if (maxSlots == slotsUsed) {
            return;
        }

        setTitle("Add character".toUpperCase());

        cleanFieldValues();
        viewWindow(false, true);
    }

    private void cleanFieldValues() {
        Random random = new Random();
        String level = String.valueOf(1 + random.nextInt(99));
        level_Field.setText(level);

        errorsLabel.setText(StanderHelper.No_Value_STRING);
        character_Name_Field.setText(StanderHelper.No_Value_STRING);
        class_Box.getSelectionModel().clearSelection();
        character_Race_DropdownBox.getSelectionModel().clearSelection();
    }

    public void returnBtn(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        showScenery(node, StanderHelper.FXML_HOMEPATH, StanderHelper.HOMEHEADER, getUser());
    }

    private void viewWindow(boolean viewListOfCharacter, boolean viewCharacterPanel) {
        scrollPane.setVisible(viewListOfCharacter);
        character_Box.setVisible(viewCharacterPanel);
    }

    private boolean findCharacter(String characterNameInput) {
        int characterListSize = getCharacterList() == null ? 0 : getCharacterList().size();

        if (getCharacterList() != null && characterListSize > 0) {
            for (Character character : getCharacterList()) {
                String characterName = character.getCharacterName();

                boolean isCharacterNameMatched = characterNameInput.equals(characterName);
                if (isCharacterNameMatched) {
                    return true;
                }
            }
        }
        return false;
    }

    private Button createCharacterBtn(String imgPath, double imgWidth, double imgHeight) {
        Button characterBtn = new Button();
        characterBtn.setMaxWidth(400.0d);
        characterBtn.setMaxHeight(100.0d);
        characterBtn.setMinWidth(400.0d);
        characterBtn.setMinHeight(100.0d);
        characterBtn.setTextAlignment(TextAlignment.LEFT);

        ImageView avatarImgBtnLayout = createImgeBtn(imgPath, imgWidth, imgHeight);
        characterBtn.setGraphic(avatarImgBtnLayout);

        return characterBtn;
    }
}