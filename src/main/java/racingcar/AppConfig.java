package racingcar;

import racingcar.controller.RaceController;
import racingcar.domain.strategy.MoveStrategy;
import racingcar.domain.strategy.RandomMoveStrategy;
import racingcar.ui.InputView;
import racingcar.ui.OutputView;
import racingcar.util.InputParser;
import racingcar.validation.InputValidator;

public class AppConfig {

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public InputValidator inputValidator() {
        return new InputValidator();
    }

    public InputParser inputParser() {
        return new InputParser();
    }

    public MoveStrategy moveStrategy() {
        return new RandomMoveStrategy();
    }

    public RaceController raceController() {
        return new RaceController(
                inputView(),
                outputView(),
                inputValidator(),
                inputParser(),
                moveStrategy());
    }

}
