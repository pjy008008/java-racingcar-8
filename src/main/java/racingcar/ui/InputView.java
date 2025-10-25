package racingcar.ui;

import racingcar.ui.console.InputReader;

public class InputView {
    private static final String CAR_NAME_PROMPT = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ATTEMPT_PROMPT = "시도할 횟수는 몇 회인가요?";

    private final InputReader inputReader;

    public InputView(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    public String readCarNames() {
        System.out.println(CAR_NAME_PROMPT);
        return inputReader.readLine();
    }

    public String readAttempt() {
        System.out.println(ATTEMPT_PROMPT);
        return inputReader.readLine();
    }
}
