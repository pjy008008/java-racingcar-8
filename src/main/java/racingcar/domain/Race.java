package racingcar.domain;

import racingcar.domain.strategy.MoveStrategy;

import java.util.List;

public class Race {
    private final Cars cars;
    private final MoveStrategy moveStrategy;

    public Race(Cars cars, MoveStrategy moveStrategy) {
        this.cars = cars;
        this.moveStrategy = moveStrategy;
    }

    public void runOneRound() {
        for (Car car : cars.getCarList()) {
            if (moveStrategy.canMove()) {
                car.move();
            }
        }
    }

    public Cars getWinner() {
        List<Car> carList = cars.getCarList();

        int maxPosition = getMaxPosition(carList);

        List<Car> winner = carList.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .toList();

        return new Cars(winner);
    }

    private static int getMaxPosition(List<Car> carList) {
        return carList.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
    }

    public Cars getCars() {
        return cars;
    }
}
