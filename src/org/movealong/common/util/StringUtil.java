package org.movealong.common.util;

import com.Ostermiller.util.Base64;
import org.movealong.common.ImpossibleException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by IntelliJ IDEA.
 * User: inkblot
 * Date: Mar 3, 2010
 * Time: 6:49:03 PM
 */
public class StringUtil {

    private StringUtil INSTANCE = new StringUtil();
    private StringUtil() {
    }

    public static String getMD5Base64(String cleartext) {
        try {
            return Base64.encodeToString(MessageDigest.getInstance("MD5").digest(cleartext.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new ImpossibleException("It's MD5, of course it exists!", e);
        }
    }

}
