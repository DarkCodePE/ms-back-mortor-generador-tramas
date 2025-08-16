package com.tramas.domain.service;

import com.tramas.domain.model.EstadoLote;
import com.tramas.domain.model.Lote;
import com.tramas.domain.model.TipoLote;
import com.tramas.domain.port.LoteRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class LoteService {
    
    private final LoteRepository loteRepository;
    
    public LoteService(LoteRepository loteRepository) {
        this.loteRepository = Objects.requireNonNull(loteRepository, "LoteRepository cannot be null");
    }
    
    public Mono<Lote> crearLote(String numeroLote, String usuarioCreacion, TipoLote tipoLote) {
        Objects.requireNonNull(numeroLote, "Numero lote cannot be null");
        Objects.requireNonNull(usuarioCreacion, "Usuario creacion cannot be null");
        Objects.requireNonNull(tipoLote, "Tipo lote cannot be null");
        
        return loteRepository.existsByNumeroLote(numeroLote)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalArgumentException("El lote ya existe: " + numeroLote));
                    }
                    
                    Lote nuevoLote = Lote.create(numeroLote, usuarioCreacion, tipoLote);
                    return loteRepository.save(nuevoLote);
                });
    }
    
    public Mono<Lote> actualizarEstadoLote(Long loteId, EstadoLote nuevoEstado) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        Objects.requireNonNull(nuevoEstado, "Nuevo estado cannot be null");
        
        return loteRepository.findById(loteId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Lote no encontrado: " + loteId)))
                .map(lote -> lote.withEstado(nuevoEstado))
                .flatMap(loteRepository::save);
    }
    
    public Mono<Lote> actualizarProgreso(Long loteId, int procesados, int conError, int validos) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        
        return loteRepository.findById(loteId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Lote no encontrado: " + loteId)))
                .map(lote -> lote.withProgreso(procesados, conError, validos))
                .flatMap(loteRepository::save);
    }
    
    public Mono<Lote> obtenerPorId(Long loteId) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        
        return loteRepository.findById(loteId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Lote no encontrado: " + loteId)));
    }
    
    public Mono<Lote> obtenerPorNumeroLote(String numeroLote) {
        Objects.requireNonNull(numeroLote, "Numero lote cannot be null");
        
        return loteRepository.findByNumeroLote(numeroLote)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Lote no encontrado: " + numeroLote)));
    }
    
    public Flux<Lote> listarPorEstado(EstadoLote estado) {
        Objects.requireNonNull(estado, "Estado cannot be null");
        
        return loteRepository.findByEstado(estado);
    }
    
    public Flux<Lote> listarPorTipo(TipoLote tipo) {
        Objects.requireNonNull(tipo, "Tipo cannot be null");
        
        return loteRepository.findByTipoLote(tipo);
    }
    
    public Flux<Lote> listarTodos() {
        return loteRepository.findAll();
    }
    
    public Mono<Boolean> existeLote(String numeroLote) {
        Objects.requireNonNull(numeroLote, "Numero lote cannot be null");
        
        return loteRepository.existsByNumeroLote(numeroLote);
    }
    
    public Mono<Void> eliminarLote(Long loteId) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        
        return loteRepository.findById(loteId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Lote no encontrado: " + loteId)))
                .flatMap(lote -> {
                    if (lote.estadoLote() == EstadoLote.EN_PROCESO) {
                        return Mono.error(new IllegalStateException("No se puede eliminar un lote en proceso"));
                    }
                    return loteRepository.deleteById(loteId);
                });
    }
}