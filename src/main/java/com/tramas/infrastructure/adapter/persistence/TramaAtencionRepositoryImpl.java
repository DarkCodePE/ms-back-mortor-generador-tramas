package com.tramas.infrastructure.adapter.persistence;

import com.tramas.domain.model.EstadoRegistro;
import com.tramas.domain.model.TramaAtencion;
import com.tramas.domain.port.TramaAtencionRepository;
import com.tramas.infrastructure.adapter.persistence.mapper.TramaAtencionMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TramaAtencionRepositoryImpl implements TramaAtencionRepository {
    
    private final TramaAtencionR2dbcRepository r2dbcRepository;
    
    public TramaAtencionRepositoryImpl(TramaAtencionR2dbcRepository r2dbcRepository) {
        this.r2dbcRepository = r2dbcRepository;
    }
    
    @Override
    public Mono<TramaAtencion> save(TramaAtencion tramaAtencion) {
        return Mono.fromCallable(() -> TramaAtencionMapper.toEntity(tramaAtencion))
                .flatMap(r2dbcRepository::save)
                .map(TramaAtencionMapper::toDomain);
    }
    
    @Override
    public Mono<TramaAtencion> findById(Long stagingId) {
        return r2dbcRepository.findById(stagingId)
                .map(TramaAtencionMapper::toDomain);
    }
    
    @Override
    public Flux<TramaAtencion> findByLoteId(Long loteId) {
        return r2dbcRepository.findByLoteId(loteId)
                .map(TramaAtencionMapper::toDomain);
    }
    
    @Override
    public Flux<TramaAtencion> findByEstado(EstadoRegistro estado) {
        return r2dbcRepository.findByEstadoRegistro(estado.getValor())
                .map(TramaAtencionMapper::toDomain);
    }
    
    @Override
    public Flux<TramaAtencion> findByLoteIdAndEstado(Long loteId, EstadoRegistro estado) {
        return r2dbcRepository.findByLoteIdAndEstadoRegistro(loteId, estado.getValor())
                .map(TramaAtencionMapper::toDomain);
    }
    
    @Override
    public Flux<TramaAtencion> findByCodigoAfiliado(String codigoAfiliado) {
        return r2dbcRepository.findByCodigoAfiliado(codigoAfiliado)
                .map(TramaAtencionMapper::toDomain);
    }
    
    @Override
    public Flux<TramaAtencion> findByCodigoIpress(String codigoIpress) {
        return r2dbcRepository.findByCodigoIpress(codigoIpress)
                .map(TramaAtencionMapper::toDomain);
    }
    
    @Override
    public Flux<TramaAtencion> findAll() {
        return r2dbcRepository.findAll()
                .map(TramaAtencionMapper::toDomain);
    }
    
    @Override
    public Mono<Boolean> existsByLoteIdAndNumeroLinea(Long loteId, Integer numeroLinea) {
        return r2dbcRepository.existsByLoteIdAndNumeroLinea(loteId, numeroLinea);
    }
    
    @Override
    public Mono<Void> deleteById(Long stagingId) {
        return r2dbcRepository.deleteById(stagingId);
    }
    
    @Override
    public Mono<Void> deleteByLoteId(Long loteId) {
        return r2dbcRepository.deleteByLoteId(loteId);
    }
    
    @Override
    public Mono<Long> countByLoteId(Long loteId) {
        return r2dbcRepository.countByLoteId(loteId);
    }
    
    @Override
    public Mono<Long> countByLoteIdAndEstado(Long loteId, EstadoRegistro estado) {
        return r2dbcRepository.countByLoteIdAndEstadoRegistro(loteId, estado.getValor());
    }
}