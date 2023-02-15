package com.blesscompany.hellishweek.mobile.android.data

import com.blesscompany.hellishweek.common.utils.toPrettyTimeString
import com.blesscompany.hellishweek.common.utils.toPrettyWithMonthString
import com.blesscompany.hellishweek.features.home.ChallengeCardUI
import com.blesscompany.hellishweek.features.notifications.presentation.NotificationCardUI
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

    fun getNotifications(): List<NotificationCardUI> {
        val mutableList = mutableListOf<NotificationCardUI>()

        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        repeat(10) { index ->
            mutableList.add(
                NotificationCardUI(
                    type = "Title of notification $index",
                    author = "Author $index",
                    description = "Description $index",
                    date = now.toPrettyTimeString()
                )
            )
        }

        return mutableList
    }
}
