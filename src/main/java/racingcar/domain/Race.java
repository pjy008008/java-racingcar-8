package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class Race {
    private final Cars cars;

    public Race(Cars cars) {
        this.cars = cars;
    }

    public void runOneRound() {
        for (Car car : cars.getCarList()) {
            if (Randoms.pickNumberInRange(0, 9) >= 4) {
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
