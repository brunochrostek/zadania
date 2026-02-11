package org.example;

public class FormValidator {

    public static boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        if (s.length() > 20) {
            return false;
        }
        return true;
    }
}