package racingcar.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarNameValidator implements Validator<List<String>> {
    private static final int MAX_NAME_LENGTH = 5;
    private static final String ERROR_CAR_NAME_EMPTY = "자동차 이름이 빈칸입니다.";
    private static final String ERROR_CAR_NAME_LENGTH_EXCEEDED = "자동차 이름은 " + MAX_NAME_LENGTH + "자를 초과할 수 없습니다.";
    private static final String ERROR_CAR_NAME_DUPLICATE = "자동차 이름은 중복될 수 없습니다.";

    @Override
    public void validate(List<String> input) {
        validateCarNameLength(input);
        validateCarNameIsEmpty(input);
        validateCarNameDuplicate(input);
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
}
