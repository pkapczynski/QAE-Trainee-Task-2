package files;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Mateusz Stolecki
 *
 * Below consist of helping metod used throughout the test
 *
 */
public class helpers {

    /** Method used for conversion of caught string to get just the proper value*/
    public static int convertValueToSimpleInt(String stringValue) {
        String subStr = stringValue.substring(stringValue.lastIndexOf("$")+1);
        String newText = subStr.replace(".00", "").replace(",", "");
        int simpleIntValue = Integer.parseInt(newText);
        return simpleIntValue;
    }

    /** Method used for conversion of caught string to get just the proper value*/
    public static String getMinimumQuantityValue(String str) {
        String subStr = str.substring(str.lastIndexOf("is ")+3);
        String justThevalue = subStr.replace("!", "");
        return  justThevalue;
    }

    /** Method used for generating random string from 1000000 to 9999999*/
    public static String generateRandomString() {
        int randomNum = ThreadLocalRandom.current().nextInt(1000000, 9999999 + 1);
        String randomString = String.valueOf(randomNum);
        return randomString;
    }
}
