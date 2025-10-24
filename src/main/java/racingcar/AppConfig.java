package racingcar;

import racingcar.controller.RaceController;
import racingcar.domain.strategy.MoveStrategy;
import racingcar.domain.strategy.RandomMoveStrategy;
import racingcar.service.RaceSetupService;
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

    public RaceSetupService raceSetupService() {
        return new RaceSetupService(inputView(), inputValidator(), inputParser());
    }

    public RaceController raceController() {
        return new RaceController(
                raceSetupService(),
                outputView(),
                moveStrategy());
    }

}
