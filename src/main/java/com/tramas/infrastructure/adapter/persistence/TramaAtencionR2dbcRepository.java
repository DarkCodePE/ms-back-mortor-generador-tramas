package com.tramas.infrastructure.adapter.persistence;

import com.tramas.infrastructure.adapter.persistence.entity.TramaAtencionEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TramaAtencionR2dbcRepository extends ReactiveCrudRepository<TramaAtencionEntity, Long> {
    
    Flux<TramaAtencionEntity> findByLoteId(Long loteId);
    
    Flux<TramaAtencionEntity> findByEstadoRegistro(String estadoRegistro);
    
    Flux<TramaAtencionEntity> findByLoteIdAndEstadoRegistro(Long loteId, String estadoRegistro);
    
    Flux<TramaAtencionEntity> findByCodigoAfiliado(String codigoAfiliado);
    
    Flux<TramaAtencionEntity> findByCodigoIpress(String codigoIpress);
    
    @Query("SELECT COUNT(*) FROM Staging.TramaAtencion WHERE LoteId = :loteId")
    Mono<Long> countByLoteId(Long loteId);
    
    @Query("SELECT COUNT(*) FROM Staging.TramaAtencion WHERE LoteId = :loteId AND EstadoRegistro = :estadoRegistro")
    Mono<Long> countByLoteIdAndEstadoRegistro(Long loteId, String estadoRegistro);
    
    Mono<Boolean> existsByLoteIdAndNumeroLinea(Long loteId, Integer numeroLinea);
    
    Mono<Void> deleteByLoteId(Long loteId);
}