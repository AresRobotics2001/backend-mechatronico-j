package com.mecatronico.backendmecatronico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RobotService {

    private final ComunicacionSerial comunicador;

    @Autowired
    public RobotService(ComunicacionSerial comunicador) {
        this.comunicador = comunicador;
    }

    public void moverBrazo(int articulacion, int angulo) {
        String comando = "MOVER A" + articulacion + ":" + angulo;
        comunicador.enviarComando(comando);
        comunicador.recibirRespuesta();
    }

    public void girarBrazo(int articulacion, int angulo) {
        String comando = "GIRAR A" + articulacion + ":" + angulo;
        comunicador.enviarComando(comando);
        comunicador.recibirRespuesta();
    }

    public void girarBase(int angulo) {
        String comando = "BASE:" + angulo;
        comunicador.enviarComando(comando);
        comunicador.recibirRespuesta();
    }


    public String obtenerEstado() {
        comunicador.enviarComando("ESTADO");
        return comunicador.recibirRespuesta();
    }

    public String moverBrazoYRetornarMensaje(int articulacion, int angulo) {
        moverBrazo(articulacion, angulo); // método ya existente
        return "✅ Brazo movido a articulación " + articulacion + " con ángulo " + angulo + "°";
    }

    public String girarBaseYRetornarMensaje(int angulo) {
        girarBase(angulo); // llamamos al método existente que hace el trabajo real
        return "🔄 Base girada a " + angulo + "°";
    }


    public String obtenerSensor() {
        comunicador.enviarComando("SENSOR:TEMP");
        int temperatura = 25; // Simulado
        String resultado = "La temperatura es " + temperatura + "°C";
        System.out.println(resultado);
        return resultado;
    }
}
