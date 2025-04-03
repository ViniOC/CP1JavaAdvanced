package com.example.demo.controllers;

import com.example.demo.domainmodel.Carros;
import com.example.demo.domainmodel.TipoCarro;
import com.example.demo.service.CarrosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carros")
public class CarrosController {

    @Autowired
    private CarrosService service;

    @GetMapping("/")
    public ResponseEntity<List<Carros>> listAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carros> findById(@PathVariable Long id){
        System.out.println("http://localhost:8080/api/carros/" + id);
        Carros carro = this.service.getById(id);
        if(carro == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(carro, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Carros> removeCarById(@PathVariable Long id){
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Carros> addCarro(@RequestBody Carros carro){
        Carros carroCreated = this.service.save(carro);
        return new ResponseEntity<>(carroCreated, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Carros> updateCarro(@PathVariable Long id, @RequestBody Carros carro){
        Carros databaseCarros = this.service.getById(id);
        databaseCarros.setMarca( carro.getMarca());
        databaseCarros.setModelo(carro.getModelo());
        databaseCarros.setAno(carro.getAno());
        databaseCarros.setPotencia(carro.getPotencia());
        databaseCarros.setEconomia(carro.getEconomia());
        databaseCarros.setTipo(carro.getTipo());
        databaseCarros.setPreco(carro.getPreco());
        this.service.update(databaseCarros);
        return new ResponseEntity<>(databaseCarros, HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Carros> patchCarro(@PathVariable Long id, @RequestBody Map<String, Object>updates){
        Carros updateCarro = this.service.partialUpdate(id, updates);
        if(updateCarro != null){
            return new ResponseEntity<>(updateCarro, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/potencia")
    public ResponseEntity<List<Carros>> top10PorPotencia() {
        return new ResponseEntity<>(
                this.service.getTop10ByPotencia(),
                HttpStatus.OK
        );
    }

    @GetMapping("/economia")
    public ResponseEntity<List<Carros>> top10PorEconomia() {
        return new ResponseEntity<>(
                this.service.getTop10ByEconomia(),
                HttpStatus.OK
        );
    }

    // Novo endpoint - Carros elétricos
    @GetMapping("/eletricos")
    public ResponseEntity<List<Carros>> listarEletricos() {
        return new ResponseEntity<>(
                this.service.getByTipo(TipoCarro.ELETRICO),
                HttpStatus.OK
        );
    }
}