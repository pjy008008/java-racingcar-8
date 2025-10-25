package racingcar.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AttemptValidatorTest {
    private final AttemptValidator validator = new AttemptValidator();

    @Test
    @DisplayName("유효한 양의 정수 문자열은 예외를 발생시키지 않아야 한다.")
    void shouldNotThrowExceptionForValidPositiveNumberString() {
        // given
        String validInput = "5";

        // when & then
        assertThatCode(() -> validator.validate(validInput))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("빈 문자열 입력은 IllegalArgumentException을 발생시켜야 한다.")
    void shouldThrowExceptionForEmptyString() {
        // given
        String emptyInput = "";

        // when & then
        assertThatThrownBy(() -> validator.validate(emptyInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("빈칸일 수 없습니다.");
    }

    @Test
    @DisplayName("공백 문자열 입력은 IllegalArgumentException을 발생시켜야 한다.")
    void shouldThrowExceptionForBlankString() {
        // given
        String blankInput = "   "; // 공백만 있는 문자열

        // when & then
        assertThatThrownBy(() -> validator.validate(blankInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("빈칸일 수 없습니다.");
    }

    @Test
    @DisplayName("숫자가 아닌 문자열 입력은 IllegalArgumentException을 발생시켜야 한다.")
    void shouldThrowExceptionForNonNumericString() {
        // given
        String nonNumericString = "abc";

        // when & then
        assertThatThrownBy(() -> validator.validate(nonNumericString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자여야 합니다.");
    }

    @Test
    @DisplayName("0 문자열 입력은 IllegalArgumentException을 발생시켜야 한다.")
    void shouldThrowExceptionForZeroNumberString() {
        // given
        String zeroNumberString = "0";

        // when & then
        assertThatThrownBy(() -> validator.validate(zeroNumberString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양수여야 합니다.");
    }

    @Test
    @DisplayName("음수 문자열 입력은 IllegalArgumentException을 발생시켜야 한다.")
    void shouldThrowExceptionForNegativeNumberString() {
        // given
        String negativeNumberString = "-5";

        // when & then
        assertThatThrownBy(() -> validator.validate(negativeNumberString))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양수여야 합니다.");
    }
}