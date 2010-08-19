package org.movealong.common;

/**
 * Created by IntelliJ IDEA.
 * User: inkblot
 * Date: Mar 3, 2010
 * Time: 6:43:06 PM
 */
public class ImpossibleException extends RuntimeException {
    public ImpossibleException(String message, Throwable cause) {
        super(message, cause);
    }
}
