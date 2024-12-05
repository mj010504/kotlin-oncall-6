package oncall.domain

import camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest
import camp.nextstep.edu.missionutils.test.NsTest
import oncall.main
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class OnCallGraphTest : NsTest() {
    @Test
    fun `사용자 입력에 따라 올바른 비상 근무표를 출력한다`() {
        assertSimpleTest {
            run(
                "4,토",
                "a,b,c",
                "c,b,a"
            )
            assertThat(output()).contains(
                """
            4월 1일 토 c
            4월 2일 일 b
            4월 3일 월 a
            4월 4일 화 b
            4월 5일 수 c
            4월 6일 목 a
            4월 7일 금 b
            4월 8일 토 a
            4월 9일 일 c
            4월 10일 월 a
            4월 11일 화 c
            4월 12일 수 b
            4월 13일 목 a
            4월 14일 금 c
            4월 15일 토 b
            4월 16일 일 a
            4월 17일 월 b
            4월 18일 화 a
            4월 19일 수 c
            4월 20일 목 b
            4월 21일 금 a
            4월 22일 토 c
            4월 23일 일 b
            4월 24일 월 c
            4월 25일 화 b
            4월 26일 수 a
            4월 27일 목 c
            4월 28일 금 b
            4월 29일 토 a
            4월 30일 일 c
                """.trimIndent()
            )
        }

    }

    override fun runMain() {
        main()
    }

}