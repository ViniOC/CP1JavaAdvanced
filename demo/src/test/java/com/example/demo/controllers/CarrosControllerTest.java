package com.example.demo.controllers;

import com.example.demo.domainmodel.Cars;
import com.example.demo.domainmodel.TypeCars;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarrosControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }


    @Test
    public void testListAllCarros() {
        given()
                .when()
                .get("/api/carros/")
                .then()
                .statusCode(200)
                .body("$", not(empty()));
    }


    @Test
    public void testFindCarroById() {

        Long carroId = given()
                .contentType(ContentType.JSON)
                .body("{ \"marca\": \"Tesla\", \"modelo\": \"Model S\", \"ano\": 2023 }")
                .when()
                .post("/api/carros")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath().getLong("id");

        // Agora busca pelo ID
        given()
                .when()
                .get("/api/carros/" + carroId)
                .then()
                .statusCode(200)
                .body("marca", equalTo("Tesla"));
    }


    @Test
    public void testDeleteCarroById() {
        // Cria um carro para deletar
        Long carroId = given()
                .contentType(ContentType.JSON)
                .body("{ \"marca\": \"Ford\", \"modelo\": \"Mustang\", \"ano\": 2022 }")
                .when()
                .post("/api/carros")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath().getLong("id");


        given()
                .when()
                .delete("/api/carros/" + carroId)
                .then()
                .statusCode(204);
    }


    @Test
    public void testAddCarro() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"marca\": \"Toyota\", \"modelo\": \"Corolla\", \"ano\": 2023 }")
                .when()
                .post("/api/carros")
                .then()
                .statusCode(201)
                .body("marca", equalTo("Toyota"));
    }

    @Test
    public void testUpdateCarro() {
        // Cria um carro
        Long carroId = given()
                .contentType(ContentType.JSON)
                .body("{ \"marca\": \"Honda\", \"modelo\": \"Civic\", \"ano\": 2021 }")
                .when()
                .post("/api/carros")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath().getLong("id");


        given()
                .contentType(ContentType.JSON)
                .body("{ \"marca\": \"Honda\", \"modelo\": \"Civic\", \"ano\": 2022 }")
                .when()
                .put("/api/carros/" + carroId)
                .then()
                .statusCode(200)
                .body("ano", equalTo(2022));
    }


    @Test
    public void testPatchCarro() {
        // Cria um carro
        Long carroId = given()
                .contentType(ContentType.JSON)
                .body("{ \"marca\": \"Chevrolet\", \"modelo\": \"Onix\", \"ano\": 2020 }")
                .when()
                .post("/api/carros")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath().getLong("id");


        Map<String, Object> updates = new HashMap<>();
        updates.put("ano", 2023);

        given()
                .contentType(ContentType.JSON)
                .body(updates)
                .when()
                .patch("/api/carros/" + carroId)
                .then()
                .statusCode(200)
                .body("ano", equalTo(2023));
    }


    @Test
    public void testTop10PorPotencia() {
        List<Cars>carsList = new ArrayList<>();
        carsList.add(new Cars(14L, "Ferrari", "488", 2020, 670, 5.0, TypeCars.COMBUSTAO, 2500000.00));
        carsList.add(new Cars(15L, "Lamborghini", "Huracan", 2021, 640, 4.5, TypeCars.COMBUSTAO, 2300000.00));
        carsList.add(new Cars(11L, "Porsche", "Taycan", 2023, 616, 4.5, TypeCars.ELETRICO, 550000.00));
        carsList.add(new Cars(5L, "Ford", "Mustang", 2020, 450, 8.0, TypeCars.COMBUSTAO, 350000.00));
        carsList.add(new Cars(8L, "Mercedes-Benz", "EQC", 2022, 408, 5.5, TypeCars.ELETRICO, 400000.00));
        carsList.add(new Cars(19L, "Jaguar", "I-PACE", 2022, 400, 5.2, TypeCars.ELETRICO, 450000.00));
        carsList.add(new Cars(10L, "Audi", "e-tron", 2022, 355, 5.8, TypeCars.ELETRICO, 380000.00));
        carsList.add(new Cars(9L, "Hyundai", "Ioniq 5", 2023, 325, 6.5, TypeCars.ELETRICO, 270000.00));
        carsList.add(new Cars(4L, "BMW", "330e", 2022, 292, 20.0, TypeCars.HIBRIDO, 280000.00));
        carsList.add(new Cars(2L, "Tesla", "Model 3", 2023, 283, 6.0, TypeCars.ELETRICO, 250000.00));



        List<Cars> result = given()
                .when()
                .get("/api/carros/potencia")
                .then()
                .statusCode(200)
                .body("$", hasSize(lessThanOrEqualTo(10)))
                .body("[0].marca", equalTo("Ferrari"))
                .body("[0].modelo", equalTo("488"))
                .body("[0].ano", equalTo(2020))
                .body("[0].potencia", equalTo(carsList.get(0).getPotencia()))
                .body("[9].marca", equalTo("Tesla"))
                .body("[9].modelo", equalTo("Model 3"))
                .body("[9].ano", equalTo(2023))
                .body("[9].potencia", equalTo((carsList.get(9).getPotencia())))
                .extract().body().as(new TypeRef<>() {});
        assertEquals(carsList, result);
    }


    @Test
    public void testTop10PorEconomia() {
        List<Cars>carsList = new ArrayList<>();
        carsList.add(new Cars(20L, "Mitsubishi", "Outlander PHEV", 2023, 236, 22.0, TypeCars.HIBRIDO, 210000.00));
        carsList.add(new Cars(4L, "BMW", "330e", 2022, 292, 20.0, TypeCars.HIBRIDO, 280000.00));
        carsList.add(new Cars(7L, "Volkswagen", "Golf GTE", 2021, 245, 18.5, TypeCars.HIBRIDO, 190000.00));
        carsList.add(new Cars(16L, "Volvo", "XC60", 2023, 250, 18.0, TypeCars.HIBRIDO, 320000.00));
        carsList.add(new Cars(1L, "Toyota", "Corolla", 2022, 169, 14.0, TypeCars.COMBUSTAO, 120000.00));
        carsList.add(new Cars(3L, "Honda", "Civic", 2021, 155, 12.0, TypeCars.COMBUSTAO, 110000.00));
        carsList.add(new Cars(13L, "Jeep", "Compass", 2021, 170, 10.0, TypeCars.COMBUSTAO, 160000.00));
        carsList.add(new Cars(18L, "Renault", "Kwid E-Tech", 2023, 65, 9.0, TypeCars.ELETRICO, 120000.00));
        carsList.add(new Cars(5L, "Ford", "Mustang", 2020, 450, 8.0, TypeCars.COMBUSTAO, 350000.00));
        carsList.add(new Cars(6L, "Chevrolet", "Bolt EV", 2023, 200, 7.5, TypeCars.ELETRICO, 220000.00));


        List<Cars> result =given()
                .when()
                .get("/api/carros/economia")
                .then()
                .statusCode(200)
                .body("$", hasSize(lessThanOrEqualTo(10)))
                .body("[0].marca", equalTo("Mitsubishi"))
                .body("[0].modelo", equalTo("Outlander PHEV"))
                .body("[0].ano", equalTo(2023))
                .body("[0].economia", equalTo(carsList.get(0).getEconomia().floatValue()))
                .body("[9].marca", equalTo("Chevrolet"))
                .body("[9].modelo", equalTo("Bolt EV"))
                .body("[9].ano", equalTo(2023))
                .body("[9].economia", equalTo((carsList.get(9).getEconomia().floatValue())))
                .extract().body().as(new TypeRef<>() {});
        assertEquals(carsList, result);
    }


    @Test
    public void testListarEletricos() {
        given()
                .when()
                .get("/api/carros/eletricos")
                .then()
                .statusCode(200)
                .body("$", everyItem(hasEntry("tipo", "ELETRICO")));
    }
}