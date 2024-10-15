package lk.ijse.posspring.util;

import java.util.regex.Pattern;

public class RegexProcess {

    public static boolean customerId(String customerId) {
        String regexForUserID = "^C\\d{3,}$\n";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        return regexPattern.matcher(customerId).matches();
    }

    public static boolean itemId(String itemId) {
        String regexForUserID = "^I\\d{3,}$\n";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        return regexPattern.matcher(itemId).matches();
    }





}
