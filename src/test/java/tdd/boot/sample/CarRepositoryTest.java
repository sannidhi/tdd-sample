package tdd.boot.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static tdd.boot.sample.CarFactory.PRIUS;
import static tdd.boot.sample.CarFactory.listOfCars;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Before
    public void setUp() throws Exception {
        carRepository.save(listOfCars());
    }

    @After
    public void tearDown() throws Exception {
        carRepository.deleteAll();
    }

    @Test
    public void findAll_returnsAllCarsInRepository() throws Exception {
        List<Car> cars = carRepository.findAll();
        assertThat(cars.size()).isEqualTo(2);
    }

    @Test
    public void findByName_returnsCarDescription() {
        Car car = carRepository.findByName(PRIUS.getName());

        assertThat(car.getDescription()).contains("hybrid");
    }
}