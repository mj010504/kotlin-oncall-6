package oncall.domain

import oncall.constant.DayOfWeek
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
        val monthInfo = schedule.getMonthInfo()
        val orderCount = order.getOrderCount()
        var currentDayOfWeek = schedule.getDayOfWeek()
        var weekdayOrderIndex = 0
        var holidayOrderIndex = 0
        var prevPerson  = ""

        for (day in 1..monthInfo.end) {
            val dayType = DayType.convertToDayType(monthInfo.month, day, currentDayOfWeek)
            val person = if(dayType == WEEKDAY) {
                val tempIndex = weekdayOrderIndex
                weekdayOrderIndex = incrementIndex(weekdayOrderIndex, orderCount)
                getWeekDayOrderPerson(prevPerson, tempIndex)
            } else {
                val tempIndex = holidayOrderIndex
                holidayOrderIndex = incrementIndex(holidayOrderIndex, orderCount)
                getHolidayOrderPerson(prevPerson, tempIndex)
            }
            addToGraph(dayType, monthInfo.month, day, currentDayOfWeek, person)
            currentDayOfWeek = currentDayOfWeek.nextDay()
            prevPerson = person

        }
    }

    private fun addToGraph(
        dayType: DayType,
        month: Int,
        day: Int,
        currentDayOfWeek: DayOfWeek,
        person: String
    ) {
        val formattedDate = if (dayType != PUBLIC_HOLIDAY) {
            DATE_FORMAT.format(month, day, currentDayOfWeek.day, currentDayOfWeek.day)
        } else {
            PUBLIC_HOLIDAY_DATE_FORMAT.format(month, day, currentDayOfWeek.day, currentDayOfWeek.day)
        }
        graph.add(Pair(formattedDate, person))
    }

    private fun getWeekDayOrderPerson(prevPerson : String, index : Int) : String {
        if (prevPerson == order.getWeekdayOrderPerson(index)) order.adjustWeekDayOrder(index)
        return order.getWeekdayOrderPerson(index)
    }

    private fun getHolidayOrderPerson(prevPerson : String, index : Int) : String {
        if (prevPerson == order.getHolidayOrderPerson(index)) order.adjustHoliDayOrder(index)
        return order.getHolidayOrderPerson(index)
    }

    private fun incrementIndex(currentIndex: Int, size: Int): Int {
        return (currentIndex + 1) % size
    }

    companion object {
        private const val DATE_FORMAT = "%d월 %d일 %s"
        private const val PUBLIC_HOLIDAY_DATE_FORMAT =  "%d월 %d일 %s(휴일)"
    }

}