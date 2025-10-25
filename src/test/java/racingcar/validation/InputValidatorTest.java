package racingcar.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {
    private final CarNameValidator carNameValidator = new CarNameValidator();
    private final AttemptValidator attemptValidator = new AttemptValidator();
    private final InputValidator inputValidator = new InputValidator(carNameValidator, attemptValidator);

    @Test
    @DisplayName("유효한 자동차 이름 리스트는 예외를 발생시키지 않아야 한다.")
    void shouldPassValidCarNames() {
        // given
        List<String> validCarNames = List.of("pobi", "woni");

        // when & then
        assertThatCode(() -> inputValidator.validateCarName(validCarNames))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유효한 시도 횟수 문자열은 예외를 발생시키지 않아야 한다.")
    void shouldPassValidAttempt() {
        // given
        String validAttempt = "5";

        // when & then
        assertThatCode(() -> inputValidator.validateAttempt(validAttempt))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유효하지 않은 자동차 이름 리스트는 IllegalArgumentException을 발생시켜야 한다.")
    void shouldThrowExceptionForInvalidCarNames() {
        // given
        List<String> invalidCarNames = List.of("pobi", "longname");

        // when & then
        assertThatThrownBy(() -> inputValidator.validateCarName(invalidCarNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5자를 초과할 수 없습니다.");
    }

    @Test
    @DisplayName("유효하지 않은 시도 횟수 문자열은 IllegalArgumentException을 발생시켜야 한다.")
    void shouldThrowExceptionForInvalidAttempt() {
        // given
        String invalidAttempt = "abc";

        // when & then
        assertThatThrownBy(() -> inputValidator.validateAttempt(invalidAttempt))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자여야 합니다.");
    }
}