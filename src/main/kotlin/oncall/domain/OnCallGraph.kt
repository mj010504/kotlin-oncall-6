package oncall.domain

import oncall.constant.MonthInfo.Companion.getMonthInfo
import oncall.constant.DayOfWeek.Companion.getDayOfWeek
import oncall.constant.DayType
import oncall.constant.DayType.PUBLIC_HOLIDAY
import oncall.constant.DayType.WEEKDAY

class OnCallGraph(private val order: OnCallOrder, private val schedule: OnCallSchedule) {

    val graph: MutableList<Pair<String, String>> = mutableListOf()
    init {
        makeGraph()
    }

    private fun makeGraph() {
        val monthInfo = getMonthInfo(schedule.month)
        val size = order.getOrderCount()
        var currentDayOfWeek = getDayOfWeek(schedule.startDay)
        var weekdayOrderIndex = 0
        var holidayOrderIndex = 0
        var prevPerson  = ""

        for (day in 1..monthInfo.end) {
            val dayType = DayType.convertToDayType(schedule.month, day, currentDayOfWeek)
            val person = if(dayType == WEEKDAY) {
                val tempIndex = weekdayOrderIndex
                weekdayOrderIndex = (weekdayOrderIndex + 1) % size
                getWeekDayOrderPerson(prevPerson, tempIndex)

            } else {
                val tempIndex = holidayOrderIndex
                holidayOrderIndex = (holidayOrderIndex + 1) % size
                getHolidayOrderPerson(prevPerson, tempIndex)
            }

            if (dayType != PUBLIC_HOLIDAY) {
                graph.add(
                    Pair(
                        DATE_FORMAT.format(schedule.month, day, currentDayOfWeek.day, currentDayOfWeek.day),
                        person
                    )
                )
            }
            else {
                graph.add(
                    Pair(
                        PUBLIC_HOLIDAY_DATE_FORMAT.format(schedule.month, day, currentDayOfWeek.day, currentDayOfWeek.day),
                        person
                    )
                )
            }
            currentDayOfWeek = currentDayOfWeek.nextDay()
            prevPerson = person

        }
    }

    private fun getWeekDayOrderPerson(prevPerson : String, index : Int) : String {
        if (prevPerson == order.getWeekdayOrderPerson(index)) order.adjustWeekDayOrder(index)
        return order.getWeekdayOrderPerson(index)
    }

    private fun getHolidayOrderPerson(prevPerson : String, index : Int) : String {
        if (prevPerson == order.getHolidayOrderPerson(index)) order.adjustHoliDayOrder(index)
        return order.getHolidayOrderPerson(index)
    }

    companion object {
        private const val DATE_FORMAT = "%d월 %d일 %s"
        private const val PUBLIC_HOLIDAY_DATE_FORMAT =  "%d월 %d일 %s(휴일)"
    }

}