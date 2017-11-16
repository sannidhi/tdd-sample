package tdd.boot.sample;

import org.assertj.core.util.Lists;

import java.util.List;

class CarFactory {
    static final Car MODEL_X = new Car(1, "model-x", "full-sized, all-electric");
    static final Car PRIUS = new Car(2, "prius", "hybrid car");


    static List<Car> listOfCars() {
        return Lists.newArrayList(MODEL_X, PRIUS);
    }
}
