package oncall.domain

import oncall.constant.MonthInfo.Companion.getMonthInfo
import oncall.constant.DayOfWeek.Companion.getDayOfWeek
import oncall.constant.DayType
import oncall.constant.DayType.PUBLIC_HOLIDAY
import oncall.constant.DayType.WEEKDAY
import oncall.utils.swap

class OnCallGraph(private val order: OnCallOrder, private val schedule: OnCallSchedule) {

    val graph: MutableList<Pair<String, String>> = mutableListOf()
    init {
        makeGraph()
    }

    private fun makeGraph() {

        val monthInfo = getMonthInfo(schedule.month)
        val size = order.weekdayOrder.size

        var currentDayOfWeek = getDayOfWeek(schedule.startDay)
        var weekdayOrderIndex = 0
        var holidayOrderIndex = 0
        var prevPerson  = ""

        for (day in 1..monthInfo.end) {
            val dayType = DayType.convertToDayType(schedule.month, day, currentDayOfWeek)
            val dayOfWeek = if (dayType == PUBLIC_HOLIDAY) PUBLIC_HOLIDAY_FORMAT.format(currentDayOfWeek.day) else currentDayOfWeek.day
            val person = if(dayType == WEEKDAY) {
                val tempIndex = weekdayOrderIndex
                weekdayOrderIndex = (weekdayOrderIndex + 1) % size
                getWeekDayOrderPerson(prevPerson, tempIndex)

            } else {
                val tempIndex = weekdayOrderIndex
                holidayOrderIndex = (holidayOrderIndex + 1) % size
                getHolidayOrderPerson(prevPerson, tempIndex)
            }

            graph.add(
                Pair(
                    DATE_FORMAT.format(schedule.month, day, currentDayOfWeek.day, dayOfWeek),
                    person
                )
            )
            currentDayOfWeek = currentDayOfWeek.nextDay()
            prevPerson = person

        }
    }

    private fun getWeekDayOrderPerson(prevPerson : String, weekdayOrderIndex : Int) : String {
        if (prevPerson == order.weekdayOrder[weekdayOrderIndex]) adjustWeekDayOrder(weekdayOrderIndex)
        return order.weekdayOrder[weekdayOrderIndex]
    }

    private fun getHolidayOrderPerson(prevPerson : String, holidayOrderIndex : Int) : String {
        if (prevPerson == order.weekdayOrder[holidayOrderIndex]) adjustHolidayOrder(holidayOrderIndex)
        return order.holidayOrder[holidayOrderIndex]
    }

    private fun adjustWeekDayOrder(weekdayOrderIndex: Int) {
        order.weekdayOrder.swap(weekdayOrderIndex)
    }

    private fun adjustHolidayOrder(holidayOrderIndex: Int) {
        order.holidayOrder.swap(holidayOrderIndex)
    }

    companion object {
        private const val DATE_FORMAT = "%d월 %d일 %s"
        private const val PUBLIC_HOLIDAY_FORMAT = "%s(휴일)"
    }

}