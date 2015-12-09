package init;

/**
 * Created by Adriel on 10/8/2015.
 */
public class StanderHelper {

    public final static double APPLICATION_WIDTH = 870.0d;
    public final static double APPLICATION_HEIGHT = 510.0d;
    public final static String APPLICATION_NAME = "Battlefield";

    private final static String FXML_PATH = "/fxml";
    public final static String FXML_LOGINPATH = String.format("%s/loginFrame.fxml", FXML_PATH);
    public final static String FXML_SERVERPATH = String.format("%s/serverFrame.fxml", FXML_PATH);
    public final static String FXML_PROFILEPATH = String.format("%s/profileFrame.fxml", FXML_PATH);
    public final static String FXML_CHARACTERPATH = String.format("%s/characterFrame.fxml", FXML_PATH);
    public final static String FXML_HOMEPATH = String.format("%s/homeFrame.fxml", FXML_PATH);
    public final static String FXML_REGISTERPATH = String.format("%s/registerFrame.fxml", FXML_PATH);

    private final static String PATCH_IMG = "/img";

    public final static double DEFAULT_FONT_TEXT_SIZE = 14.0d;

    public final static String LOGINHEADER = "Login";
    public final static String PROFILEHEADER = "Profile";
    public final static String CHARACTERHEADER = "Characters";
    public final static String HOMEHEADER = "Home";
    public final static String REGISTERHEADER = "Registration";


    public final static String SERVER_ONLINE_IMGPATH = String.format("%s/online.png", PATCH_IMG);
    public final static String SERVER_OFFLINE_IMGPATH = String.format("%s/offline.png", PATCH_IMG);

    public final static String IMG_AVATARPATH = String.format("%s/avatars", PATCH_IMG);

    private final static String FONT_PATH = "/fonts";
    public final static String DEFAULT_TEXT_FONT_PATH = String.format("%s/OpenSans-Regular.ttf", FONT_PATH);


    public final static double SERVER_BUTTON_WIDTH = 300.0d;
    public final static double SERVER_BUTTON_HEIGHT= 50.0d;

    public static final double BALANCE = 0.0d;
    public static final int CHARACTER_SLOTS = 5;
    public static final int MONTHS_PAYED = 0;

    public final static double SERVER_IMAGE_HEIGHT = 15.0d;
    public final static double SERVER_IMAGE_WIDTH = 15.0d;
    public final static double AVATAR_IMGWIDTH = 100.0d;
    public final static double AVATAR_IMGHEIGHT = 80.0d;

    public static final String No_Value_STRING = "";

}