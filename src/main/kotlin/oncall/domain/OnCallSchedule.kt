package oncall.domain

import oncall.constant.DayOfWeek
import oncall.constant.MAX_MONTH
import oncall.constant.MIN_MONTH
import oncall.utils.Validator.getErrorMessage

class OnCallSchedule(private val month: Int, private val startDay: String) {
    init {
        validateMonth()
        validateStartDay()
    }

    private fun validateMonth() {
        require(month <= MAX_MONTH && month >= MIN_MONTH) {
            getErrorMessage(INVALID_MONTH)
        }
    }

    private fun validateStartDay() {
        require(DayOfWeek.entries.any { it.day == startDay }) {
            getErrorMessage(INVALID_START_DAY)
        }
    }

    companion object {
        private const val INVALID_MONTH = "월은 $MIN_MONTH ~ ${MAX_MONTH}사이의 정수여야 합니다."
        private const val INVALID_START_DAY = "올바르지 않은 시작 요일입니다. "
    }
}