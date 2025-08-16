package com.tramas.infrastructure.adapter.persistence;

import com.tramas.infrastructure.adapter.persistence.entity.TipoHospitalizacionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TipoHospitalizacionR2dbcRepository extends ReactiveCrudRepository<TipoHospitalizacionEntity, String> {
    
    Flux<TipoHospitalizacionEntity> findByEstadoTrue();
    
    Flux<TipoHospitalizacionEntity> findByDescripcionContainingIgnoreCaseAndEstadoTrue(String descripcion);
    
    Flux<TipoHospitalizacionEntity> findAllByOrderByDescripcion();
}