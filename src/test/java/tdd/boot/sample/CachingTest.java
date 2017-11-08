package tdd.boot.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static tdd.boot.sample.CarFactory.listOfCars;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class CachingTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Test
    public void testCaching_getAllCarsEndpoint() {
        List<Car> updatedList = listOfCars();
        updatedList.add(new Car(3, "golf", "vw-car"));
        given(carRepository.findAll()).willReturn(listOfCars(), updatedList);

        List<Car> firstResponse = carService.getCars();
        assertThat(firstResponse.size()).isEqualTo(2);

        List<Car> secondResponse = carService.getCars();
        assertThat(secondResponse.size()).isEqualTo(2);
        verify(carRepository, times(1)).findAll();
    }
}