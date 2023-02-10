package com.blesscompany.hellishweek.common.utils

object PasswordUtil {

    fun isValidPassword(password: String): Boolean{
        return password.meetsRequirements
    }
}

private fun String.isLongEnough() = length >= 8
private fun String.hasEnoughDigits() = count(Char::isDigit) > 0
private fun String.isMixedCase() = any(Char::isLowerCase) && any(Char::isUpperCase)
private fun String.hasSpecialChar() = any { it in "!,+^" }

// you can decide which requirements need to be included (or make separate lists
// of different priority requirements, and check that enough of each have been met)
private val requirements = listOf(String::isLongEnough, String::hasEnoughDigits)
private val String.meetsRequirements get() = requirements.all { check -> check(this) }