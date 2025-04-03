package com.example.demo.domainmodel;

import lombok.*;
import java.util.Objects;




public class Cars {




    private @Getter @Setter Long id;

    private @Getter @Setter String marca;
    private @Getter @Setter String modelo;
    private @Getter @Setter Integer ano;
    private @Getter @Setter Integer potencia;
    private @Getter @Setter Double economia;


    private @Getter @Setter TypeCars tipo;

    private @Getter @Setter Double preco;

    public Cars() {
    }

    // Construtor sem ID (para criação de novos registros)
    public Cars(String marca, String modelo, Integer ano, Integer potencia,
                Double economia, TypeCars tipo, Double preco) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.potencia = potencia;
        this.economia = economia;
        this.tipo = tipo;
        this.preco = preco;
    }

    // Construtor com ID (para atualizações)
    public Cars(Long id, String marca, String modelo, Integer ano, Integer potencia,
                Double economia, TypeCars tipo, Double preco) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.potencia = potencia;
        this.economia = economia;
        this.tipo = tipo;
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cars carro = (Cars) o;
        return Objects.equals(id, carro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "{id=" + id +
                ", marca=" + marca +
                ", modelo=" + modelo +
                ", ano=" + ano +
                ", potencia=" + potencia +
                ", economia=" + economia +
                ", tipo=" + tipo +
                ", preco=" + preco +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    public Double getEconomia() {
        return economia;
    }

    public void setEconomia(Double economia) {
        this.economia = economia;
    }

    public TypeCars getTipo() {
        return tipo;
    }

    public void setTipo(TypeCars tipo) {
        this.tipo = tipo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }


}