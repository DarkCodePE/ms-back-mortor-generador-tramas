package com.tramas.infrastructure.adapter.web;

import com.tramas.application.dto.CreateTramaAtencionRequest;
import com.tramas.application.dto.TramaAtencionDto;
import com.tramas.application.service.TramaAtencionApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/tramas-atencion")
public class TramaAtencionController {
    
    private final TramaAtencionApplicationService tramaAtencionApplicationService;
    
    public TramaAtencionController(TramaAtencionApplicationService tramaAtencionApplicationService) {
        this.tramaAtencionApplicationService = tramaAtencionApplicationService;
    }
    
    @PostMapping
    public Mono<ResponseEntity<TramaAtencionDto>> crearTramaAtencion(
            @Valid @RequestBody CreateTramaAtencionRequest request) {
        
        return tramaAtencionApplicationService.crearTramaAtencion(request)
                .map(trama -> ResponseEntity.status(HttpStatus.CREATED).body(trama));
    }
    
    @GetMapping("/{stagingId}")
    public Mono<ResponseEntity<TramaAtencionDto>> obtenerTrama(@PathVariable Long stagingId) {
        return tramaAtencionApplicationService.obtenerTramaPorId(stagingId)
                .map(trama -> ResponseEntity.ok(trama))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/lote/{loteId}")
    public Flux<TramaAtencionDto> listarTramasPorLote(@PathVariable Long loteId) {
        return tramaAtencionApplicationService.listarTramasPorLote(loteId);
    }
    
    @GetMapping
    public Flux<TramaAtencionDto> listarTramas(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) Long loteId) {
        
        if (estado != null && loteId != null) {
            return tramaAtencionApplicationService.listarTramasPorLoteYEstado(loteId, estado);
        }
        
        if (estado != null) {
            return tramaAtencionApplicationService.listarTramasPorEstado(estado);
        }
        
        if (loteId != null) {
            return tramaAtencionApplicationService.listarTramasPorLote(loteId);
        }
        
        return Flux.empty(); // Evitar listar todas las tramas sin filtros
    }
    
    @PutMapping("/{stagingId}")
    public Mono<ResponseEntity<TramaAtencionDto>> actualizarTrama(
            @PathVariable Long stagingId,
            @Valid @RequestBody TramaAtencionDto tramaDto) {
        
        // Asegurar que el ID coincida
        TramaAtencionDto tramaConId = new TramaAtencionDto(
                stagingId,
                tramaDto.loteId(),
                tramaDto.numeroLinea(),
                tramaDto.codNumPagoSita(),
                tramaDto.tipoDocPago(),
                tramaDto.numDocPago(),
                tramaDto.fechaEmision(),
                tramaDto.codigoIpress(),
                tramaDto.codigoAfiliado(),
                tramaDto.numeroHistoriaClinica(),
                tramaDto.tipoAfiliacion(),
                tramaDto.codigoAtencion(),
                tramaDto.tipoDocumentoPaciente(),
                tramaDto.numeroDocumentoPaciente(),
                tramaDto.apellidosNombres(),
                tramaDto.tipoDocAutorizador(),
                tramaDto.numDocAutorizador(),
                tramaDto.segundoDocAutorizador(),
                tramaDto.codigoAutorizacion(),
                tramaDto.tipoCobertura(),
                tramaDto.subtipoCobertura(),
                tramaDto.fechaAtencion(),
                tramaDto.fechaSalida(),
                tramaDto.codigoDxPrincipal(),
                tramaDto.codigoDxSecundario1(),
                tramaDto.codigoDxSecundario2(),
                tramaDto.tipoPersonalResponsable(),
                tramaDto.codigoPersonalResponsable(),
                tramaDto.tipoDocPersonalResponsable(),
                tramaDto.numDocPersonalResponsable(),
                tramaDto.nombrePersonalResponsable(),
                tramaDto.montoCobertura(),
                tramaDto.montoSalud(),
                tramaDto.tipoHospitalizacion(),
                tramaDto.fechaIngresoHospital(),
                tramaDto.fechaEgreso(),
                tramaDto.tipoEgresoHospitalario(),
                tramaDto.estadoRegistro(),
                tramaDto.fechaIngresoRegistro(),
                tramaDto.fechaProcesamiento(),
                tramaDto.usuarioProcesamiento(),
                tramaDto.observacionesValidacion(),
                tramaDto.erroresValidacion(),
                tramaDto.datosOriginales()
        );
        
        return tramaAtencionApplicationService.actualizarTramaAtencion(tramaConId)
                .map(trama -> ResponseEntity.ok(trama))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{stagingId}/estado")
    public Mono<ResponseEntity<TramaAtencionDto>> cambiarEstadoTrama(
            @PathVariable Long stagingId,
            @RequestParam String nuevoEstado) {
        
        return tramaAtencionApplicationService.cambiarEstadoTrama(stagingId, nuevoEstado)
                .map(trama -> ResponseEntity.ok(trama))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{stagingId}/validar")
    public Mono<ResponseEntity<TramaAtencionDto>> validarTrama(
            @PathVariable Long stagingId,
            @RequestParam(required = false) String observaciones,
            @RequestParam(required = false) String errores) {
        
        return tramaAtencionApplicationService.validarTrama(stagingId, observaciones, errores)
                .map(trama -> ResponseEntity.ok(trama))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/lote/{loteId}/count")
    public Mono<ResponseEntity<Long>> contarTramasPorLote(@PathVariable Long loteId) {
        return tramaAtencionApplicationService.contarTramasPorLote(loteId)
                .map(count -> ResponseEntity.ok(count));
    }
    
    @GetMapping("/lote/{loteId}/count/{estado}")
    public Mono<ResponseEntity<Long>> contarTramasPorLoteYEstado(
            @PathVariable Long loteId,
            @PathVariable String estado) {
        
        return tramaAtencionApplicationService.contarTramasPorLoteYEstado(loteId, estado)
                .map(count -> ResponseEntity.ok(count));
    }
    
    @DeleteMapping("/{stagingId}")
    public Mono<ResponseEntity<Void>> eliminarTrama(@PathVariable Long stagingId) {
        return tramaAtencionApplicationService.eliminarTrama(stagingId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
    
    @DeleteMapping("/lote/{loteId}")
    public Mono<ResponseEntity<Void>> eliminarTramasPorLote(@PathVariable Long loteId) {
        return tramaAtencionApplicationService.eliminarTramasPorLote(loteId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}