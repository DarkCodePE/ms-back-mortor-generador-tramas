package com.tramas.infrastructure.adapter.persistence;

import com.tramas.infrastructure.adapter.persistence.entity.SubtipoCoberturaEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SubtipoCoberturaR2dbcRepository extends ReactiveCrudRepository<SubtipoCoberturaEntity, String> {
    
    Flux<SubtipoCoberturaEntity> findByIdTipoCoberturaAndEstadoTrue(String idTipoCobertura);
    
    Flux<SubtipoCoberturaEntity> findByEstadoTrue();
    
    Flux<SubtipoCoberturaEntity> findByDescripcionContainingIgnoreCaseAndEstadoTrue(String descripcion);
    
    Flux<SubtipoCoberturaEntity> findAllByOrderByDescripcion();
}