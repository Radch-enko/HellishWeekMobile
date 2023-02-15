package com.blesscompany.hellishweek.common.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

fun LocalDate.toPrettyString(): String {
    return "${if (this.dayOfMonth < 10) "0".plus(this.dayOfMonth) else this.dayOfMonth}" +
            "." +
            "${if (this.monthNumber < 10) "0".plus(this.monthNumber) else this.monthNumber}" +
            "." +
            "${this.year}"
}

/**
 * Method will return date in format: 15 February
 */
fun LocalDate.toPrettyWithMonthString(): String {
    val month = this.month.name
    return this.dayOfMonth.toString() + " " + month.first().uppercase() + month.substring(1)
        .lowercase()
}

/**
 * Method will return date in format: 22:35 / 10:35PM
 */
fun LocalDateTime.toPrettyTimeString(): String {
    return "${this.hour}:${this.minute}"
}