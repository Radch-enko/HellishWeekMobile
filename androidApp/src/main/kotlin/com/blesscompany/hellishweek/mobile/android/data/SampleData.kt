package com.blesscompany.hellishweek.mobile.android.data

import com.blesscompany.hellishweek.common.utils.toPrettyWithMonthString
import com.blesscompany.hellishweek.features.home.ChallengeCardUI
import kotlinx.datetime.*

object SampleData {

    fun getChallenges(): List<ChallengeCardUI> {
        val mutableList = mutableListOf<ChallengeCardUI>()
        val now = Clock.System.now()
        val thisDate: LocalDate = now.toLocalDateTime(TimeZone.currentSystemDefault()).date
        val tomorrowDate: LocalDate =
            now.toLocalDateTime(TimeZone.currentSystemDefault()).date.plus(
                DatePeriod(days = 7)
            )
        repeat(10) { index ->
            mutableList.add(
                ChallengeCardUI(
                    id = index,
                    title = "Challenge $index",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                    startDate = thisDate.toPrettyWithMonthString(),
                    endDate = tomorrowDate.toPrettyWithMonthString()
                )
            )
        }
        return mutableList
    }
}
