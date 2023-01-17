package com.blesscompany.hellishweek.common.utils

fun String.isPasswordValid(): Boolean {
    return Validator.isPasswordValid(this)
}

fun String.isEmailValid(): Boolean {
    return Validator.isEmailValid(this)
}

