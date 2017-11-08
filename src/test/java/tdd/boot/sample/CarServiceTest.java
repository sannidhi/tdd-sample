package tdd.boot.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static tdd.boot.sample.CarFactory.PRIUS;
import static tdd.boot.sample.CarFactory.listOfCars;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    private CarService carService;

    @Before
    public void setUp() throws Exception {
        carService = new CarService(carRepository);
    }

    @Test
    public void getCars_returnsListOfCars() {
        given(carRepository.findAll()).willReturn(listOfCars());

        List<Car> cars = carService.getCars();

        assertThat(cars.size()).isEqualTo(2);
    }

    @Test
    public void getCar_returnsDetailsOfCar() {
        given(carRepository.findByName(PRIUS.getName())).willReturn(PRIUS);

        Car car = carService.getCar(PRIUS.getName());

        assertThat(car.getDescription()).isEqualTo(PRIUS.getDescription());
    }

}