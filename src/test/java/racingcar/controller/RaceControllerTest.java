package racingcar.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racingcar.domain.strategy.AlwaysMoveStrategy;
import racingcar.domain.strategy.MoveStrategy;
import racingcar.service.RaceSetupService;
import racingcar.ui.InputView;
import racingcar.ui.OutputView;
import racingcar.ui.console.InputReader;
import racingcar.ui.console.OutputWriter;
import racingcar.util.InputParser;
import racingcar.validation.AttemptValidator;
import racingcar.validation.CarNameValidator;
import racingcar.validation.InputValidator;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StubInputReaderQueued implements InputReader {
    private final Queue<String> inputs = new LinkedList<>();

    public void addInput(String input) {
        inputs.add(input);
    }

    @Override
    public String readLine() {
        return inputs.poll();
    }
}

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

class RaceControllerTest {

    private StubInputReaderQueued stubInputReader;
    private StubOutputWriter stubOutputWriter;
    private RaceController raceController;

    @BeforeEach
    void setUp() {
        stubInputReader = new StubInputReaderQueued();
        InputView inputView = new InputView(stubInputReader);
        stubOutputWriter = new StubOutputWriter();
        OutputView outputView = new OutputView(stubOutputWriter);

        CarNameValidator carNameValidator = new CarNameValidator();
        AttemptValidator attemptValidator = new AttemptValidator();

        InputValidator inputValidator = new InputValidator(carNameValidator, attemptValidator);
        InputParser inputParser = new InputParser();
        RaceSetupService raceSetupService = new RaceSetupService(inputView, inputValidator, inputParser);
        MoveStrategy moveStrategy = new AlwaysMoveStrategy();
        raceController = new RaceController(raceSetupService, outputView, moveStrategy);
    }


    @Test
    @DisplayName("start 메서드는 전체 게임 흐름을 실행하고 최종 우승자를 올바르게 출력해야 한다.")
    void start_shouldRunFullRaceAndPrintWinner() {
        // given
        stubInputReader.addInput("pobi,woni");
        stubInputReader.addInput("2");

        // when
        raceController.start();

        // then
        String output = stubOutputWriter.getOutput();
        String expectedOutput = """
                
                실행 결과
                pobi : -
                woni : -
                
                pobi : --
                woni : --
                
                최종 우승자 : pobi,woni""".trim();

        assertThat(output).isEqualTo(expectedOutput);
    }

    @Test
    @DisplayName("유효하지 않은 자동차 이름 입력 시 예외를 전파해야 한다 (출력 없음).")
    void start_shouldPropagateExceptionOnInvalidCarNames() {
        // given
        stubInputReader.addInput("pobi,,woni");

        // when & then
        assertThatThrownBy(() -> raceController.start())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차 이름이 빈칸입니다.");

        String output = stubOutputWriter.getOutput();
        assertThat(output).isEmpty();
    }

    @Test
    @DisplayName("유효하지 않은 시도 횟수 입력 시 예외를 전파해야 한다 (출력 없음).")
    void start_shouldPropagateExceptionOnInvalidAttempt() {
        // given
        stubInputReader.addInput("pobi,woni");
        stubInputReader.addInput("abc");

        // when & then
        assertThatThrownBy(() -> raceController.start())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("숫자여야 합니다.");

        String output = stubOutputWriter.getOutput();
        assertThat(output).isEmpty();
    }
}

