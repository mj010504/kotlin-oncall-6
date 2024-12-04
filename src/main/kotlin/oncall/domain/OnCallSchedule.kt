package oncall.domain

import oncall.constant.DayOfWeek
import oncall.utils.Validator.getErrorMessage

class OnCallSchedule(val month: Int, val startDay: String) {
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
        private const val MIN_MONTH = 1
        private const val MAX_MONTH = 12
        private const val INVALID_MONTH = "월은 $MIN_MONTH ~ ${MAX_MONTH}사이의 정수여야 합니다."
        private const val INVALID_START_DAY = "올바르지 않은 시작 요일입니다. "
    }


}