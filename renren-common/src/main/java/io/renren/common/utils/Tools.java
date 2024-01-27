package io.renren.common.utils;

import java.util.regex.Pattern;

public class Tools {
    public static boolean isEmail(String email) {
        String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }


}
