package oncall.constant

enum class PublicHoliday(val month: Int, val day: Int) {
    NEW_YEAR(1, 1),
    MARCH_FIRST(3, 1),
    CHILDREN(5, 5),
    MEMORIAL(6, 6),
    LIBERATION(8, 15),
    NATIONAL_FOUNDATION(10, 3),
    HANGEUL(10, 9),
    CHRISTMAS(12, 25)
}