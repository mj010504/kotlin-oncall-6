package oncall.domain

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest

import oncall.main
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OnCallSchedultTest : NsTest() {
    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 13, 99])
    fun `비상 근무를 배정할 월은 1 ~ 12 사이의 정수이다`(month: Int) {
        assertThrows<IllegalArgumentException> {
            OnCallSchedule(month, "월")
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["월요일, 천, 대, 공휴일"])
    fun `비상 근무를 배정할 요일을 요일을 제외하고 입력한다`(startDay: String) {
        assertThrows<IllegalArgumentException> {
            OnCallSchedule(1, startDay)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 13, 99])
    fun `비상 근무를 배정할 월을 올바르게 입력하지 않으면 다시 입력받는다`(month: Int) {
        assertSimpleTest {
            run(
                "$month, 토",
                "a,b,c",
                "c,b,a",
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

    @ParameterizedTest
    @ValueSource(strings = ["월요일, 천, 대, 공휴일"])
    fun `비상 근무를 배정할 요일을 올바르게 입력하지 않으면 다시 입력받는다`(startDay: String) {
        assertSimpleTest {
            run(
                "4, $startDay",
                "a,b,c",
                "c,b,a",
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

    }

}