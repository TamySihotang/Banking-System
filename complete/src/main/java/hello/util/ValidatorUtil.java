package hello.util;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidatorUtil {


    public static final String REGEX_PRINT_CODE = "[C-Z]|[A-Z]{2,1000}";

    public static boolean isNotEmptyOrNull(Object obj) {
        if(obj == null) return false;
        if(obj instanceof String)
            return !((String) obj).isEmpty();
        else if (obj instanceof Collection)
            return ((Collection<?>) obj).size() != 0;
        return true;
    }

    public static boolean isEmptyOrNull(Object obj) {
        if(obj == null) return true;
        if(obj instanceof String)
            return ((String) obj).isEmpty();
        else if (obj instanceof Collection)
            return ((Collection<?>) obj).size() == 0;
        return false;
    }

    public static boolean regexValidate(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }
}
