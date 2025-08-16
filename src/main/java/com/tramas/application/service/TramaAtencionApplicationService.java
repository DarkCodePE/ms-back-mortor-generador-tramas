package com.tramas.application.service;

import com.tramas.application.dto.CreateTramaAtencionRequest;
import com.tramas.application.dto.TramaAtencionDto;
import com.tramas.application.dto.mapper.TramaAtencionDtoMapper;
import com.tramas.domain.model.EstadoRegistro;
import com.tramas.domain.service.TramaAtencionService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class TramaAtencionApplicationService {
    
    private final TramaAtencionService tramaAtencionService;
    
    public TramaAtencionApplicationService(TramaAtencionService tramaAtencionService) {
        this.tramaAtencionService = Objects.requireNonNull(tramaAtencionService, 
                "TramaAtencionService cannot be null");
    }
    
    public Mono<TramaAtencionDto> crearTramaAtencion(CreateTramaAtencionRequest request) {
        Objects.requireNonNull(request, "CreateTramaAtencionRequest cannot be null");
        
        return tramaAtencionService.crearTramaAtencion(request.loteId(), request.numeroLinea())
                .map(TramaAtencionDtoMapper::toDto);
    }
    
    public Mono<TramaAtencionDto> actualizarTramaAtencion(TramaAtencionDto tramaDto) {
        Objects.requireNonNull(tramaDto, "TramaAtencionDto cannot be null");
        
        var trama = TramaAtencionDtoMapper.toDomain(tramaDto);
        
        return tramaAtencionService.actualizarTramaAtencion(trama)
                .map(TramaAtencionDtoMapper::toDto);
    }
    
    public Mono<TramaAtencionDto> obtenerTramaPorId(Long stagingId) {
        Objects.requireNonNull(stagingId, "Staging ID cannot be null");
        
        return tramaAtencionService.obtenerPorId(stagingId)
                .map(TramaAtencionDtoMapper::toDto);
    }
    
    public Flux<TramaAtencionDto> listarTramasPorLote(Long loteId) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        
        return tramaAtencionService.listarPorLote(loteId)
                .map(TramaAtencionDtoMapper::toDto);
    }
    
    public Flux<TramaAtencionDto> listarTramasPorEstado(String estado) {
        Objects.requireNonNull(estado, "Estado cannot be null");
        
        EstadoRegistro estadoRegistro = EstadoRegistro.fromString(estado);
        
        return tramaAtencionService.listarPorEstado(estadoRegistro)
                .map(TramaAtencionDtoMapper::toDto);
    }
    
    public Flux<TramaAtencionDto> listarTramasPorLoteYEstado(Long loteId, String estado) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        Objects.requireNonNull(estado, "Estado cannot be null");
        
        EstadoRegistro estadoRegistro = EstadoRegistro.fromString(estado);
        
        return tramaAtencionService.listarPorLoteYEstado(loteId, estadoRegistro)
                .map(TramaAtencionDtoMapper::toDto);
    }
    
    public Mono<TramaAtencionDto> validarTrama(Long stagingId, String observaciones, String errores) {
        Objects.requireNonNull(stagingId, "Staging ID cannot be null");
        
        return tramaAtencionService.validarTramaAtencion(stagingId, observaciones, errores)
                .map(TramaAtencionDtoMapper::toDto);
    }
    
    public Mono<TramaAtencionDto> cambiarEstadoTrama(Long stagingId, String nuevoEstado) {
        Objects.requireNonNull(stagingId, "Staging ID cannot be null");
        Objects.requireNonNull(nuevoEstado, "Nuevo estado cannot be null");
        
        EstadoRegistro estadoRegistro = EstadoRegistro.fromString(nuevoEstado);
        
        return tramaAtencionService.cambiarEstado(stagingId, estadoRegistro)
                .map(TramaAtencionDtoMapper::toDto);
    }
    
    public Mono<Long> contarTramasPorLote(Long loteId) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        
        return tramaAtencionService.contarPorLote(loteId);
    }
    
    public Mono<Long> contarTramasPorLoteYEstado(Long loteId, String estado) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        Objects.requireNonNull(estado, "Estado cannot be null");
        
        EstadoRegistro estadoRegistro = EstadoRegistro.fromString(estado);
        
        return tramaAtencionService.contarPorLoteYEstado(loteId, estadoRegistro);
    }
    
    public Mono<Void> eliminarTrama(Long stagingId) {
        Objects.requireNonNull(stagingId, "Staging ID cannot be null");
        
        return tramaAtencionService.eliminarTrama(stagingId);
    }
    
    public Mono<Void> eliminarTramasPorLote(Long loteId) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        
        return tramaAtencionService.eliminarPorLote(loteId);
    }
}