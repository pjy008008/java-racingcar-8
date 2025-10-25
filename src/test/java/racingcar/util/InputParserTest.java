package racingcar.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InputParserTest {
    private final InputParser inputParser = new InputParser();

    @Test
    @DisplayName("쉼표로 구분된 문자열을 올바르게 리스트로 분리해야 한다.")
    void shouldParseCommaSeparatedStringToList() {
        // given
        String input = "pobi,woni,jun";

        // when
        List<String> result = inputParser.parse(input);

        // then
        assertThat(result).containsExactly("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("각 이름의 앞뒤 공백을 제거하고 분리해야 한다.")
    void shouldTrimWhitespaceAndParseToList() {
        // given
        String input = "pobi  , woni ,  jun";

        // when
        List<String> result = inputParser.parse(input);

        // then
        assertThat(result).containsExactly("pobi", "woni", "jun");
    }

    @Test
    @DisplayName("쉼표가 없는 단일 이름 문자열도 리스트로 반환해야 한다.")
    void shouldParseSingleNameStringToList() {
        // given
        String input = "pobi";

        // when
        List<String> result = inputParser.parse(input);

        // then
        assertThat(result).containsExactly("pobi");
    }
}