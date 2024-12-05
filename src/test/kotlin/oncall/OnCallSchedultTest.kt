package oncall

import camp.nextstep.edu.missionutils.test.NsTest
import oncall.domain.OnCallSchedule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class OnCallSchedultTest : NsTest() {
    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 13, 99])
   fun `비상 근무를 배정할 월은 1 ~ 12 사이의 정수이다`(month : Int) {
        assertThrows<IllegalArgumentException> {
            OnCallSchedule(month, "월")
        }
   }

    @ParameterizedTest
    @ValueSource(strings = ["월요일, 천, 대, 공휴일"])
    fun `비상 근무를 배정할 요일을 요일을 제외하고 입력한다`(startDay : String) {
        assertThrows<IllegalArgumentException> {
            OnCallSchedule(1, startDay)
        }
    }

    override fun runMain() {
        main()
    }
}