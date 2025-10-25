package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CarsTest {
    @Test
    @DisplayName("생성 시 자동차 리스트를 올바르게 저장해야 한다.")
    void shouldInitializeWithCarList() {
        // given
        Car car1 = new Car("pobi");
        Car car2 = new Car("woni");
        List<Car> carList = List.of(car1, car2);

        // when
        Cars cars = new Cars(carList);

        // then
        assertThat(cars.getCarList()).isEqualTo(carList);
        assertThat(cars.getCarList()).containsExactly(car1, car2);
    }

    @Test
    @DisplayName("빈 리스트로 생성할 수 있어야 한다.")
    void shouldInitializeWithEmptyList() {
        // given
        List<Car> emptyList = Collections.emptyList();

        // when
        Cars cars = new Cars(emptyList);

        // then
        assertThat(cars.getCarList()).isNotNull();
        assertThat(cars.getCarList()).isEmpty();
    }

    @Test
    @DisplayName("getCarList 메서드는 내부 자동차 리스트를 반환해야 한다.")
    void shouldReturnInternalCarList() {
        // given
        Car car1 = new Car("jun");
        List<Car> initialList = new ArrayList<>();
        initialList.add(car1);
        Cars cars = new Cars(initialList);

        // when
        List<Car> retrievedList = cars.getCarList();

        // then
        assertThat(retrievedList).isSameAs(initialList);
        assertThat(retrievedList).containsExactly(car1);
    }
}