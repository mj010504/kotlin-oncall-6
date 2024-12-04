package oncall.constant

enum class DayType {
    WEEKDAY,
    HOLIDAY,
    PUBLIC_HOLIDAY;

    companion object {
        fun convertToDayType(month: Int, day: Int, dayOfWeek: DayOfWeek): DayType {
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) return HOLIDAY
            if (PublicHoliday.entries.any { it.month == month && it.day == day }) return PUBLIC_HOLIDAY
            return WEEKDAY
        }
    }
}