package oncall.constants

import oncall.constant.DayOfWeek
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class DayOfWeekTest {
    @ParameterizedTest
    @MethodSource("provideDayAndDayOfWeek")
    fun `각 요일에 따른 타입을 반환받는다`(pair: Pair<String, DayOfWeek>) {
        val dayOfWeek = DayOfWeek.getDayOfWeek(pair.first)
        assertEquals(dayOfWeek, pair.second)
    }

    @ParameterizedTest
    @MethodSource("provideDayAndNextDay")
    fun `각 요일에 따른 올바른 다음 요일을 반환받는다 `(pair: Pair<DayOfWeek, DayOfWeek>) {
        val nextDay = pair.first.nextDay()
        assertEquals(nextDay, pair.second)
    }

    companion object {
        @JvmStatic
        fun provideDayAndDayOfWeek(): List<Pair<String, DayOfWeek>> {
            return listOf(
                Pair("월", DayOfWeek.MONDAY),
                Pair("화", DayOfWeek.TUESDAY),
                Pair("수", DayOfWeek.WEDNESDAY),
                Pair("목", DayOfWeek.THURSDAY),
                Pair("금", DayOfWeek.FRIDAY),
                Pair("토", DayOfWeek.SATURDAY),
                Pair("일", DayOfWeek.SUNDAY),
                )
        }

        @JvmStatic
        fun provideDayAndNextDay(): List<Pair<DayOfWeek, DayOfWeek>> {
            return listOf(
                Pair(DayOfWeek.MONDAY, DayOfWeek.TUESDAY),
                Pair(DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY),
                Pair(DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY),
                Pair(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY),
                Pair(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY),
                Pair(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY),
            )
        }
    }
}