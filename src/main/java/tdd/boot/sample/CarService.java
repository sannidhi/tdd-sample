package tdd.boot.sample;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Cacheable("cars")
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public Car getCar(String name) {
        return carRepository.findByName(name);
    }
}
