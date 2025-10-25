package racingcar.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.ui.console.OutputWriter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StubOutputWriter implements OutputWriter {
    private final StringBuilder outputBuffer = new StringBuilder();

    @Override
    public void println(String message) {
        outputBuffer.append(message).append("\n");
    }

    public String getOutput() {
        return outputBuffer.toString().replace("\r\n", "\n").trim();
    }
}

class OutputViewTest {
    private StubOutputWriter stubOutputWriter;
    private OutputView outputView;

    @BeforeEach
    void setUp() {
        stubOutputWriter = new StubOutputWriter();
        outputView = new OutputView(stubOutputWriter);
    }

    @Test
    @DisplayName("printCarNamePrompt는 자동차 이름 입력 프롬프트를 출력해야 한다.")
    void shouldPrintCarNamePrompt() {
        // when
        outputView.printCarNamePrompt();

        // then
        assertThat(stubOutputWriter.getOutput()).isEqualTo("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
    }

    @Test
    @DisplayName("printAttemptPrompt는 시도 횟수 입력 프롬프트를 출력해야 한다.")
    void shouldPrintAttemptPrompt() {
        // when
        outputView.printAttemptPrompt();

        // then
        assertThat(stubOutputWriter.getOutput()).isEqualTo("시도할 횟수는 몇 회인가요?");
    }

    @Test
    @DisplayName("printWinners는 최종 우승자 메시지와 함께 우승자 이름을 쉼표로 구분하여 출력해야 한다 (단일 우승자).")
    void shouldPrintSingleWinner() {
        // given
        Car winnerCar = new Car("pobi");
        Cars winners = new Cars(List.of(winnerCar));

        // when
        outputView.printWinners(winners);

        // then
        assertThat(stubOutputWriter.getOutput()).isEqualTo("최종 우승자 : pobi");
    }

    @Test
    @DisplayName("printWinners는 최종 우승자 메시지와 함께 우승자 이름들을 쉼표로 구분하여 출력해야 한다 (복수 우승자).")
    void shouldPrintMultipleWinners() {
        // given
        Car winner1 = new Car("pobi");
        Car winner2 = new Car("jun");
        Cars winners = new Cars(List.of(winner1, winner2));

        // when
        outputView.printWinners(winners);

        // then
        assertThat(stubOutputWriter.getOutput()).isEqualTo("최종 우승자 : pobi,jun");
    }

    @Test
    @DisplayName("printStartMessage는 실행 결과 시작 메시지를 출력해야 한다.")
    void shouldPrintStartMessage() {
        // when
        outputView.printStartMessage();

        // then
        assertThat(stubOutputWriter.getOutput()).contains("실행 결과");
    }

    @Test
    @DisplayName("printCarsAndPositions는 각 자동차의 이름과 위치를 형식에 맞게 출력하고 마지막에 빈 줄을 출력해야 한다.")
    void shouldPrintCarNamesAndPositions() {
        // given
        Car car1 = new Car("pobi");
        car1.move();
        Car car2 = new Car("woni");
        car2.move();
        car2.move();
        Car car3 = new Car("jun");
        car3.move();
        Cars cars = new Cars(List.of(car1, car2, car3));

        // when
        outputView.printCarsAndPositions(cars);

        // then
        String expectedOutput = """
                pobi : -
                woni : --
                jun : -""";

        assertThat(stubOutputWriter.getOutput()).isEqualTo(expectedOutput);
    }
}