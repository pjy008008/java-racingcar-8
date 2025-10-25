package racingcar.ui;

import racingcar.ui.console.InputReader;

public class InputView {
    private final InputReader inputReader;

    public InputView(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    public String readCarNames() {
        return inputReader.readLine();
    }

    public String readAttempt() {
        return inputReader.readLine();
    }
}
