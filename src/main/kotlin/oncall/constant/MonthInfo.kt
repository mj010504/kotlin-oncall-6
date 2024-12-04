package oncall.constant


enum class MonthInfo(val month: Int, val end: Int) {
    JANUARY(1, 31),
    FEBRURARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    companion object {
        fun getMonthInfo(month: Int): MonthInfo {
            return MonthInfo.entries.find { it.month == month }
                ?: throw IllegalArgumentException(INVALID_MONTH)
        }

        private const val INVALID_MONTH = "올바르지 않은 월입니다."
    }
}