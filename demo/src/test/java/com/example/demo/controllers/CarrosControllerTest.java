package com.example.demo.controllers;

import com.example.demo.domainmodel.Carros;
import com.example.demo.domainmodel.TipoCarro;
import com.example.demo.service.CarrosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarrosControllerTest {

    @Mock
    private CarrosService carrosService;

    @InjectMocks
    private CarrosController carrosController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_QuandoCarroExiste_RetornaCarro() {
        // Arrange
        Long carId = 1L;
        Carros mockCar = new Carros();
        mockCar.setId(carId);
        when(carrosService.getById(carId)).thenReturn(mockCar);

        // Act
        ResponseEntity<Carros> response = carrosController.findById(carId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(carId, response.getBody().getId());
        verify(carrosService, times(1)).getById(carId);
    }

    @Test
    void top10PorPotencia_DeveFazerTop10DePotenciaOrdenadosDecrescentemente() {
        // Arrange
        Carros car1 = new Carros();
        car1.setPotencia(200);
        Carros car2 = new Carros();
        car2.setPotencia(300);

        List<Carros> mockCars = Arrays.asList(car2, car1);
        when(carrosService.getTop10ByPotencia()).thenReturn(mockCars);

        // Act
        ResponseEntity<List<Carros>> response = carrosController.top10PorPotencia();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals(300, response.getBody().get(0).getPotencia());
        assertEquals(200, response.getBody().get(1).getPotencia());
        verify(carrosService, times(1)).getTop10ByPotencia();
    }

    @Test
    void top10PorEconomia_DeveFazerTop10DeEconomiaOrdenadosDecrescentemente() {
        // Arrange
        Carros car1 = new Carros();
        car1.setEconomia(15.5);
        Carros car2 = new Carros();
        car2.setEconomia(20.0);

        List<Carros> mockCars = Arrays.asList(car2, car1);
        when(carrosService.getTop10ByEconomia()).thenReturn(mockCars);

        // Act
        ResponseEntity<List<Carros>> response = carrosController.top10PorEconomia();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals(20.0, response.getBody().get(0).getEconomia());
        assertEquals(15.5, response.getBody().get(1).getEconomia());
        verify(carrosService, times(1)).getTop10ByEconomia();
    }

    @Test
    void listarEletricos_DeveListarSomenteOsCarrosEletricos() {
        // Arrange
        Carros electricCar = new Carros();
        electricCar.setTipo(TipoCarro.ELETRICO);

        List<Carros> mockCars = Collections.singletonList(electricCar);
        when(carrosService.getByTipo(TipoCarro.ELETRICO)).thenReturn(mockCars);

        // Act
        ResponseEntity<List<Carros>> response = carrosController.listarEletricos();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(TipoCarro.ELETRICO, response.getBody().get(0).getTipo());
        verify(carrosService, times(1)).getByTipo(TipoCarro.ELETRICO);
    }

}