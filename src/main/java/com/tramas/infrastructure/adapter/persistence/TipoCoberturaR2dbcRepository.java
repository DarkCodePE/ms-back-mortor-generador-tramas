package com.tramas.infrastructure.adapter.persistence;

import com.tramas.infrastructure.adapter.persistence.entity.TipoCoberturaEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TipoCoberturaR2dbcRepository extends ReactiveCrudRepository<TipoCoberturaEntity, String> {
    
    Flux<TipoCoberturaEntity> findByEstadoTrue();
    
    Flux<TipoCoberturaEntity> findByDescripcionContainingIgnoreCaseAndEstadoTrue(String descripcion);
    
    Flux<TipoCoberturaEntity> findAllByOrderByDescripcion();
}