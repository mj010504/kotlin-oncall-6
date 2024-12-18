package oncall.domain

import oncall.utils.Validator


class OnCallOrder(
    private val weekdayOrder: MutableList<String>, private val holidayOrder: MutableList<String>
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

    fun getWeekdayOrderPerson(index: Int): String = weekdayOrder[index]


    fun getHolidayOrderPerson(index: Int): String = holidayOrder[index]


    fun adjustWeekDayOrder(index: Int) {
        weekdayOrder.swap(index)
    }

    fun adjustHoliDayOrder(index: Int) {
        holidayOrder.swap(index)
    }

    fun getOrderCount(): Int = weekdayOrder.size


    companion object {
        private const val INVALID_DUPLICATED_NAME = "순번에 중복된 이름이 존재합니다."
        private const val INVALID_ORDER_NAMES = "평일 순번과 휴일 순번 인원들의 이름이 일치하지 않습니다."
    }
}


fun MutableList<String>.swap(index: Int) {
    val temp = this[index]
    val nextIndex = (index + 1) % this.size
    this[index] = this[nextIndex]
    this[nextIndex] = temp
}