package com.tramas.domain.port;

import com.tramas.domain.model.EstadoLote;
import com.tramas.domain.model.Lote;
import com.tramas.domain.model.TipoLote;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LoteRepository {
    
    Mono<Lote> save(Lote lote);
    
    Mono<Lote> findById(Long loteId);
    
    Mono<Lote> findByNumeroLote(String numeroLote);
    
    Flux<Lote> findByEstado(EstadoLote estado);
    
    Flux<Lote> findByTipoLote(TipoLote tipoLote);
    
    Flux<Lote> findByUsuarioCreacion(String usuario);
    
    Flux<Lote> findAll();
    
    Mono<Boolean> existsByNumeroLote(String numeroLote);
    
    Mono<Void> deleteById(Long loteId);
    
    Mono<Long> countByEstado(EstadoLote estado);
}