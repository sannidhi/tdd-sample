package tdd.boot.sample;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, String> {
    public List<Car> findAll();

    public Car findByName(String name);
}
