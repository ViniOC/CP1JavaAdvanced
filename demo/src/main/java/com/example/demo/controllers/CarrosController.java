package com.example.demo.controllers;

import com.example.demo.domainmodel.Carros;
import com.example.demo.service.CarrosService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
@AllArgsConstructor
public class CarrosController {
    @Autowired
    private final CarrosService service;

    @GetMapping("/")
    public ResponseEntity<List<Carros>> listAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }
    //http://localhost:8080/api/{id}
    @GetMapping("{id}")
    public ResponseEntity<Carros> findById(@PathVariable Long id){
        System.out.println("http://localhost:8080/api/" + id);
        Carros emp = this.service.getById(id);
        if(emp == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<>(emp, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Carros> removeCarById(@PathVariable Long id){
        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
