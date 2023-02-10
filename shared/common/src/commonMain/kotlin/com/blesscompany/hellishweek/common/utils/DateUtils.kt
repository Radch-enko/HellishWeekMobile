package com.blesscompany.hellishweek.common.utils

import kotlinx.datetime.LocalDate

fun LocalDate.toPrettyString(): String {
    return "${if (this.dayOfMonth < 10) "0".plus(this.dayOfMonth) else this.dayOfMonth}" +
            "." +
            "${if (this.monthNumber < 10) "0".plus(this.monthNumber) else this.monthNumber}" +
            "." +
            "${this.year}"
}