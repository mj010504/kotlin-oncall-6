package oncall.domain

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import oncall.main
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class OnCallOrderTest : NsTest() {
    @Test
    fun `평일 순번에 중복된 인원이 있으면 예외가 발생한다`() {
        val weekdayOrder = mutableListOf("a", "a", "b", "c")
        val holidayOrder = mutableListOf("a", "b", "c")

        assertThrows<IllegalArgumentException> {
            OnCallOrder(weekdayOrder, holidayOrder)
        }
    }

    @Test
    fun `휴일 순번에 중복된 인원이 있으면 예외가 발생한다`() {
        val weekdayOrder = mutableListOf("a", "b", "c")
        val holidayOrder = mutableListOf("a", "a", "b", "c")

        assertThrows<IllegalArgumentException> {
            OnCallOrder(weekdayOrder, holidayOrder)
        }
    }

    @Test
    fun `평일 순번과 휴일 순번 인원들의 이름들이 일치하지 않으면 예외가 발생한다`() {
        val weekdayOrder = mutableListOf("a", "b", "c")
        val holidayOrder = mutableListOf("a", "b", "e")

        assertThrows<IllegalArgumentException> {
            OnCallOrder(weekdayOrder, holidayOrder)
        }
    }

    @ParameterizedTest
    @MethodSource("provideInvalidOrder")
    fun `평일 순번과 휴일 순번을 올바르게 입력하지 않을시 다시 입력받는다`(pair: Pair<String, String>) {
        assertSimpleTest {
            run(
              "4, 토",
                pair.first,
                pair.second,
                "4,토",
                "a,b,c",
                "c,b,a"
            )
            assertThat(output()).contains(
                ERROR,
                RETRY_INPUT,
                """
                4월 1일 토 c
                4월 2일 일 b
                4월 3일 월 a
                4월 4일 화 b
                4월 5일 수 c
                """.trimIndent()
            )
        }
    }

    override fun runMain() {
        main()
    }

    companion object {
        private const val ERROR = "[ERROR]"
        private const val RETRY_INPUT = "다시 입력해 주세요."

        @JvmStatic
        fun provideInvalidOrder(): List<Pair<String, String>> {
            return listOf(
               Pair("a,b,c", "a,b,e"),
               Pair("a,b,a", "a,b,c")
            )
        }
    }
}