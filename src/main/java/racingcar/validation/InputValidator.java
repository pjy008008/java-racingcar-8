package racingcar.validation;

import java.util.List;

public class InputValidator {

    public void validateCarName(List<String> carNames) {
        validateCarNameLength(carNames);
        validateCarNameIsEmpty(carNames);
    }

    private void validateCarNameIsEmpty(List<String> carNames) {
        for (String carName : carNames) {
            if (carName.isEmpty()) {
                throw new IllegalArgumentException("자동차 이름이 빈칸입니다.");
            }
        }
    }
    private void validateCarNameLength(List<String> carNames) {
        for (String carName : carNames) {
            if (carName.length() > 5) {
                throw new IllegalArgumentException("자동차 이름의 길이가 5를 초과합니다.");
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
            throw new IllegalArgumentException("시도 횟수는 양수여야 합니다.");
        }
    }
    private void validateAttemptFormat(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자여야 합니다.");
        }
    }
}
