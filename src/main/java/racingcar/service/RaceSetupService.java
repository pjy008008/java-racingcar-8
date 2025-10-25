package racingcar.service;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.ui.InputView;
import racingcar.ui.OutputView;
import racingcar.util.InputParser;
import racingcar.validation.InputValidator;

import java.util.List;

public class RaceSetupService {
    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidator inputValidator;
    private final InputParser inputParser;

    public RaceSetupService(
            InputView inputView,
            OutputView outputView,
            InputValidator inputValidator,
            InputParser inputParser) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputValidator = inputValidator;
        this.inputParser = inputParser;
    }

    public Cars getValidCars() {
        List<String> carNames = getCarNames();

        List<Car> carList = carNames.stream()
                .map(Car::new)
                .toList();

        return new Cars(carList);
    }

    private List<String> getCarNames() {
        outputView.printCarNamePrompt();
        String carNames = inputView.readCarNames();
        List<String> parsedCarNames = inputParser.parse(carNames);
        inputValidator.validateCarName(parsedCarNames);
        return parsedCarNames;
    }

    public int getValidAttempt() {
        outputView.printAttemptPrompt();
        String attempt = inputView.readAttempt();
        inputValidator.validateAttempt(attempt);
        return Integer.parseInt(attempt);
    }
}
