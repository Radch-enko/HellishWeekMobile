package com.blesscompany.hellishweek.common.utils;

object Validator {
    private const val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    private const val PASSWORD_REGEX =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"

    fun isEmailValid(email: String): Boolean {
        return EMAIL_REGEX.toRegex().matches(email);
    }

    fun isPasswordValid(password: String): Boolean {
        return PASSWORD_REGEX.toRegex().matches(password)
    }
}