package com.pavilion.util;

import junit.framework.TestCase;

public class MD5UtilTest extends TestCase {
    public void testEncode() throws Exception {
        String md5=MD5Util.encode("zqg261607");
        System.out.println(md5);
    }

}