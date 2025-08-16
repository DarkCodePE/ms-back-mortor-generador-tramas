package com.tramas.infrastructure.adapter.persistence;

import com.tramas.infrastructure.adapter.persistence.entity.TipoPersonalResponsableEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TipoPersonalResponsableR2dbcRepository extends ReactiveCrudRepository<TipoPersonalResponsableEntity, String> {
    
    Flux<TipoPersonalResponsableEntity> findByEstadoTrue();
    
    Flux<TipoPersonalResponsableEntity> findByDescripcionContainingIgnoreCaseAndEstadoTrue(String descripcion);
    
    Flux<TipoPersonalResponsableEntity> findAllByOrderByDescripcion();
}