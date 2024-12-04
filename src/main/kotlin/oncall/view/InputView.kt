package oncall.view

import camp.nextstep.edu.missionutils.Console
import oncall.domain.OnCallOrder
import oncall.domain.OnCallSchedule
import oncall.utils.Validator.validateSchedule

object InputView {

    private const val ON_CALL_SCHEDULE_SCRIPT = "비상 근무를 배정할 월과 시작 요일을 입력하세요"
    private const val ON_CALL_WEEKDAY_ORDER_SCRIPT = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요"
    private const val ON_CALL_HOLIDAY_ORDER_SCRIPT = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요"

    fun getOnCallSchedule(): OnCallSchedule {
        while (true) {
            try {
                println(ON_CALL_SCHEDULE_SCRIPT)
                val schedule = Console.readLine()
                val splitSchedule = schedule.split(",")
                splitSchedule.validateSchedule()
                return OnCallSchedule(splitSchedule[0].toInt(), splitSchedule[1])

            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun getOnCallOrder() : OnCallOrder{
        while (true) {
            try {
                val weekdayOrder = getWeekDayOrder()
                val holidayOrder = getHoliDayOrder()
                return OnCallOrder(weekdayOrder, holidayOrder)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    private fun getWeekDayOrder() : MutableList<String> {
        println(ON_CALL_WEEKDAY_ORDER_SCRIPT)
        return Console.readLine().split(",").toMutableList()
    }

    private fun getHoliDayOrder() : MutableList<String> {
        println(ON_CALL_HOLIDAY_ORDER_SCRIPT)
        return Console.readLine().split(",").toMutableList()
    }
}