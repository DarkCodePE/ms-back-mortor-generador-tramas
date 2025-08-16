package com.tramas.infrastructure.adapter.persistence;

import com.tramas.infrastructure.adapter.persistence.entity.TipoDocAutorizacionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TipoDocAutorizacionR2dbcRepository extends ReactiveCrudRepository<TipoDocAutorizacionEntity, String> {
    
    Flux<TipoDocAutorizacionEntity> findByEstadoTrue();
    
    Flux<TipoDocAutorizacionEntity> findByDescripcionContainingIgnoreCaseAndEstadoTrue(String descripcion);
    
    Flux<TipoDocAutorizacionEntity> findAllByOrderByDescripcion();
}