package com.project.testunit.service;

import com.project.testunit.model.TipoCambio;
import com.project.testunit.repository.TipoCambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TipoCambioService {

    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    public Mono<TipoCambio> registrarTipoCambio(TipoCambio tipoCambio) {
        return tipoCambioRepository.save(tipoCambio);
    }

    public Mono<TipoCambio> obtenerTipoCambio(Long id) {
        return tipoCambioRepository.findById(id);
    }

    public Double convertirMoneda(Double monto, Double tipoCambio) {
        return monto * tipoCambio;
    }
}
