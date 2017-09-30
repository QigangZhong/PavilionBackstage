package com.pavilion.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public static String encode(String input){
        return DigestUtils.md5Hex(input);
    }
}
