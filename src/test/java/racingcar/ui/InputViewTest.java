package racingcar.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
// Mockito 관련 import 제거
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
import racingcar.ui.console.InputReader; // InputReader 인터페이스 import

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList; // StubInputReaderQueued 위해 추가
import java.util.Queue;    // StubInputReaderQueued 위해 추가

import static org.assertj.core.api.Assertions.assertThat;
// Mockito 관련 import 제거
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class) 제거

// 테스트용 Stub InputReader (큐 방식)
class StubInputReaderQueued implements InputReader {
    private final Queue<String> inputs = new LinkedList<>();

    public void addInput(String input) {
        inputs.add(input);
    }

    @Override
    public String readLine() {
        if (inputs.isEmpty()) {
            throw new IllegalStateException("No more inputs set for StubInputReaderQueued");
        }
        return inputs.poll();
    }
}


class InputViewTest {

    // System.out 출력을 캡처하기 위한 설정
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    // Mock 대신 Stub InputReader 사용
    private StubInputReaderQueued stubInputReader;
    // InjectMocks 대신 직접 InputView 생성
    private InputView inputView;

    @BeforeEach
    void setUp() {
        // 각 테스트 전에 System.out의 출력 방향을 변경
        System.setOut(new PrintStream(outputStreamCaptor));
        // Stub InputReader와 실제 InputView 생성 및 주입
        stubInputReader = new StubInputReaderQueued();
        inputView = new InputView(stubInputReader);
    }

    @AfterEach
    void tearDown() {
        // 각 테스트 후에 System.out을 원래대로 복구
        System.setOut(standardOut);
        // Stub 버퍼 클리어 (선택적)
        outputStreamCaptor.reset();
    }

    @Test
    @DisplayName("readCarNames 호출 시 자동차 이름 입력 프롬프트를 출력하고 InputReader의 readLine 결과를 반환해야 한다.")
    void shouldPrintPromptAndReadCarNames() {
        // given
        String expectedPrompt = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
        String simulatedInput = "pobi,woni";
        // StubInputReader에 입력값 설정
        stubInputReader.addInput(simulatedInput);

        // when
        String actualInput = inputView.readCarNames();

        // then
        // 1. 프롬프트가 올바르게 출력되었는지 검증
        String printedOutput = outputStreamCaptor.toString().replace("\r\n", "\n").trim();
        assertThat(printedOutput).isEqualTo(expectedPrompt);

        // 2. InputReader.readLine() 호출 검증은 Mockito 없이 직접 어려움
        //    대신 반환값이 올바른지로 간접 확인

        // 3. 반환된 값이 StubInputReader가 반환한 값과 동일한지 검증
        assertThat(actualInput).isEqualTo(simulatedInput);
    }

    @Test
    @DisplayName("readAttempt 호출 시 시도 횟수 입력 프롬프트를 출력하고 InputReader의 readLine 결과를 반환해야 한다.")
    void shouldPrintPromptAndReadAttempt() {
        // given
        String expectedPrompt = "시도할 횟수는 몇 회인가요?";
        String simulatedInput = "5";
        // StubInputReader에 입력값 설정
        stubInputReader.addInput(simulatedInput);

        // when
        String actualInput = inputView.readAttempt();

        // then
        // 1. 프롬프트가 올바르게 출력되었는지 검증
        String printedOutput = outputStreamCaptor.toString().replace("\r\n", "\n").trim();
        assertThat(printedOutput).isEqualTo(expectedPrompt);

        // 2. InputReader.readLine() 호출 검증은 Mockito 없이 직접 어려움

        // 3. 반환된 값이 StubInputReader가 반환한 값과 동일한지 검증
        assertThat(actualInput).isEqualTo(simulatedInput);
    }
}

