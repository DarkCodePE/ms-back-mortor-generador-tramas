package com.tramas.infrastructure.adapter.persistence;

import com.tramas.infrastructure.adapter.persistence.entity.LoteEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface LoteR2dbcRepository extends ReactiveCrudRepository<LoteEntity, Long> {
    
    Mono<LoteEntity> findByNumeroLote(String numeroLote);
    
    Flux<LoteEntity> findByEstadoLote(String estadoLote);
    
    Flux<LoteEntity> findByTipoLote(String tipoLote);
    
    Flux<LoteEntity> findByUsuarioCreacion(String usuarioCreacion);
    
    @Query("SELECT COUNT(*) FROM Control.Lotes WHERE EstadoLote = :estadoLote")
    Mono<Long> countByEstadoLote(String estadoLote);
    
    Mono<Boolean> existsByNumeroLote(String numeroLote);
}