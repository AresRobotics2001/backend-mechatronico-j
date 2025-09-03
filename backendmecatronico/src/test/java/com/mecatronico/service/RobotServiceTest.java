package com.mecatronico.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mecatronico.backendmecatronico.service.ComunicacionSerial;
import com.mecatronico.backendmecatronico.service.RobotService;

public class RobotServiceTest {

    private RobotService robotService;
    private ComunicacionSerial comunicadorMock;

    @BeforeEach
    void setUp() {
        comunicadorMock = mock(ComunicacionSerial.class); // mock de la interfaz
        robotService = new RobotService(comunicadorMock); // inyección del mock
    }

    @Test
    void testMoverBrazo() {
        robotService.moverBrazo(2, 45);

        verify(comunicadorMock).enviarComando("MOVER A2:45");
        verify(comunicadorMock).recibirRespuesta();
    }

    @Test
    void testGirarBrazo() {
        robotService.girarBrazo(3, 90);

        verify(comunicadorMock).enviarComando("GIRAR A3:90");
        verify(comunicadorMock).recibirRespuesta();
    }

    @Test
    void testGirarBase() {
        robotService.girarBase(180);

        verify(comunicadorMock).enviarComando("BASE:180");
        verify(comunicadorMock).recibirRespuesta();
    }

    @Test
    void testObtenerEstado() {
        when(comunicadorMock.recibirRespuesta()).thenReturn("Estado: OK");

        String estado = robotService.obtenerEstado();

        verify(comunicadorMock).enviarComando("ESTADO");
        assertEquals("Estado: OK", estado);
    }

    @Test
    void testMoverBrazoYRetornarMensaje() {
        String resultado = robotService.moverBrazoYRetornarMensaje(1, 90);

        verify(comunicadorMock).enviarComando("MOVER A1:90");
        verify(comunicadorMock).recibirRespuesta();
        assertEquals("✅ Brazo movido a articulación 1 con ángulo 90°", resultado);
    }

    @Test
    void testGirarBaseYRetornarMensaje() {
        String resultado = robotService.girarBaseYRetornarMensaje(60);

        verify(comunicadorMock).enviarComando("BASE:60");
        verify(comunicadorMock).recibirRespuesta();
        assertEquals("🔄 Base girada a 60°", resultado);
    }

    @Test
    void testObtenerSensor() {
        // Este método no usa recibirRespuesta() directamente, pero simula una salida
        String resultado = robotService.obtenerSensor();

        verify(comunicadorMock).enviarComando("SENSOR:TEMP");
        assertEquals("La temperatura es 28°C", resultado);
    }
}
