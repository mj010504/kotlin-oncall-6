package oncall.controller

import oncall.domain.OnCallGraph
import oncall.view.InputView
import oncall.view.InputView.getOnCallOrder
import oncall.view.InputView.getOnCallSchedule

class OnCallController {
    fun run() {
        val schedule = getOnCallSchedule()
        val order = getOnCallOrder()
        val graph = OnCallGraph(order, schedule)
    }


}

