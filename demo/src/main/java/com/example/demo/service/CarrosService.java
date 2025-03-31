package com.example.demo.service;


import com.example.demo.datasource.repositories.MockCarros;
import com.example.demo.domainmodel.Carros;
import com.example.demo.domainmodel.TipoCarro;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class CarrosService {
    @Autowired
    private MockCarros repository;

    public List<Carros> getAll(){
        return this.repository.getAll();
    }
    public Carros getById(Long id){
        return this.repository.findById(id);
    }
    public Carros save(Carros carros){
        return this.repository.save(carros);
    }
    public Carros update(Carros carros){
        return this.repository.save(carros);
    }
    public void deleteById(Long id){
         this.repository.deleteById(id);
    }
    public void delete(Carros carros){
        this.repository.delete(carros);
    }

    public Carros partialUpdate(Long id, Map<String, Object> updates){
        Carros carros = this.repository.findById(id);
        for(Map.Entry<String, Object> entry : updates.entrySet()){
            switch (entry.getKey()){
                case "id": carros.setId(Long.parseLong(entry.getValue().toString())); break;
                case "marca": carros.setMarca(entry.getValue().toString()); break;
                case "modelo": carros.setModelo(entry.getValue().toString()); break;
                case "ano": carros.setAno(Integer.parseInt(entry.getValue().toString())); break;
                case "potencia": carros.setPotencia(Integer.parseInt(entry.getValue().toString())); break;
                case "economia": carros.setEconomia(Double.parseDouble(entry.getValue().toString())); break;
                case "tipo": carros.setTipo(Enum.valueOf(TipoCarro.class, entry.getValue().toString())); break;
                case "preco": carros.setPreco(Double.parseDouble(entry.getValue().toString())); break;

            }

        }
        return this.repository.save(carros);
    }
}
