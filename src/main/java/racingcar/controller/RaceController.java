package racingcar.controller;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Race;
import racingcar.domain.strategy.MoveStrategy;
import racingcar.ui.InputView;
import racingcar.ui.OutputView;
import racingcar.util.InputParser;
import racingcar.validation.InputValidator;

import java.util.List;

public class RaceController {
    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidator inputValidator;
    private final InputParser inputParser;
    private final MoveStrategy moveStrategy;

    public RaceController(
            InputView inputView,
            OutputView outputView,
            InputValidator inputValidator,
            InputParser inputParser,
            MoveStrategy moveStrategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.inputValidator = inputValidator;
        this.inputParser = inputParser;
        this.moveStrategy = moveStrategy;
    }

    public void start() {
        List<String> carNames = getCarNames();
        int attempt = getAttempt();

        List<Car> carList = carNames.stream()
                .map(Car::new)
                .toList();

        Cars cars = new Cars(carList);

        Race race = new Race(cars, moveStrategy);

        outputView.printStartMessage();
        for (int i = 0; i < attempt; i++) {
            race.runOneRound();
            outputView.printCarsAndPositions(race.getCars());
        }

        Cars winner = race.getWinner();
        outputView.printWinners(winner);
    }

    private List<String> getCarNames() {
        String carNames = inputView.readCarNames();
        List<String> parsedCarNames = inputParser.parse(carNames);
        inputValidator.validateCarName(parsedCarNames);
        return parsedCarNames;
    }

    private int getAttempt() {
        String attempt = inputView.readAttempt();
        inputValidator.validateAttempt(attempt);
        return Integer.parseInt(attempt);
    }
}
