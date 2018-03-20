package com.capco.technologies.living.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by sachin on 06/01/18.
 */

public class AppValidator {

    // static Pattern object, since pattern is fixed
    private static Pattern emailPattern = Pattern.compile(AppConstants.EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    private static Pattern ipPattern = Pattern.compile(AppConstants.HOST_OR_IP_REGEX, Pattern.CASE_INSENSITIVE);

    // non-static Matcher object because it's created from the input String


    public static boolean isValidEmail(String email) {
        if (TextUtils.isEmpty(email))
            return false;

        return emailPattern.matcher(email).matches();
    }

    public static boolean isValidIP(String ip) {
        return ipPattern.matcher(ip).matches();
    }

}
