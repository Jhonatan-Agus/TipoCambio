package com.project.testunit.controller;

import com.project.testunit.model.TipoCambio;
import com.project.testunit.service.TipoCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tipoCambio")
public class TipoCambioController {

    @Autowired
    private TipoCambioService tipoCambioService;

    @PostMapping
    public Mono<TipoCambio> registrarTipoCambio(@RequestBody TipoCambio tipoCambio) {
        return tipoCambioService.registrarTipoCambio(tipoCambio);
    }

    @GetMapping("/{id}")
    public Mono<TipoCambio> obtenerTipoCambio(@PathVariable Long id) {
        return tipoCambioService.obtenerTipoCambio(id);
    }

    @GetMapping("/convertir")
    public Mono<Double> convertirMoneda(@RequestParam Double monto, @RequestParam Double tipoCambio) {
        return Mono.just(tipoCambioService.convertirMoneda(monto, tipoCambio));
    }
}
