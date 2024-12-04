package oncall.view

import camp.nextstep.edu.missionutils.Console
import oncall.domain.OnCallSchedule
import oncall.utils.Validator.validateSchedule

object InputView {

    private const val OnCallScheduleScript = "비상 근무를 배정할 월과 시작 요일을 입력하세요"

    fun getOnCallSchedule() : OnCallSchedule {
        while(true) {
            try {
                println(OnCallScheduleScript)
                val schedule = Console.readLine()
                val splitSchedule = schedule.split(",")
                splitSchedule.validateSchedule()
                return OnCallSchedule(splitSchedule[0].toInt(), splitSchedule[1])

            } catch(e : IllegalArgumentException) {
                println(e.message)
            }
        }




    }
}