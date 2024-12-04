package oncall.domain

import oncall.utils.Validator

class OnCallOrder(
    private val weekdayOrder: List<String>, private val holidayOrder: List<String>
) {
    init {
        validateDuplicatedName()
        validateSameNames()
    }

    private fun validateDuplicatedName() {
        require(weekdayOrder.size == weekdayOrder.toSet().size && holidayOrder.size == holidayOrder.toSet().size) {
            Validator.getErrorMessage(INVALID_DUPLICATED_NAME)
        }
    }

    private fun validateSameNames() {
        require(weekdayOrder.toSet() == holidayOrder.toSet()) {
            Validator.getErrorMessage(INVALID_ORDER_NAMES)
        }
    }


    companion object {
        private const val INVALID_DUPLICATED_NAME = "순번에 중복된 이름이 존재합니다."
        private const val  INVALID_ORDER_NAMES = "평일 순번과 휴일 순번 인원들의 이름이 일치하지 않습니다."
    }
}