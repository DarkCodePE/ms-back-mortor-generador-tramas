package com.tramas.infrastructure.adapter.persistence;

import com.tramas.infrastructure.adapter.persistence.entity.ClasificacionServicioEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ClasificacionServicioR2dbcRepository extends ReactiveCrudRepository<ClasificacionServicioEntity, String> {
    
    @Query("SELECT * FROM Catalogo.CodigoRubro WHERE CategoriaPrincipal = 'CLASIFICACION_SERVICIO' AND Activo = 1 AND Descripcion LIKE '%' + :descripcion + '%' ORDER BY Descripcion")
    Flux<ClasificacionServicioEntity> findByDescripcionContainingIgnoreCase(String descripcion);
    
    @Query("SELECT * FROM Catalogo.CodigoRubro WHERE CategoriaPrincipal = 'CLASIFICACION_SERVICIO' AND Activo = 1 ORDER BY Descripcion")
    Flux<ClasificacionServicioEntity> findAllActiveByOrderByDescripcion();
    
    @Query("SELECT * FROM Catalogo.CodigoRubro WHERE CategoriaPrincipal = 'CLASIFICACION_SERVICIO' AND Activo = 1")
    Flux<ClasificacionServicioEntity> findAllActive();
    
    @Query("SELECT * FROM Catalogo.CodigoRubro WHERE Codigo = :codigo AND CategoriaPrincipal = 'CLASIFICACION_SERVICIO'")
    Mono<ClasificacionServicioEntity> findByCodigo(String codigo);
}