package com.tramas.domain.port;

import com.tramas.domain.model.EstadoRegistro;
import com.tramas.domain.model.TramaAtencion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TramaAtencionRepository {
    
    Mono<TramaAtencion> save(TramaAtencion tramaAtencion);
    
    Mono<TramaAtencion> findById(Long stagingId);
    
    Flux<TramaAtencion> findByLoteId(Long loteId);
    
    Flux<TramaAtencion> findByEstado(EstadoRegistro estado);
    
    Flux<TramaAtencion> findByLoteIdAndEstado(Long loteId, EstadoRegistro estado);
    
    Flux<TramaAtencion> findByCodigoAfiliado(String codigoAfiliado);
    
    Flux<TramaAtencion> findByCodigoIpress(String codigoIpress);
    
    Flux<TramaAtencion> findAll();
    
    Mono<Boolean> existsByLoteIdAndNumeroLinea(Long loteId, Integer numeroLinea);
    
    Mono<Void> deleteById(Long stagingId);
    
    Mono<Void> deleteByLoteId(Long loteId);
    
    Mono<Long> countByLoteId(Long loteId);
    
    Mono<Long> countByLoteIdAndEstado(Long loteId, EstadoRegistro estado);
}