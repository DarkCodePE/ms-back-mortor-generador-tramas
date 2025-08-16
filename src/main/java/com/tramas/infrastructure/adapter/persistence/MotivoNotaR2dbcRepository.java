package com.tramas.infrastructure.adapter.persistence;

import com.tramas.infrastructure.adapter.persistence.entity.MotivoNotaEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MotivoNotaR2dbcRepository extends ReactiveCrudRepository<MotivoNotaEntity, String> {
    
    @Query("SELECT * FROM Catalogo.CodigoRubro WHERE CategoriaPrincipal = 'MOTIVO_NOTA' AND Activo = 1 AND Descripcion LIKE '%' + :descripcion + '%' ORDER BY Descripcion")
    Flux<MotivoNotaEntity> findByDescripcionContainingIgnoreCase(String descripcion);
    
    @Query("SELECT * FROM Catalogo.CodigoRubro WHERE CategoriaPrincipal = 'MOTIVO_NOTA' AND Activo = 1 ORDER BY Descripcion")
    Flux<MotivoNotaEntity> findAllActiveByOrderByDescripcion();
    
    @Query("SELECT * FROM Catalogo.CodigoRubro WHERE CategoriaPrincipal = 'MOTIVO_NOTA' AND Activo = 1")
    Flux<MotivoNotaEntity> findAllActive();
    
    @Query("SELECT * FROM Catalogo.CodigoRubro WHERE Codigo = :codigo AND CategoriaPrincipal = 'MOTIVO_NOTA'")
    Mono<MotivoNotaEntity> findByCodigo(String codigo);
}