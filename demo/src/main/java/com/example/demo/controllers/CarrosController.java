package com.example.demo.controllers;

import com.example.demo.domainmodel.Carros;
import com.example.demo.domainmodel.TipoCarro;
import com.example.demo.service.CarrosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
@RequiredArgsConstructor
public class CarrosController {

    private final CarrosService service;

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