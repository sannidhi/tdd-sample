package tdd.boot.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tdd.boot.sample.CarFactory.PRIUS;
import static tdd.boot.sample.CarFactory.listOfCars;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CarControllerTest {

    @MockBean
    private CarService carService;

    @Autowired
    private CarController carController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void cars_returnsAllCars() throws Exception {
        given(carService.getCars()).willReturn(listOfCars());

        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].name").value("model-x"))
                .andExpect(jsonPath("$.[0].description").value("full-sized, all-electric"));

        verify(carService).getCars();
    }

    @Test
    public void getCar_returnsCarWithNameAndDescription() throws Exception {
        given(carService.getCar("prius")).willReturn(PRIUS);

        mockMvc.perform(get("/car/{name}", "prius"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(PRIUS.getName()))
                .andExpect(jsonPath("description").value(PRIUS.getDescription()));

        verify(carService).getCar(PRIUS.getName());
    }
}
