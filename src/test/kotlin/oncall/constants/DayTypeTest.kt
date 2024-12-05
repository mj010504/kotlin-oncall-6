package oncall.constants

import oncall.constant.DayOfWeek
import oncall.constant.DayType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class DayTypeTest {
    @ParameterizedTest
    @MethodSource("provideWeekday")
    fun `법정공휴일이 아닌 평일이라면 평일을 반환한다`(pair: Pair<Int, Int>) {
        val dayType = DayType.convertToDayType(pair.first, pair.second, DayOfWeek.FRIDAY)
        assertEquals(dayType, DayType.WEEKDAY)
    }


    @ParameterizedTest
    @MethodSource("provideHoliday")
    fun `토요일 또는 일요일이라면 주말을 반환한다`(dayOfWeek: DayOfWeek) {
        val dayType = DayType.convertToDayType(1, 3, dayOfWeek)
        assertEquals(dayType, DayType.HOLIDAY)
    }

    @ParameterizedTest
    @MethodSource("providePublicHoliday")
    fun `법정공휴일인 평일이라면 법정공휴일을 반환한다`(pair : Pair<Int, Int>) {
        val dayType = DayType.convertToDayType(pair.first, pair.second, DayOfWeek.FRIDAY)
        assertEquals(dayType, DayType.PUBLIC_HOLIDAY)
    }

    companion object {
        @JvmStatic
        fun provideWeekday() : List<Pair<Int, Int>>{
            return listOf(
                Pair(1,2),
                Pair(3,31),
                Pair(5,4)
            )
        }


        @JvmStatic
        fun provideHoliday() : List<DayOfWeek>{
            return listOf(
                DayOfWeek.SATURDAY,
                DayOfWeek.SUNDAY
            )
        }

        @JvmStatic
        fun providePublicHoliday() : List<Pair<Int, Int>>{
            return listOf(
                Pair(1,1),
                Pair(3,1),
                Pair(8,15)
            )
        }
    }


}