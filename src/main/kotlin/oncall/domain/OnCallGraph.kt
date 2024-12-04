package oncall.domain

import oncall.constant.MonthInfo.Companion.getMonthInfo
import oncall.constant.DayOfWeek.Companion.getDayOfWeek
import oncall.constant.DayType
import oncall.constant.DayType.PUBLIC_HOLIDAY

class OnCallGraph(private val order: OnCallOrder, private val schedule: OnCallSchedule) {

    val graph: MutableList<Pair<String, String>> = mutableListOf()

    init {
        makeGraph()
    }

    private fun makeGraph() {
        val monthInfo = getMonthInfo(schedule.month)
        var currentDayOfWeek = getDayOfWeek(schedule.startDay)

        for (day in 1..monthInfo.end) {
            val dayType = DayType.convertToDayType(schedule.month, day, currentDayOfWeek)
            val dayOfWeek =
                if (dayType == PUBLIC_HOLIDAY) PUBLIC_HOLIDAY_FORMAT.format(currentDayOfWeek.day) else currentDayOfWeek.day
            graph.add(
                Pair(
                    DATE_FORMAT.format(schedule.month, day, currentDayOfWeek.day, dayOfWeek),
                    ""
                )
            )
            currentDayOfWeek = currentDayOfWeek.nextDay()
        }
    }

    companion object {
        private const val DATE_FORMAT = "%d월 %d일 %s"
        private const val PUBLIC_HOLIDAY_FORMAT = "%s(휴일)"
    }


}