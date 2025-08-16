package com.tramas.application.service;

import com.tramas.application.dto.CreateLoteRequest;
import com.tramas.application.dto.LoteDto;
import com.tramas.application.dto.UpdateEstadoLoteRequest;
import com.tramas.application.dto.mapper.LoteDtoMapper;
import com.tramas.domain.model.EstadoLote;
import com.tramas.domain.model.TipoLote;
import com.tramas.domain.service.LoteService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class LoteApplicationService {
    
    private final LoteService loteService;
    
    public LoteApplicationService(LoteService loteService) {
        this.loteService = Objects.requireNonNull(loteService, "LoteService cannot be null");
    }
    
    public Mono<LoteDto> crearLote(CreateLoteRequest request) {
        Objects.requireNonNull(request, "CreateLoteRequest cannot be null");
        
        TipoLote tipoLote = LoteDtoMapper.parseTipoLote(request.tipoLote());
        
        return loteService.crearLote(request.numeroLote(), request.usuarioCreacion(), tipoLote)
                .map(LoteDtoMapper::toDto);
    }
    
    public Mono<LoteDto> actualizarEstadoLote(Long loteId, UpdateEstadoLoteRequest request) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        Objects.requireNonNull(request, "UpdateEstadoLoteRequest cannot be null");
        
        EstadoLote nuevoEstado = LoteDtoMapper.parseEstadoLote(request.nuevoEstado());
        
        return loteService.actualizarEstadoLote(loteId, nuevoEstado)
                .map(LoteDtoMapper::toDto);
    }
    
    public Mono<LoteDto> obtenerLotePorId(Long loteId) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        
        return loteService.obtenerPorId(loteId)
                .map(LoteDtoMapper::toDto);
    }
    
    public Mono<LoteDto> obtenerLotePorNumero(String numeroLote) {
        Objects.requireNonNull(numeroLote, "Numero lote cannot be null");
        
        return loteService.obtenerPorNumeroLote(numeroLote)
                .map(LoteDtoMapper::toDto);
    }
    
    public Flux<LoteDto> listarLotesPorEstado(String estado) {
        Objects.requireNonNull(estado, "Estado cannot be null");
        
        EstadoLote estadoLote = LoteDtoMapper.parseEstadoLote(estado);
        
        return loteService.listarPorEstado(estadoLote)
                .map(LoteDtoMapper::toDto);
    }
    
    public Flux<LoteDto> listarLotesPorTipo(String tipo) {
        Objects.requireNonNull(tipo, "Tipo cannot be null");
        
        TipoLote tipoLote = LoteDtoMapper.parseTipoLote(tipo);
        
        return loteService.listarPorTipo(tipoLote)
                .map(LoteDtoMapper::toDto);
    }
    
    public Flux<LoteDto> listarTodosLosLotes() {
        return loteService.listarTodos()
                .map(LoteDtoMapper::toDto);
    }
    
    public Mono<Boolean> existeLote(String numeroLote) {
        Objects.requireNonNull(numeroLote, "Numero lote cannot be null");
        
        return loteService.existeLote(numeroLote);
    }
    
    public Mono<Void> eliminarLote(Long loteId) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        
        return loteService.eliminarLote(loteId);
    }
    
    public Mono<LoteDto> actualizarProgreso(Long loteId, int procesados, int conError, int validos) {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        
        return loteService.actualizarProgreso(loteId, procesados, conError, validos)
                .map(LoteDtoMapper::toDto);
    }
}