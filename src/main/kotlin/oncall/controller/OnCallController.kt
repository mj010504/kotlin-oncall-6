package oncall.controller

import oncall.view.InputView
import oncall.view.InputView.getOnCallSchedule

class OnCallController {
    fun run() {
        val schedule = getOnCallSchedule()
    }


}

