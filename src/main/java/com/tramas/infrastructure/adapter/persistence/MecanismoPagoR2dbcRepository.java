package com.tramas.infrastructure.adapter.persistence;

import com.tramas.infrastructure.adapter.persistence.entity.MecanismoPagoEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MecanismoPagoR2dbcRepository extends ReactiveCrudRepository<MecanismoPagoEntity, String> {
    
    @Query("SELECT * FROM Catalogo.CodigoRubro WHERE CategoriaPrincipal = 'MECANISMO_PAGO' AND Activo = 1 AND Descripcion LIKE '%' + :descripcion + '%' ORDER BY Descripcion")
    Flux<MecanismoPagoEntity> findByDescripcionContainingIgnoreCase(String descripcion);
    
    @Query("SELECT * FROM Catalogo.CodigoRubro WHERE CategoriaPrincipal = 'MECANISMO_PAGO' AND Activo = 1 ORDER BY Descripcion")
    Flux<MecanismoPagoEntity> findAllActiveByOrderByDescripcion();
    
    @Query("SELECT * FROM Catalogo.CodigoRubro WHERE CategoriaPrincipal = 'MECANISMO_PAGO' AND Activo = 1")
    Flux<MecanismoPagoEntity> findAllActive();
    
    @Query("SELECT * FROM Catalogo.CodigoRubro WHERE Codigo = :codigo AND CategoriaPrincipal = 'MECANISMO_PAGO'")
    Mono<MecanismoPagoEntity> findByCodigo(String codigo);
}