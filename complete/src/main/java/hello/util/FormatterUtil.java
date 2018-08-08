package hello.util;

import org.apache.commons.lang.StringUtils;

/**
 * Created by antin on 1/28/16.
 */
public class FormatterUtil {

    public static String formatPhoneNumber(String phoneNumber) {
        String formatted = "";
        if (StringUtils.isEmpty(phoneNumber)) {
            return null;
        }
        char first = phoneNumber.charAt(0);
        if (first == '0' || first != '+') {
            formatted = phoneNumber.replaceFirst("" + first, "+62");
        } else {
            return phoneNumber;
        }
        return formatted;
    }
}
