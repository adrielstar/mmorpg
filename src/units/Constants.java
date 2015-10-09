package units;

/**
 * Created by Adriel on 10/8/2015.
 */
public class Constants {

    public final static double APP_WIDTH = 870.0d;
    public final static double APP_HEIGHT = 510.0d;
    public final static String APP_NAME = "Battlefield";

    private final static String FXML_PATH = "/fxml";
    public final static String FXML_LOGINPATH = String.format("%s/loginFrame.fxml", FXML_PATH);
    public final static String SERVER_FXML_PATH = String.format("%s/serverFrame.fxml", FXML_PATH);
    public final static String PROFILE_FXML_PATH = String.format("%s/profileFrame.fxml", FXML_PATH);
    public final static String CHARACTER_FXML_PATH = String.format("%s/characterFrame.fxml", FXML_PATH);
    public final static String FXML_HOMEPATH = String.format("%s/startFrame.fxml", FXML_PATH);
    public final static String FXML_REGISTERPATH = String.format("%s/registerFrame.fxml", FXML_PATH);

    private final static String IMAGE_PATH = "/images";
    public final static String BACK_IMAGE_PATH = String.format("%s/back_button.png", IMAGE_PATH);
    public final static String ADD_BUTTON_IMAGE_PATH = String.format("%s/add.png", IMAGE_PATH);

    public final static String SERVER_AVAILABLE_IMAGE_PATH = String.format("%s/available.png", IMAGE_PATH);
    public final static String SERVER_FULL_IMAGE_PATH = String.format("%s/full.png", IMAGE_PATH);

    public final static String IMAGE_AVATAR_PATH = String.format("%s/avatars", IMAGE_PATH);

    private final static String FONT_PATH = "/fonts";
    public final static String DEFAULT_TEXT_FONT_PATH = String.format("%s/OpenSans-Regular.ttf", FONT_PATH);

    public final static double DEFAULT_FONT_TEXT_SIZE = 14.0d;

    public final static String LOGINHEADER = "Login";
    public final static String SERVER_SCENE_HEADER = "Server";
    public final static String PROFILE_SCENE_HEADER = "Profile";
    public final static String CHARACTER_SCENE_HEADER = "Characters";
    public final static String HOMEHEADER = "Home";
    public final static String REGISTERHEADER = "Registration";

    public final static double BACK_IMAGE_WIDTH = 25.0d;
    public final static double BACK_IMAGE_HEIGHT = 25.0d;
    public final static double SERVER_IMAGE_HEIGHT = 15.0d;
    public final static double SERVER_IMAGE_WIDTH = 15.0d;
    public final static double ADD_IMAGE_WIDTH = 15.0d;
    public final static double ADD_IMAGE_HEIGHT = 15.0d;
    public final static double AVATAR_IMAGE_WIDTH = 75.0d;
    public final static double AVATAR_IMAGE_HEIGHT = 75.0d;

    public final static double SERVER_BUTTON_WIDTH = 300.0d;
    public final static double SERVER_BUTTON_HEIGHT= 50.0d;

    public static final double BALANCE = 0.0d;
    public static final int CHARACTER_SLOTS = 5;
    public static final int MONTHS_PAYED = 0;

    public static final String No_Value_STRING = "";
}