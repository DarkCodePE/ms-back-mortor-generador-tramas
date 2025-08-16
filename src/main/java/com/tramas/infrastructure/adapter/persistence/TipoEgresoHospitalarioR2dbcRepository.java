package com.tramas.infrastructure.adapter.persistence;

import com.tramas.infrastructure.adapter.persistence.entity.TipoEgresoHospitalarioEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TipoEgresoHospitalarioR2dbcRepository extends ReactiveCrudRepository<TipoEgresoHospitalarioEntity, String> {
    
    Flux<TipoEgresoHospitalarioEntity> findByEstadoTrue();
    
    Flux<TipoEgresoHospitalarioEntity> findByDescripcionContainingIgnoreCaseAndEstadoTrue(String descripcion);
    
    Flux<TipoEgresoHospitalarioEntity> findAllByOrderByDescripcion();
}