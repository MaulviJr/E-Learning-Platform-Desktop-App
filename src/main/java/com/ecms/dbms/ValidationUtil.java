/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecms.dbms;

/**
 *
 * @author abdul
 */
import java.util.regex.Pattern;

public class ValidationUtil {

    // Regex patterns
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String DOB_REGEX = "^\\d{4}-\\d{2}-\\d{2}$"; // yyyy-mm-dd
    private static final String CONTACT_REGEX = "^(\\+92|0)?3\\d{9}$"; // Pakistani mobile numbers
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$"; // at least 6 chars with letters + digits
    private static final String USERNAME_REGEX = "^[A-Za-z0-9_]{4,15}$"; // optional: only letters, numbers, underscores

    // Validate email
    public static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    // Validate date of birth
    public static boolean isValidDOB(String dob) {
        return Pattern.matches(DOB_REGEX, dob);
    }

    // Validate contact number
    public static boolean isValidContact(String contact) {
        return Pattern.matches(CONTACT_REGEX, contact);
    }

    // Validate password
    public static boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }

    // Validate username (optional)
    public static boolean isValidUsername(String username) {
        return Pattern.matches(USERNAME_REGEX, username);
    }
}
