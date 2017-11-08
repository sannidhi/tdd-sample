package tdd.boot.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static tdd.boot.sample.CarFactory.PRIUS;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class IntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private CarRepository carRepository;

	@Test
	public void getCar() {
	    given(carRepository.findByName("prius")).willReturn(PRIUS);

        ResponseEntity<Car> response = this.testRestTemplate.getForEntity("/car/prius", Car.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).contains(PRIUS.getName());
        assertThat(response.getBody().getDescription()).contains(PRIUS.getDescription());
    }
}
