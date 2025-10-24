package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.Race;
import racingcar.domain.strategy.MoveStrategy;
import racingcar.service.RaceSetupService;
import racingcar.ui.OutputView;

public class RaceController {
    private final RaceSetupService raceSetupService;
    private final OutputView outputView;

    private final MoveStrategy moveStrategy;

    public RaceController(
            RaceSetupService raceSetupService,
            OutputView outputView,
            MoveStrategy moveStrategy) {
        this.raceSetupService = raceSetupService;
        this.outputView = outputView;
        this.moveStrategy = moveStrategy;
    }

    public void start() {
        Cars cars = raceSetupService.getValidCars();
        int attempt = raceSetupService.getValidAttempt();

        Race race = new Race(cars, moveStrategy);
        runRace(race, attempt);

        announceWinner(race);
    }

    private void runRace(Race race, int attempt) {
        outputView.printStartMessage();
        for (int i = 0; i < attempt; i++) {
            race.runOneRound();
            outputView.printCarsAndPositions(race.getCars());
        }
    }

    private void announceWinner(Race race) {
        Cars winner = race.getWinner();
        outputView.printWinners(winner);
    }
}
