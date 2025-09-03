package com.mecatronico.backendmecatronico.service;

import org.springframework.stereotype.Component;

@Component
public class ComunicacionSerial {

    public boolean verificarConexion() {
        for (int intentos = 0; intentos < 3; intentos++) {
            System.out.println("🔍 Buscando señal de comunicación... (Intento " + (intentos + 1) + "/3)");
            try {
                Thread.sleep(10000); // Simula espera
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt(); // buena práctica
            }
        }

        System.out.println("✅ Señal recibida. Comunicación establecida.");
        return true;
    }

    public void enviarComando(String comando) {
        System.out.println("📤 Enviando comando: " + comando);
        // Aquí iría la lógica para enviar por puerto serial
    }

    public String recibirRespuesta() {
        // Simula recepción de respuesta
        String respuesta = "📥 Respuesta recibida del robot.";
        System.out.println(respuesta);
        return respuesta;
    }
}
