package racingcar.validation;

public class AttemptValidator implements Validator<String>{
    private static final String ERROR_ATTEMPT_NOT_A_NUMBER = "시도 횟수는 숫자여야 합니다.";
    private static final String ERROR_ATTEMPT_NOT_POSITIVE = "시도 횟수는 양수여야 합니다.";

    @Override
    public void validate(String input) {
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
