package com.example.demo.datasource.repositories;


import com.example.demo.domainmodel.Carros;
import com.example.demo.domainmodel.TipoCarro;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MockCarros {

    private final List<Carros> dataset = new ArrayList<>();

    public MockCarros() {
        this.dataset.add(new Carros(1L, "Toyota", "Corolla", 2022, 169, 14.0, TipoCarro.COMBUSTAO, 120000.00));
        this.dataset.add(new Carros(2L, "Tesla", "Model 3", 2023, 283, 6.0, TipoCarro.ELETRICO, 250000.00));
        this.dataset.add(new Carros(3L, "Honda", "Civic", 2021, 155, 12.0, TipoCarro.COMBUSTAO, 110000.00));
        this.dataset.add(new Carros(4L, "BMW", "330e", 2022, 292, 20.0, TipoCarro.HIBRIDO, 280000.00));
        this.dataset.add(new Carros(5L, "Ford", "Mustang", 2020, 450, 8.0, TipoCarro.COMBUSTAO, 350000.00));
        this.dataset.add(new Carros(6L, "Chevrolet", "Bolt EV", 2023, 200, 7.5, TipoCarro.ELETRICO, 220000.00));
        this.dataset.add(new Carros(7L, "Volkswagen", "Golf GTE", 2021, 245, 18.5, TipoCarro.HIBRIDO, 190000.00));
        this.dataset.add(new Carros(8L, "Mercedes-Benz", "EQC", 2022, 408, 5.5, TipoCarro.ELETRICO, 400000.00));
        this.dataset.add(new Carros(9L, "Hyundai", "Ioniq 5", 2023, 325, 6.5, TipoCarro.ELETRICO, 270000.00));
        this.dataset.add(new Carros(10L, "Audi", "e-tron", 2022, 355, 5.8, TipoCarro.ELETRICO, 380000.00));
        this.dataset.add(new Carros(11L, "Porsche", "Taycan", 2023, 616, 4.5, TipoCarro.ELETRICO, 550000.00));
        this.dataset.add(new Carros(12L, "Nissan", "Leaf", 2022, 147, 6.0, TipoCarro.ELETRICO, 180000.00));
        this.dataset.add(new Carros(13L, "Jeep", "Compass", 2021, 170, 10.0, TipoCarro.COMBUSTAO, 160000.00));
        this.dataset.add(new Carros(14L, "Ferrari", "488", 2020, 670, 5.0, TipoCarro.COMBUSTAO, 2500000.00));
        this.dataset.add(new Carros(15L, "Lamborghini", "Huracan", 2021, 640, 4.5, TipoCarro.COMBUSTAO, 2300000.00));
        this.dataset.add(new Carros(16L, "Volvo", "XC60", 2023, 250, 18.0, TipoCarro.HIBRIDO, 320000.00));
        this.dataset.add(new Carros(17L, "Peugeot", "e-208", 2022, 136, 7.0, TipoCarro.ELETRICO, 150000.00));
        this.dataset.add(new Carros(18L, "Renault", "Kwid E-Tech", 2023, 65, 9.0, TipoCarro.ELETRICO, 120000.00));
        this.dataset.add(new Carros(19L, "Jaguar", "I-PACE", 2022, 400, 5.2, TipoCarro.ELETRICO, 450000.00));
        this.dataset.add(new Carros(20L, "Mitsubishi", "Outlander PHEV", 2023, 236, 22.0, TipoCarro.HIBRIDO, 210000.00));
        this.dataset.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
    }

    public List<Carros> getAll() {
        return dataset;
    }

    public Carros save(Carros car) {
        this.dataset.sort((o1, o2) -> o1.getId().compareTo(o2.getId()));
        Long lastID = this.dataset.isEmpty() ? 0 : this.dataset.get(this.dataset.size() - 1).getId();
        car.setId(lastID + 1);
        this.dataset.add(car);
        return car;
    }

    public void deleteById(Long id) {
        this.dataset.removeIf(car -> car.getId().equals(id));
    }

    public void delete(Carros car) {
        this.dataset.removeIf(c -> c.getId().equals(car.getId()));
    }

    public Carros findById(Long id) {
        return this.dataset.stream()
                .filter(car -> car.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}