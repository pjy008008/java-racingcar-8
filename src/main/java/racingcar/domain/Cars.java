package racingcar.domain;

import java.util.List;

public class Cars {
    List<Car> cars;

    public Cars(List<Car> carList) {
        this.cars = carList;
    }

    public List<Car> getCarList() {
        return cars;
    }
}
