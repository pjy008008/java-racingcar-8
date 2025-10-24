package racingcar;

import racingcar.controller.RaceController;

public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        RaceController raceController = appConfig.raceController();
        raceController.start();
    }
}
