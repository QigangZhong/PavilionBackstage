package com.pavilion.util;

import java.util.Random;

public class PasswordUtil {
    private static final String passwordPassword = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";

    public static String getRandomPassword(int lenth) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < lenth; i++) {
            int a = random.nextInt(61);
            sb.append(passwordPassword.substring(0, 61).substring(a, a+1));
        }
        return sb.toString();
    }
}
