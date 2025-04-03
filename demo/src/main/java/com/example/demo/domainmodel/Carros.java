package com.example.demo.domainmodel;

import lombok.*;
import java.util.Objects;




public class Carros {




    private @Getter @Setter Long id;

    private @Getter @Setter String marca;          // Fabricante do veículo
    private @Getter @Setter String modelo;         // Nome do modelo
    private @Getter @Setter Integer ano;           // Ano de fabricação
    private @Getter @Setter Integer potencia;      // Potência em cavalos (HP)
    private @Getter @Setter Double economia;       // Economia (km/litro ou km/kWh)


    private @Getter @Setter TipoCarro tipo;        // Tipo (combustão, híbrido ou elétrico)

    private @Getter @Setter Double preco;          // Valor de mercado

    public Carros() {
    }

    // Construtor sem ID (para criação de novos registros)
    public Carros(String marca, String modelo, Integer ano, Integer potencia,
                 Double economia, TipoCarro tipo, Double preco) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.potencia = potencia;
        this.economia = economia;
        this.tipo = tipo;
        this.preco = preco;
    }

    // Construtor com ID (para atualizações)
    public Carros(Long id, String marca, String modelo, Integer ano, Integer potencia,
                 Double economia, TipoCarro tipo, Double preco) {
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
        Carros carro = (Carros) o;
        return Objects.equals(id, carro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", potencia=" + potencia + "HP" +
                ", economia=" + economia + (tipo == TipoCarro.ELETRICO ? "km/kWh" : "km/l") +
                ", tipo=" + tipo +
                ", preco=R$" + preco +
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

    public TipoCarro getTipo() {
        return tipo;
    }

    public void setTipo(TipoCarro tipo) {
        this.tipo = tipo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }


}