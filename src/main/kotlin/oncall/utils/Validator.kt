package oncall.utils

object Validator {
    private const val ERROR_LABLE = "[ERROR]"
    private const val RETRY_INPUT = "다시 입력해 주세요."
    private const val INVALID_SCHEDULE = "올바르지 않은 스케줄 형식입니다."
    private const val INVALID_MONTH_TYPE = "월은 정수로 입력해야 합니다."

    fun List<String>.validateSchedule() {
        if(this.size != 2) throw IllegalArgumentException(getErrorMessage(INVALID_SCHEDULE))
        this[0].toIntOrNull() ?: throw IllegalArgumentException(getErrorMessage(INVALID_MONTH_TYPE))
    }

    fun getErrorMessage(message : String) : String {
        return "$ERROR_LABLE $message $RETRY_INPUT"
    }

}