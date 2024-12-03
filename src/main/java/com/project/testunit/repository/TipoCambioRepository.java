package com.project.testunit.repository;

import com.project.testunit.model.TipoCambio;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TipoCambioRepository extends ReactiveCrudRepository<TipoCambio, Long> {
}
