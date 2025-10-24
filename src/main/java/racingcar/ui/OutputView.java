package racingcar.ui;

import racingcar.domain.Car;
import racingcar.domain.Cars;

import java.util.stream.Collectors;

public class OutputView {
    public void printWinners(Cars winners) {
        String result = winners.getCarList()
                .stream()
                .map(Car::getName)
                .collect(Collectors.joining(","));
        System.out.println("최종 우승자 : " + result);
    }

    public void printStartMessage() {
        System.out.println();
        System.out.println("실행 결과");
    }

    public void printCarsAndPositions(Cars cars) {
        for (Car car : cars.getCarList()) {
            System.out.print(car.getName()+" : ");
            int position = car.getPosition();
            System.out.println("-".repeat(position));
        }
        System.out.println();
    }
}
