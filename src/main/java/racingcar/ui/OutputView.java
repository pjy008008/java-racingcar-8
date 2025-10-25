package racingcar.ui;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.ui.console.OutputWriter;

import java.util.stream.Collectors;

public class OutputView {
    private static final String CAR_NAME_PROMPT = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ATTEMPT_PROMPT = "시도할 횟수는 몇 회인가요?";
    private static final String DELIMITER = ",";
    private static final String WINNER_MESSAGE = "최종 우승자 : ";
    private static final String START_MESSAGE = "\n실행 결과";
    private static final String NAME_POSITION_SEPARATOR = " : ";
    private static final String POSITION_MARK = "-";

    private final OutputWriter outputWriter;

    public OutputView(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    public void printCarNamePrompt() {
        outputWriter.println(CAR_NAME_PROMPT);
    }

    public void printAttemptPrompt() {
        outputWriter.println(ATTEMPT_PROMPT);
    }

    public void printWinners(Cars winners) {
        String result = winners.getCarList()
                .stream()
                .map(Car::getName)
                .collect(Collectors.joining(DELIMITER));
        outputWriter.println(WINNER_MESSAGE + result);
    }

    public void printStartMessage() {
        outputWriter.println(START_MESSAGE);
    }

    public void printCarsAndPositions(Cars cars) {
        for (Car car : cars.getCarList()) {
            int position = car.getPosition();
            String message = car.getName() + NAME_POSITION_SEPARATOR + POSITION_MARK.repeat(position);
            outputWriter.println(message);
        }
        outputWriter.println("");
    }
}
