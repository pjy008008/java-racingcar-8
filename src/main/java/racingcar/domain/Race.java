package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.ui.OutputView;

import java.util.List;

public class Race {
    private final Cars cars;
    private final int attempt;
    private final OutputView outputView;

    public Race(Cars cars, int attempt, OutputView outputView) {
        this.cars = cars;
        this.attempt = attempt;
        this.outputView = outputView;
    }

    public Cars run() {
        for (int i = 0; i < attempt; i++) {
            runOneRound(cars);
            outputView.printCarsAndPositions(cars);
        }
        return getWinner(cars);
    }


    private void runOneRound(Cars cars) {
        for (Car car : cars.getCarList()) {
            if (Randoms.pickNumberInRange(0, 9) >= 4) {
                car.move();
            }
        }
    }

    private Cars getWinner(Cars cars) {
        List<Car> carList = cars.getCarList();

        int maxPosition = carList.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);

        List<Car> winner = carList.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .toList();

        return new Cars(winner);
    }
}
