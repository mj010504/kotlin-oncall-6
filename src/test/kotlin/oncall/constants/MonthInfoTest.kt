package oncall.constants


import oncall.constant.MonthInfo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class MonthInfoTest{
    @ParameterizedTest
    @MethodSource("provideMothAndDayCount")
    fun `각 월이 가지는 총 일수를 올바르게 반환한다`(pair: Pair<Int, Int>) {
       val monthInfo =  MonthInfo.getMonthInfo(pair.first)
        assertEquals(monthInfo.end ,pair.second)
    }

    companion object {
        @JvmStatic
         fun provideMothAndDayCount() : List<Pair<Int, Int>> {
            return listOf(
                Pair(1, 31),
                Pair(2, 28),
                Pair(3, 31),
                Pair(4, 30),
                Pair(5, 31),
                Pair(6, 30),
                Pair(7, 31),
                Pair(8, 31),
                Pair(9, 30),
                Pair(10, 31),
                Pair(11, 30),
                Pair(12, 31)
            )
        }
    }
}