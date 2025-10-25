package racingcar.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarNameValidatorTest {
    private final CarNameValidator validator = new CarNameValidator();

    @Test
    @DisplayName("유효한 자동차 이름 리스트는 예외를 발생시키지 않아야 한다.")
    void shouldNotThrowExceptionForValidNames() {
        // given
        List<String> validNames = List.of("pobi", "woni", "jun");

        // when & then
        assertThatCode(() -> validator.validate(validNames))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("자동차 이름 리스트에 빈 이름이 포함된 경우 IllegalArgumentException을 발생시켜야 한다.")
    void shouldThrowExceptionWhenNameIsEmpty() {
        // given
        List<String> namesWithEmpty = List.of("pobi", "", "jun");

        // when & then
        assertThatThrownBy(() -> validator.validate(namesWithEmpty))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름이 빈칸입니다.");
    }

    @Test
    @DisplayName("자동차 이름이 5자를 초과하는 경우 IllegalArgumentException을 발생시켜야 한다.")
    void shouldThrowExceptionWhenNameLengthExceeded() {
        // given
        List<String> namesWithEmpty = List.of("pobi", "woni", "junyou");

        // when & then
        assertThatThrownBy(() -> validator.validate(namesWithEmpty))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5자를 초과할 수 없습니다.");
    }

    @Test
    @DisplayName("자동차 이름 리스트에 중복된 이름이 포함된 경우 IllegalArgumentException을 발생시켜야 한다.")
    void shouldThrowExceptionWhenNameIsDuplicate() {
        // given
        List<String> namesWithDuplicate = List.of("pobi", "woni", "pobi");

        // when & then
        assertThatThrownBy(() -> validator.validate(namesWithDuplicate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복될 수 없습니다.");
    }
}