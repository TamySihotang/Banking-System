package hello.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Common Object utilities
 *
 * Created by andri.khrisharyadi@nostratech.com
 * on 3/20/14.
 */
public class ObjectUtil {

    /**
     * Extract long string value to be List of String
     * ex : [ aaa, bbb ]
     * @return List<String>
     */
    public static List<String> extractStringToList(String value, String splitter){

        String[] arrays = value.split(splitter);
        return Arrays.asList(arrays);

    }

    public static boolean isNotEmptyOrNotNull(Object obj) {

        if(obj == null) return false;

        if(obj instanceof String) {
            return !((String) obj).isEmpty();
        } else if (obj instanceof Collection) {
            return ((Collection<?>) obj).size() != 0;
        }

        return true;

    }

    public static boolean isEmptyOrNull(Object obj) {

        if(obj == null) return true;

        if(obj instanceof String) {
            return ((String) obj).isEmpty();
        } else if (obj instanceof Collection) {
            return ((Collection<?>) obj).size() == 0;
        }

        return false;

    }

}
