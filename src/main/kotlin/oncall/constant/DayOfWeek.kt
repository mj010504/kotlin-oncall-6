package oncall.constant

enum class DayOfWeek(val day: String) {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    fun nextDay() : DayOfWeek {
        val nextOrdinal = (this.ordinal + 1) % entries.size
        return entries[nextOrdinal]
    }

    companion object {
        fun getDayOfWeek(day: String): DayOfWeek {
            return DayOfWeek.entries.find { it.day == day }
                ?: throw IllegalArgumentException(INVALID_DAY)
        }

        private const val INVALID_DAY = "올바르지 않은 요일입니다."
    }
}