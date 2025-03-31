package com.example.demo.domainmodel;

import lombok.*;
import java.util.Objects;



@NoArgsConstructor
public class Carros {




    private @Getter @Setter Long id;

    private @Getter @Setter String marca;          // Fabricante do veículo
    private @Getter @Setter String modelo;         // Nome do modelo
    private @Getter @Setter Integer ano;           // Ano de fabricação
    private @Getter @Setter Integer potencia;      // Potência em cavalos (HP)
    private @Getter @Setter Double economia;       // Economia (km/litro ou km/kWh)


    private @Getter @Setter TipoCarro tipo;        // Tipo (combustão, híbrido ou elétrico)

    private @Getter @Setter Double preco;          // Valor de mercado

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
}