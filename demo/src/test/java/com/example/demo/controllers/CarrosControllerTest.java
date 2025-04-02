package com.example.demo.controllers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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
        given()
                .when()
                .get("/api/carros/potencia")
                .then()
                .statusCode(200)
                .body("$", hasSize(lessThanOrEqualTo(10)));
    }


    @Test
    public void testTop10PorEconomia() {
        given()
                .when()
                .get("/api/carros/economia")
                .then()
                .statusCode(200)
                .body("$", hasSize(lessThanOrEqualTo(10)));
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