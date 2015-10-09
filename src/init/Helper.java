package init;

/**
 * Created by Adriel on 10/8/2015.
 */
public class Helper {

    public static boolean asDouble(String amount) {
        try {
            Double balance = Double.parseDouble(amount);
            System.out.println(balance + "  number.");
            return true;
        } catch (NumberFormatException e) {
            System.out.println(amount + " not number.");
            return false;
        }
    }
}