package com.mecatronico.backendmecatronico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mecatronico.backendmecatronico.service.RobotService;

@Controller
@RequestMapping("/robot")
public class RobotController {

    private final RobotService robotService;

    @Autowired
    public RobotController(RobotService robotService) {
        this.robotService = robotService;
    }

    @PostMapping("/moverBrazo")
    public String moverBrazo(@RequestParam int articulacion, @RequestParam int angulo, Model model) {
        String resultado = robotService.moverBrazoYRetornarMensaje(articulacion, angulo);
        model.addAttribute("resultado", resultado);
        return "inicio";
    }

    @PostMapping("/girarBase")
    public String girarBase(@RequestParam int angulo, Model model) {
        String resultado = robotService.girarBaseYRetornarMensaje(angulo);
        model.addAttribute("resultado", resultado);
        return "inicio";
    }

    @GetMapping("/estado")
    public String obtenerEstado(Model model) {
        model.addAttribute("estado", "📋 Estado del robot: " + robotService.obtenerEstado());
        return "inicio";
    }

    @GetMapping("/sensor")
    public String obtenerSensor(Model model) {
        model.addAttribute("sensor", "🌡️ Sensor: " + robotService.obtenerSensor());
        return "inicio";
    }
}
