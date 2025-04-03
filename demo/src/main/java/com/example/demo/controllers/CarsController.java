package com.example.demo.controllers;

import com.example.demo.domainmodel.Cars;
import com.example.demo.domainmodel.TypeCars;
import com.example.demo.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carros")
public class CarsController {

    @Autowired
    private CarsService service;

    @GetMapping("/")
    public ResponseEntity<List<Cars>> listAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cars> findById(@PathVariable Long id){
        System.out.println("http://localhost:8080/api/carros/" + id);
        Cars carro = this.service.getById(id);
        if(carro == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(carro, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cars> removeCarById(@PathVariable Long id){
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Cars> addCarro(@RequestBody Cars carro){
        Cars carroCreated = this.service.save(carro);
        return new ResponseEntity<>(carroCreated, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cars> updateCarro(@PathVariable Long id, @RequestBody Cars carro){
        Cars databaseCars = this.service.getById(id);
        databaseCars.setMarca( carro.getMarca());
        databaseCars.setModelo(carro.getModelo());
        databaseCars.setAno(carro.getAno());
        databaseCars.setPotencia(carro.getPotencia());
        databaseCars.setEconomia(carro.getEconomia());
        databaseCars.setTipo(carro.getTipo());
        databaseCars.setPreco(carro.getPreco());
        this.service.update(databaseCars);
        return new ResponseEntity<>(databaseCars, HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Cars> patchCarro(@PathVariable Long id, @RequestBody Map<String, Object>updates){
        Cars updateCarro = this.service.partialUpdate(id, updates);
        if(updateCarro != null){
            return new ResponseEntity<>(updateCarro, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/potencia")
    public ResponseEntity<List<Cars>> top10PorPotencia() {
        return new ResponseEntity<>(
                this.service.getTop10ByPotencia(),
                HttpStatus.OK
        );
    }

    @GetMapping("/economia")
    public ResponseEntity<List<Cars>> top10PorEconomia() {
        return new ResponseEntity<>(
                this.service.getTop10ByEconomia(),
                HttpStatus.OK
        );
    }

    // Novo endpoint - Carros elétricos
    @GetMapping("/eletricos")
    public ResponseEntity<List<Cars>> listarEletricos() {
        return new ResponseEntity<>(
                this.service.getByTipo(TypeCars.ELETRICO),
                HttpStatus.OK
        );
    }
}