package org.movealong.common;

import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: inkblot
 * Date: Sep 30, 2010
 * Time: 9:08:09 PM
 */
public class ImpossibleExceptionTest {

    @Test(expected = ImpossibleException.class)
    public void testThrow() throws Exception {
        throw new ImpossibleException("Not happening", new RuntimeException());
    }

}
