package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CarTest {
    private static final String VALID_NAME = "pobi";

    @Test
    @DisplayName("자동차 생성 시 이름과 초기 위치(0)를 올바르게 설정해야 한다.")
    void shouldInitializeNameAndZeroPosition() {
        // given
        String expectedName = VALID_NAME;

        // when
        Car car = new Car(expectedName);

        // then
        assertThat(car.getName()).isEqualTo(expectedName);
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    @DisplayName("move 메서드를 호출하면 위치가 1 증가해야 한다.")
    void shouldIncrementPositionByOneWhenMoved() {
        // given
        Car car = new Car(VALID_NAME);
        int initialPosition = car.getPosition();

        // when
        car.move();

        // then
        int movedPosition = car.getPosition();
        assertThat(movedPosition).isEqualTo(initialPosition + 1);
    }

    @Test
    @DisplayName("move 메서드를 여러 번 호출하면 호출한 횟수만큼 위치가 증가해야 한다.")
    void shouldIncrementPositionCorrectlyWhenMovedMultipleTimes() {
        // given
        Car car = new Car(VALID_NAME);
        int moveCount = 3;

        // when
        for (int i = 0; i < moveCount; i++) {
            car.move();
        }

        // then
        assertThat(car.getPosition()).isEqualTo(moveCount);
    }

    @Test
    @DisplayName("getPosition 메서드는 현재 위치를 정확하게 반환해야 한다.")
    void shouldReturnCurrentPosition() {
        // given
        Car car = new Car(VALID_NAME);
        assertThat(car.getPosition()).isEqualTo(0);

        // when
        car.move();
        car.move();

        // then
        assertThat(car.getPosition()).isEqualTo(2);
    }

    @Test
    @DisplayName("getName 메서드는 생성 시 설정된 이름을 정확하게 반환해야 한다.")
    void shouldReturnCorrectName() {
        // given
        String name = VALID_NAME;
        Car car = new Car(name);

        // when
        String actualName = car.getName();

        // then
        assertThat(actualName).isEqualTo(name);
    }
}

