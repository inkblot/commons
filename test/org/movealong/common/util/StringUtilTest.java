package org.movealong.common.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: inkblot
 * Date: Aug 18, 2010
 * Time: 6:59:35 AM
 */
public class StringUtilTest {

    @Test
    public void getMD5Base64() throws Exception {
        assertEquals("R7zlx09Yn0hn29V+nKn4CA==", StringUtil.getMD5Base64("aaa"));
        assertEquals("CPjgJgxkQYUQzvsrBu7lzQ==", StringUtil.getMD5Base64("bbb"));
        assertEquals("XUFAKrxLKna5cZ2REBfFkg==", StringUtil.getMD5Base64("hello"));
        assertEquals("6ZoYxCjLONXyYIU2eJIuAw==", StringUtil.getMD5Base64("abc123"));
        assertEquals("qQZEnVdp+nNh1+zGqj9tKA==", StringUtil.getMD5Base64("123abc"));
    }

}
