package racingcar.validation;

import java.util.List;

public class InputValidator {
    private final Validator<List<String>> carNameValidator;
    private final Validator<String> attemptValidator;

    public InputValidator(Validator<List<String>> carNameValidator, Validator<String> attemptValidator) {
        this.carNameValidator = carNameValidator;
        this.attemptValidator = attemptValidator;
    }

    public void validateCarName(List<String> carNames) {
        carNameValidator.validate(carNames);
    }

    public void validateAttempt(String attempt) {
        attemptValidator.validate(attempt);
    }
}
