package com.tramas.infrastructure.adapter.persistence;

import com.tramas.domain.model.EstadoLote;
import com.tramas.domain.model.Lote;
import com.tramas.domain.model.TipoLote;
import com.tramas.domain.port.LoteRepository;
import com.tramas.infrastructure.adapter.persistence.mapper.LoteMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class LoteRepositoryImpl implements LoteRepository {
    
    private final LoteR2dbcRepository r2dbcRepository;
    
    public LoteRepositoryImpl(LoteR2dbcRepository r2dbcRepository) {
        this.r2dbcRepository = r2dbcRepository;
    }
    
    @Override
    public Mono<Lote> save(Lote lote) {
        return Mono.fromCallable(() -> LoteMapper.toEntity(lote))
                .flatMap(r2dbcRepository::save)
                .map(LoteMapper::toDomain);
    }
    
    @Override
    public Mono<Lote> findById(Long loteId) {
        return r2dbcRepository.findById(loteId)
                .map(LoteMapper::toDomain);
    }
    
    @Override
    public Mono<Lote> findByNumeroLote(String numeroLote) {
        return r2dbcRepository.findByNumeroLote(numeroLote)
                .map(LoteMapper::toDomain);
    }
    
    @Override
    public Flux<Lote> findByEstado(EstadoLote estado) {
        return r2dbcRepository.findByEstadoLote(estado.getValor())
                .map(LoteMapper::toDomain);
    }
    
    @Override
    public Flux<Lote> findByTipoLote(TipoLote tipoLote) {
        return r2dbcRepository.findByTipoLote(tipoLote.getValor())
                .map(LoteMapper::toDomain);
    }
    
    @Override
    public Flux<Lote> findByUsuarioCreacion(String usuario) {
        return r2dbcRepository.findByUsuarioCreacion(usuario)
                .map(LoteMapper::toDomain);
    }
    
    @Override
    public Flux<Lote> findAll() {
        return r2dbcRepository.findAll()
                .map(LoteMapper::toDomain);
    }
    
    @Override
    public Mono<Boolean> existsByNumeroLote(String numeroLote) {
        return r2dbcRepository.existsByNumeroLote(numeroLote);
    }
    
    @Override
    public Mono<Void> deleteById(Long loteId) {
        return r2dbcRepository.deleteById(loteId);
    }
    
    @Override
    public Mono<Long> countByEstado(EstadoLote estado) {
        return r2dbcRepository.countByEstadoLote(estado.getValor());
    }
}