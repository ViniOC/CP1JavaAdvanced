package com.example.demo.service;

import com.example.demo.datasource.repositories.MockCars;
import com.example.demo.domainmodel.Cars;
import com.example.demo.domainmodel.TypeCars;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CarsService {
    @Autowired
    private MockCars repository;

    public List<Cars> getAll(){
        return this.repository.getAll();
    }

    public Cars getById(Long id){
        return this.repository.findById(id);
    }

    public Cars save(Cars cars){
        return this.repository.save(cars);
    }

    public Cars update(Cars cars){
        return this.repository.save(cars);
    }

    public void deleteById(Long id){
        this.repository.deleteById(id);
    }

    public void delete(Cars cars){
        this.repository.delete(cars);
    }

    // Novo método - Top 10 por potência
    public List<Cars> getTop10ByPotencia() {
        return this.repository.getAll().stream()
                .sorted(Comparator.comparingInt(Cars::getPotencia).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    // Novo método - Top 10 por economia
    public List<Cars> getTop10ByEconomia() {
        return this.repository.getAll().stream()
                .sorted(Comparator.comparingDouble(Cars::getEconomia).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Cars> getByTipo(TypeCars tipo) {
        return this.repository.getAll().stream()
                .filter(carro -> carro.getTipo() == tipo)
                .collect(Collectors.toList());
    }

    public Cars partialUpdate(Long id, Map<String, Object> updates){
        Cars cars = this.repository.findById(id);
        for(Map.Entry<String, Object> entry : updates.entrySet()){
            switch (entry.getKey()){
                case "id": cars.setId(Long.parseLong(entry.getValue().toString())); break;
                case "marca": cars.setMarca(entry.getValue().toString()); break;
                case "modelo": cars.setModelo(entry.getValue().toString()); break;
                case "ano": cars.setAno(Integer.parseInt(entry.getValue().toString())); break;
                case "potencia": cars.setPotencia(Integer.parseInt(entry.getValue().toString())); break;
                case "economia": cars.setEconomia(Double.parseDouble(entry.getValue().toString())); break;
                case "tipo": cars.setTipo(Enum.valueOf(TypeCars.class, entry.getValue().toString())); break;
                case "preco": cars.setPreco(Double.parseDouble(entry.getValue().toString())); break;
            }
        }
        return this.repository.save(cars);
    }
}