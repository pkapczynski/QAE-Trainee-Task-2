package files;

import java.util.concurrent.ThreadLocalRandom;

public class helpers {

    public static int convertValueToSimpleInt(String stringValue) {
        String subStr = stringValue.substring(stringValue.lastIndexOf("$")+1);
        String newText = subStr.replace(".00", "").replace(",", "");
        int simpleIntValue = Integer.parseInt(newText);
        return simpleIntValue;
    }

    public static String getMinimumQuantityValue(String str) {
        String subStr = str.substring(str.lastIndexOf("is ")+3);
        String justThevalue = subStr.replace("!", "");
        return  justThevalue;
    }

    public static String generateRandomString() {
        int randomNum = ThreadLocalRandom.current().nextInt(1000000, 9999999 + 1);
        String randomString = String.valueOf(randomNum);
        return randomString;
    }
}
