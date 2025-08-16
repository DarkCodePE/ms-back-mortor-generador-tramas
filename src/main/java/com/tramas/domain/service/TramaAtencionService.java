package com.tramas.domain.service;

import com.tramas.domain.model.EstadoRegistro;
import com.tramas.domain.model.TramaAtencion;
import com.tramas.domain.port.LoteRepository;
import com.tramas.domain.port.TramaAtencionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class TramaAtencionService {
    
    private final TramaAtencionRepository tramaAtencionRepository;
    private final LoteRepository loteRepository;
    
    public TramaAtencionService(TramaAtencionRepository tramaAtencionRepository, 
                               LoteRepository loteRepository) {
        this.tramaAtencionRepository = Objects.requireNonNull(tramaAtencionRepository, 
                "TramaAtencionRepository cannot be null");
        this.loteRepository = Objects.requireNonNull(loteRepository, 
                "LoteRepository cannot be null");
    }
    
    public Mono<TramaAtencion> crearTramaAtencion(Long loteId, Integer numeroLinea) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        Objects.requireNonNull(numeroLinea, "Numero linea cannot be null");
        
        return loteRepository.findById(loteId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Lote no encontrado: " + loteId)))
                .then(tramaAtencionRepository.existsByLoteIdAndNumeroLinea(loteId, numeroLinea))
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalArgumentException(
                                "Ya existe una trama con el lote " + loteId + " y línea " + numeroLinea));
                    }
                    
                    TramaAtencion nuevaTrama = TramaAtencion.create(loteId, numeroLinea);
                    return tramaAtencionRepository.save(nuevaTrama);
                });
    }
    
    public Mono<TramaAtencion> actualizarTramaAtencion(TramaAtencion tramaAtencion) {
        Objects.requireNonNull(tramaAtencion, "TramaAtencion cannot be null");
        Objects.requireNonNull(tramaAtencion.stagingId(), "Staging ID cannot be null");
        
        return tramaAtencionRepository.findById(tramaAtencion.stagingId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException(
                        "Trama no encontrada: " + tramaAtencion.stagingId())))
                .then(tramaAtencionRepository.save(tramaAtencion));
    }
    
    public Mono<TramaAtencion> validarTramaAtencion(Long stagingId, String observaciones, String errores) {
        Objects.requireNonNull(stagingId, "Staging ID cannot be null");
        
        EstadoRegistro nuevoEstado = (errores != null && !errores.trim().isEmpty()) 
                ? EstadoRegistro.ERROR 
                : EstadoRegistro.VALIDADO;
        
        return tramaAtencionRepository.findById(stagingId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Trama no encontrada: " + stagingId)))
                .map(trama -> trama.withValidacion(observaciones, errores).withEstado(nuevoEstado))
                .flatMap(tramaAtencionRepository::save);
    }
    
    public Mono<TramaAtencion> cambiarEstado(Long stagingId, EstadoRegistro nuevoEstado) {
        Objects.requireNonNull(stagingId, "Staging ID cannot be null");
        Objects.requireNonNull(nuevoEstado, "Nuevo estado cannot be null");
        
        return tramaAtencionRepository.findById(stagingId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Trama no encontrada: " + stagingId)))
                .map(trama -> trama.withEstado(nuevoEstado))
                .flatMap(tramaAtencionRepository::save);
    }
    
    public Mono<TramaAtencion> obtenerPorId(Long stagingId) {
        Objects.requireNonNull(stagingId, "Staging ID cannot be null");
        
        return tramaAtencionRepository.findById(stagingId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Trama no encontrada: " + stagingId)));
    }
    
    public Flux<TramaAtencion> listarPorLote(Long loteId) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        
        return tramaAtencionRepository.findByLoteId(loteId);
    }
    
    public Flux<TramaAtencion> listarPorEstado(EstadoRegistro estado) {
        Objects.requireNonNull(estado, "Estado cannot be null");
        
        return tramaAtencionRepository.findByEstado(estado);
    }
    
    public Flux<TramaAtencion> listarPorLoteYEstado(Long loteId, EstadoRegistro estado) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        Objects.requireNonNull(estado, "Estado cannot be null");
        
        return tramaAtencionRepository.findByLoteIdAndEstado(loteId, estado);
    }
    
    public Mono<Long> contarPorLote(Long loteId) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        
        return tramaAtencionRepository.countByLoteId(loteId);
    }
    
    public Mono<Long> contarPorLoteYEstado(Long loteId, EstadoRegistro estado) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        Objects.requireNonNull(estado, "Estado cannot be null");
        
        return tramaAtencionRepository.countByLoteIdAndEstado(loteId, estado);
    }
    
    public Mono<Void> eliminarTrama(Long stagingId) {
        Objects.requireNonNull(stagingId, "Staging ID cannot be null");
        
        return tramaAtencionRepository.findById(stagingId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Trama no encontrada: " + stagingId)))
                .flatMap(trama -> {
                    if (trama.estadoRegistro() == EstadoRegistro.PROCESADO || 
                        trama.estadoRegistro() == EstadoRegistro.INTEGRADO) {
                        return Mono.error(new IllegalStateException(
                                "No se puede eliminar una trama procesada o integrada"));
                    }
                    return tramaAtencionRepository.deleteById(stagingId);
                });
    }
    
    public Mono<Void> eliminarPorLote(Long loteId) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        
        return tramaAtencionRepository.deleteByLoteId(loteId);
    }
}