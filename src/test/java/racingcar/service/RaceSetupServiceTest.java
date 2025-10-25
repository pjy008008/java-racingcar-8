package racingcar.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.ui.InputReader;
import racingcar.ui.InputView;
import racingcar.util.InputParser;
import racingcar.validation.AttemptValidator;
import racingcar.validation.CarNameValidator;
import racingcar.validation.InputValidator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class StubInputReader implements InputReader {
    private String input;

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String readLine() {
        return this.input;
    }
}

class RaceSetupServiceTest {

    private final StubInputReader stubInputReader = new StubInputReader();
    private final InputView inputView = new InputView(stubInputReader);
    private final CarNameValidator carNameValidator = new CarNameValidator();
    private final AttemptValidator attemptValidator = new AttemptValidator();
    private final InputValidator inputValidator = new InputValidator(carNameValidator, attemptValidator);
    private final InputParser inputParser = new InputParser();
    private final RaceSetupService raceSetupService = new RaceSetupService(inputView, inputValidator, inputParser);

    @Nested
    @DisplayName("getValidCars 메서드는")
    class Describe_getValidCars {
        @Test
        @DisplayName("유효한 자동차 이름 입력을 받아 Cars 객체를 생성해야 한다.")
        void shouldCreateCarsFromValidInput() {
            // given
            String validInputNames = "pobi,woni,jun";
            stubInputReader.setInput(validInputNames);

            // when
            Cars resultCars = raceSetupService.getValidCars();

            // then
            assertThat(resultCars).isNotNull();
            assertThat(resultCars.getCarList())
                    .hasSize(3)
                    .extracting(Car::getName)
                    .containsExactly("pobi", "woni", "jun");
        }

        @Test
        @DisplayName("유효하지 않은 자동차 이름 입력 시 IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionForInvalidCarNamesInput() {
            // given
            String invalidInputNames = "pobi,,jun";
            stubInputReader.setInput(invalidInputNames);

            // when & then
            assertThatThrownBy(raceSetupService::getValidCars)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("자동차 이름이 빈칸입니다.");
        }
    }

    @Nested
    @DisplayName("getValidAttempt 메서드는")
    class Describe_getValidAttempt {
        @Test
        @DisplayName("유효한 시도 횟수 입력을 받아 정수형으로 반환해야 한다.")
        void shouldReturnAttemptFromValidInput() {
            // given
            String validAttemptInput = "5";
            stubInputReader.setInput(validAttemptInput);

            // when
            int resultAttempt = raceSetupService.getValidAttempt();

            // then
            int expectedAttempt = 5;
            assertThat(resultAttempt).isEqualTo(expectedAttempt);
        }

        @Test
        @DisplayName("유효하지 않은 시도 횟수 입력 시 IllegalArgumentException을 발생시켜야 한다.")
        void shouldThrowExceptionForInvalidAttemptInput() {
            // given
            String invalidAttemptInput = "abc";
            stubInputReader.setInput(invalidAttemptInput);

            // when & then
            assertThatThrownBy(raceSetupService::getValidAttempt)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("숫자여야 합니다.");
        }
    }
}

