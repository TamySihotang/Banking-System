package hello.util;

import hello.exception.NostraException;
import org.springframework.stereotype.Component;

/**
 * Created by antin on 1/7/16.
 */
@Component
public class ErrorUtil {


    private static String notFound;


    public void setNotFound(String notFound) {
        ErrorUtil.notFound = Constants.ERROR.NOT_FOUND;
    }


    public static NostraException notFoundError(String var) {
        return new NostraException(String.format(notFound, var));
    }

    /**
     *  You cant't add, the %s data with %s is %s already exist. You should be execute edit action.
     * @return
     */

    public static NostraException notAuthorize() {
        return new NostraException("You're not authorize.");
    }
}
