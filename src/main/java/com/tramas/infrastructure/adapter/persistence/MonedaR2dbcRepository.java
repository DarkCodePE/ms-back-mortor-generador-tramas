package com.tramas.infrastructure.adapter.persistence;

import com.tramas.infrastructure.adapter.persistence.entity.MonedaEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MonedaR2dbcRepository extends ReactiveCrudRepository<MonedaEntity, String> {
    
    @Query("SELECT * FROM Catalogo.CodigoRubro WHERE CategoriaPrincipal = 'MONEDA' AND Activo = 1 AND Descripcion LIKE '%' + :descripcion + '%' ORDER BY Descripcion")
    Flux<MonedaEntity> findByDescripcionContainingIgnoreCase(String descripcion);
    
    @Query("SELECT * FROM Catalogo.CodigoRubro WHERE CategoriaPrincipal = 'MONEDA' AND Activo = 1 ORDER BY Descripcion")
    Flux<MonedaEntity> findAllActiveByOrderByDescripcion();
    
    @Query("SELECT * FROM Catalogo.CodigoRubro WHERE CategoriaPrincipal = 'MONEDA' AND Activo = 1")
    Flux<MonedaEntity> findAllActive();
    
    @Query("SELECT * FROM Catalogo.CodigoRubro WHERE Codigo = :codigo AND CategoriaPrincipal = 'MONEDA'")
    Mono<MonedaEntity> findByCodigo(String codigo);
}