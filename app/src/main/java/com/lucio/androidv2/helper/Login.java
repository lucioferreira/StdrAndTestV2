package com.lucio.androidv2.helper;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {
    public boolean isValidUsername(String user) {
        if (user.isEmpty()) {
            return false;
        }
        return (isValidCpf(user) || isValidEmail(user));
    }

    private boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean isValidCpf(String cpf) {
        Pattern pattern = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}-?[0-9]{2}");
        Matcher matcher = pattern.matcher(cpf);
        return matcher.find();
    }

    public boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("(?=.*[A-Z])(?=.*[@$%&#])(?=.*[0-9a-z])");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}
