package edu.hw2.task4;

import org.junit.jupiter.api.DisplayName;
import edu.hw2.task4.CallingInfoUtils.CallingInfo;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Проверка вывода последнего вызванного метода")
class CallingInfoTest {
    @Test
    void testCallingInfo() {
        //given
        CallingInfo callingInfo = CallingInfoUtils.callingInfo();

        //then
        assertThat(callingInfo.className()).isEqualTo("edu.hw2.task4.CallingInfoTest");
        assertThat(callingInfo.methodName()).isEqualTo("testCallingInfo");
    }
}
