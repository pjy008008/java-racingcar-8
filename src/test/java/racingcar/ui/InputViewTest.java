package racingcar.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import racingcar.ui.console.InputReader;

import java.util.LinkedList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

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

class InputViewTest {
    private StubInputReaderQueued stubInputReader;
    private InputView inputView;

    @BeforeEach
    void setUp() {
        stubInputReader = new StubInputReaderQueued();
        inputView = new InputView(stubInputReader);
    }

    @Test
    @DisplayName("readCarNames는 InputReader의 readLine 결과를 반환해야 한다.")
    void shouldReturnCarNamesFromReader() {
        // given
        String simulatedInput = "pobi,woni";
        stubInputReader.addInput(simulatedInput);

        // when
        String actualInput = inputView.readCarNames();

        // then
        assertThat(actualInput).isEqualTo(simulatedInput);
    }

    @Test
    @DisplayName("readAttempt는 InputReader의 readLine 결과를 반환해야 한다.")
    void shouldReturnAttemptFromReader() {
        // given
        String simulatedInput = "5";
        stubInputReader.addInput(simulatedInput);

        // when
        String actualInput = inputView.readAttempt();

        // then
        assertThat(actualInput).isEqualTo(simulatedInput);
    }
}

