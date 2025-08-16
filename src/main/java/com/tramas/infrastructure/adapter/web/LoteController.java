package com.tramas.infrastructure.adapter.web;

import com.tramas.application.dto.CreateLoteRequest;
import com.tramas.application.dto.LoteDto;
import com.tramas.application.dto.UpdateEstadoLoteRequest;
import com.tramas.application.service.LoteApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/lotes")
public class LoteController {
    
    private final LoteApplicationService loteApplicationService;
    
    public LoteController(LoteApplicationService loteApplicationService) {
        this.loteApplicationService = loteApplicationService;
    }
    
    @PostMapping
    public Mono<ResponseEntity<LoteDto>> crearLote(@Valid @RequestBody CreateLoteRequest request) {
        return loteApplicationService.crearLote(request)
                .map(lote -> ResponseEntity.status(HttpStatus.CREATED).body(lote));
    }
    
    @GetMapping("/{loteId}")
    public Mono<ResponseEntity<LoteDto>> obtenerLote(@PathVariable Long loteId) {
        return loteApplicationService.obtenerLotePorId(loteId)
                .map(lote -> ResponseEntity.ok(lote))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/numero/{numeroLote}")
    public Mono<ResponseEntity<LoteDto>> obtenerLotePorNumero(@PathVariable String numeroLote) {
        return loteApplicationService.obtenerLotePorNumero(numeroLote)
                .map(lote -> ResponseEntity.ok(lote))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public Flux<LoteDto> listarLotes(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String tipo) {
        
        if (estado != null) {
            return loteApplicationService.listarLotesPorEstado(estado);
        }
        
        if (tipo != null) {
            return loteApplicationService.listarLotesPorTipo(tipo);
        }
        
        return loteApplicationService.listarTodosLosLotes();
    }
    
    @PutMapping("/{loteId}/estado")
    public Mono<ResponseEntity<LoteDto>> actualizarEstadoLote(
            @PathVariable Long loteId,
            @Valid @RequestBody UpdateEstadoLoteRequest request) {
        
        return loteApplicationService.actualizarEstadoLote(loteId, request)
                .map(lote -> ResponseEntity.ok(lote))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{loteId}/progreso")
    public Mono<ResponseEntity<LoteDto>> actualizarProgreso(
            @PathVariable Long loteId,
            @RequestParam int procesados,
            @RequestParam int conError,
            @RequestParam int validos) {
        
        return loteApplicationService.actualizarProgreso(loteId, procesados, conError, validos)
                .map(lote -> ResponseEntity.ok(lote))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{loteId}")
    public Mono<ResponseEntity<Void>> eliminarLote(@PathVariable Long loteId) {
        return loteApplicationService.eliminarLote(loteId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
    
    @GetMapping("/{numeroLote}/exists")
    public Mono<ResponseEntity<Boolean>> existeLote(@PathVariable String numeroLote) {
        return loteApplicationService.existeLote(numeroLote)
                .map(exists -> ResponseEntity.ok(exists));
    }
}