package com.example.demo.datasource.repositories;


import com.example.demo.domainmodel.Cars;
import com.example.demo.domainmodel.TypeCars;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MockCars {

    private final List<Cars> dataset = new ArrayList<>();

    public MockCars() {
        this.dataset.add(new Cars(1L, "Toyota", "Corolla", 2022, 169, 14.0, TypeCars.COMBUSTAO, 120000.00));
        this.dataset.add(new Cars(2L, "Tesla", "Model 3", 2023, 283, 6.0, TypeCars.ELETRICO, 250000.00));
        this.dataset.add(new Cars(3L, "Honda", "Civic", 2021, 155, 12.0, TypeCars.COMBUSTAO, 110000.00));
        this.dataset.add(new Cars(4L, "BMW", "330e", 2022, 292, 20.0, TypeCars.HIBRIDO, 280000.00));
        this.dataset.add(new Cars(5L, "Ford", "Mustang", 2020, 450, 8.0, TypeCars.COMBUSTAO, 350000.00));
        this.dataset.add(new Cars(6L, "Chevrolet", "Bolt EV", 2023, 200, 7.5, TypeCars.ELETRICO, 220000.00));
        this.dataset.add(new Cars(7L, "Volkswagen", "Golf GTE", 2021, 245, 18.5, TypeCars.HIBRIDO, 190000.00));
        this.dataset.add(new Cars(8L, "Mercedes-Benz", "EQC", 2022, 408, 5.5, TypeCars.ELETRICO, 400000.00));
        this.dataset.add(new Cars(9L, "Hyundai", "Ioniq 5", 2023, 325, 6.5, TypeCars.ELETRICO, 270000.00));
        this.dataset.add(new Cars(10L, "Audi", "e-tron", 2022, 355, 5.8, TypeCars.ELETRICO, 380000.00));
        this.dataset.add(new Cars(11L, "Porsche", "Taycan", 2023, 616, 4.5, TypeCars.ELETRICO, 550000.00));
        this.dataset.add(new Cars(12L, "Nissan", "Leaf", 2022, 147, 6.0, TypeCars.ELETRICO, 180000.00));
        this.dataset.add(new Cars(13L, "Jeep", "Compass", 2021, 170, 10.0, TypeCars.COMBUSTAO, 160000.00));
        this.dataset.add(new Cars(14L, "Ferrari", "488", 2020, 670, 5.0, TypeCars.COMBUSTAO, 2500000.00));
        this.dataset.add(new Cars(15L, "Lamborghini", "Huracan", 2021, 640, 4.5, TypeCars.COMBUSTAO, 2300000.00));
        this.dataset.add(new Cars(16L, "Volvo", "XC60", 2023, 250, 18.0, TypeCars.HIBRIDO, 320000.00));
        this.dataset.add(new Cars(17L, "Peugeot", "e-208", 2022, 136, 7.0, TypeCars.ELETRICO, 150000.00));
        this.dataset.add(new Cars(18L, "Renault", "Kwid E-Tech", 2023, 65, 9.0, TypeCars.ELETRICO, 120000.00));
        this.dataset.add(new Cars(19L, "Jaguar", "I-PACE", 2022, 400, 5.2, TypeCars.ELETRICO, 450000.00));
        this.dataset.add(new Cars(20L, "Mitsubishi", "Outlander PHEV", 2023, 236, 22.0, TypeCars.HIBRIDO, 210000.00));
        this.dataset.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
    }

    public List<Cars> getAll() {
        return dataset;
    }

    public Cars save(Cars car) {
        this.dataset.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
        Long lastID = this.dataset.isEmpty() ? 0 : this.dataset.get(this.dataset.size() - 1).getId();
        car.setId(lastID + 1);
        this.dataset.add(car);
        return car;
    }

    public void deleteById(Long id) {
        this.dataset.removeIf(car -> car.getId().equals(id));
    }

    public void delete(Cars car) {
        this.dataset.removeIf(c -> c.getId().equals(car.getId()));
    }

    public Cars findById(Long id) {
        return this.dataset.stream()
                .filter(car -> car.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}

