package racingcar.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputValidator {
    private static final int MAX_NAME_LENGTH = 5;
    private static final String ERROR_CAR_NAME_EMPTY = "자동차 이름이 빈칸입니다.";
    private static final String ERROR_CAR_NAME_LENGTH_EXCEEDED = "자동차 이름은 " + MAX_NAME_LENGTH + "자를 초과할 수 없습니다.";
    private static final String ERROR_CAR_NAME_DUPLICATE = "자동차 이름은 중복될 수 없습니다.";
    private static final String ERROR_ATTEMPT_NOT_A_NUMBER = "시도 횟수는 숫자여야 합니다.";
    private static final String ERROR_ATTEMPT_NOT_POSITIVE = "시도 횟수는 양수여야 합니다.";

    public void validateCarName(List<String> carNames) {
        validateCarNameLength(carNames);
        validateCarNameIsEmpty(carNames);
        validateCarNameDuplicate(carNames);
    }

    private void validateCarNameIsEmpty(List<String> carNames) {
        for (String carName : carNames) {
            if (carName.isEmpty()) {
                throw new IllegalArgumentException(ERROR_CAR_NAME_EMPTY);
            }
        }
    }
    private void validateCarNameLength(List<String> carNames) {
        for (String carName : carNames) {
            if (carName.length() > MAX_NAME_LENGTH) {
                throw new IllegalArgumentException(ERROR_CAR_NAME_LENGTH_EXCEEDED);
            }
        }
    }

    private void validateCarNameDuplicate(List<String> carNames) {
        Set<String> uniqueNames = new HashSet<>();
        for (String carName : carNames) {
            if (!uniqueNames.add(carName)) {
                throw new IllegalArgumentException(ERROR_CAR_NAME_DUPLICATE);
            }
        }
    }

    public void validateAttempt(String input) {
        validateAttemptFormat(input);
        validateAttemptIsPositive(input);
    }

    private void validateAttemptIsPositive(String input) {
        int attempt = Integer.parseInt(input);
        if (attempt <= 0) {
            throw new IllegalArgumentException(ERROR_ATTEMPT_NOT_POSITIVE);
        }
    }
    private void validateAttemptFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_ATTEMPT_NOT_A_NUMBER);
        }
    }
}
