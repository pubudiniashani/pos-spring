package lk.ijse.posspring.util;

import java.util.regex.Pattern;

public class RegexProcess {

    public static boolean customerId(String noteId) {
        String regexForUserID = "^C\\d{3,}$\n";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        return regexPattern.matcher(noteId).matches();
    }
}
