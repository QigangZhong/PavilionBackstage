package com.pavilion.util;

import junit.framework.TestCase;

public class PasswordUtilTest extends TestCase {
    public void testPassword() throws Exception {
        System.out.println(PasswordUtil.getRandomPassword(8));
    }

}