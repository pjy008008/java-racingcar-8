package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import racingcar.domain.strategy.MoveStrategy;
import racingcar.domain.strategy.AlwaysMoveStrategy;
import racingcar.domain.strategy.NeverMoveStrategy;
import racingcar.domain.strategy.RandomMoveStrategy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RaceTest {
    private Car car1;
    private Car car2;
    private Car car3;
    private Cars cars;

    @BeforeEach
    void setUp() {
        car1 = new Car("pobi");
        car2 = new Car("woni");
        car3 = new Car("jun");
        cars = new Cars(List.of(car1, car2, car3));
    }

    @Nested
    @DisplayName("runOneRound 메서드는")
    class Describe_runOneRound {

        @Test
        @DisplayName("모든 자동차가 움직일 수 있을 때 모든 자동차를 전진시킨다.")
        void shouldMoveAllCarsWhenStrategyAllows() {
            // given
            MoveStrategy alwaysMove = new AlwaysMoveStrategy();
            Race race = new Race(cars, alwaysMove);

            // when
            race.runOneRound();

            // then
            assertThat(car1.getPosition()).isEqualTo(1);
            assertThat(car2.getPosition()).isEqualTo(1);
            assertThat(car3.getPosition()).isEqualTo(1);
        }

        @Test
        @DisplayName("어떤 자동차도 움직일 수 없을 때 아무도 전진시키지 않는다.")
        void shouldNotMoveAnyCarWhenStrategyDenies() {
            // given
            MoveStrategy neverMove = new NeverMoveStrategy();
            Race race = new Race(cars, neverMove);

            // when
            race.runOneRound();

            // then
            assertThat(car1.getPosition()).isEqualTo(0);
            assertThat(car2.getPosition()).isEqualTo(0);
            assertThat(car3.getPosition()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("getWinner 메서드는")
    class Describe_getWinner {

        @Test
        @DisplayName("가장 멀리 이동한 자동차 한 대를 정확히 반환한다.")
        void shouldReturnSingleWinnerCorrectly() {
            // given
            car1.move();
            car1.move();
            car2.move();

            Race race = new Race(cars, new RandomMoveStrategy());

            // when
            Cars winners = race.getWinner();

            // then
            assertThat(winners.getCarList()).hasSize(1);
            assertThat(winners.getCarList()).containsExactly(car1);
        }

        @Test
        @DisplayName("가장 멀리 이동한 자동차가 여러 대일 경우 모두 반환한다.")
        void shouldReturnMultipleWinnersCorrectly() {
            // given
            car1.move();
            car1.move();
            car2.move();
            car3.move();
            car3.move();

            Race race = new Race(cars, new RandomMoveStrategy());

            // when
            Cars winners = race.getWinner();

            // then
            assertThat(winners.getCarList()).hasSize(2);
            assertThat(winners.getCarList()).containsExactlyInAnyOrder(car1, car3);
        }

        @Test
        @DisplayName("모든 자동차의 위치가 같을 경우 모두 반환한다.")
        void shouldReturnAllCarsWhenPositionsAreEqual() {
            // given
            car1.move();
            car2.move();
            car3.move();

            Race race = new Race(cars, new RandomMoveStrategy());

            // when
            Cars winners = race.getWinner();

            // then
            assertThat(winners.getCarList()).hasSize(3);
            assertThat(winners.getCarList()).containsExactlyInAnyOrder(car1, car2, car3);
        }

        @Test
        @DisplayName("자동차가 없을 경우 빈 Cars 객체를 반환한다.")
        void shouldReturnEmptyCarsWhenNoCarsExist() {
            // given
            Cars emptyCars = new Cars(List.of());
            Race race = new Race(emptyCars, new RandomMoveStrategy());

            // when
            Cars winners = race.getWinner();

            // then
            assertThat(winners.getCarList()).isEmpty();
        }
    }

    @Test
    @DisplayName("getCars 메서드는 내부 Cars 객체를 반환해야 한다.")
    void shouldReturnInternalCarsObject() {
        // given
        Race race = new Race(cars, new RandomMoveStrategy());

        // when
        Cars retrievedCars = race.getCars();

        // then
        assertThat(retrievedCars).isSameAs(cars);
    }
}