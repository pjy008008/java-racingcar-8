package racingcar.ui;

import racingcar.domain.Car;
import racingcar.domain.Cars;

import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ",";
    private static final String WINNER_MESSAGE = "최종 우승자 : ";
    private static final String START_MESSAGE = "\n실행 결과";
    private static final String NAME_POSITION_SEPARATOR = " : ";
    private static final String POSITION_MARK = "-";

    public void printWinners(Cars winners) {
        String result = winners.getCarList()
                .stream()
                .map(Car::getName)
                .collect(Collectors.joining(DELIMITER));
        System.out.println(WINNER_MESSAGE + result);
    }

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printCarsAndPositions(Cars cars) {
        for (Car car : cars.getCarList()) {
            System.out.print(car.getName() + NAME_POSITION_SEPARATOR);
            int position = car.getPosition();
            System.out.println(POSITION_MARK.repeat(position));
        }
        System.out.println();
    }
}
