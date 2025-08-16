package com.tramas.infrastructure.adapter.web;

import com.tramas.domain.model.*;
import com.tramas.domain.service.CatalogoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/catalogos")
public class CatalogoController {
    
    private final CatalogoService catalogoService;
    
    public CatalogoController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }
    
    // === PROFESIONALES ===
    
    @GetMapping(value = "/profesionales", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Profesional> obtenerProfesionales(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String tipoProfesional) {
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            return catalogoService.buscarProfesionalesPorNombre(nombre.trim());
        }
        
        if (tipoProfesional != null && !tipoProfesional.trim().isEmpty()) {
            try {
                TipoProfesional tipo = TipoProfesional.fromCodigo(tipoProfesional);
                return catalogoService.obtenerProfesionalesPorTipo(tipo);
            } catch (IllegalArgumentException e) {
                return Flux.empty();
            }
        }
        
        return catalogoService.obtenerProfesionales();
    }
    
    @GetMapping(value = "/profesionales/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Profesional> obtenerProfesionalPorId(@PathVariable Integer id) {
        return catalogoService.obtenerProfesionalPorId(id);
    }
    
    @GetMapping(value = "/profesionales/buscar-por-documento", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Profesional> buscarProfesionalPorDocumento(
            @RequestParam String tipoDoc,
            @RequestParam String numeroDoc) {
        return catalogoService.buscarProfesionalPorDocumento(tipoDoc, numeroDoc);
    }
    
    // === CLASIFICACIONES DE SERVICIO ===
    
    @GetMapping(value = "/clasificaciones-servicio", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ClasificacionServicio> obtenerClasificacionesServicio(
            @RequestParam(required = false) String nombre) {
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            return catalogoService.buscarClasificacionesServicioPorNombre(nombre.trim());
        }
        
        return catalogoService.obtenerClasificacionesServicio();
    }
    
    @GetMapping(value = "/clasificaciones-servicio/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ClasificacionServicio> obtenerClasificacionServicioPorCodigo(@PathVariable String codigo) {
        return catalogoService.obtenerClasificacionServicioPorCodigo(codigo);
    }
    
    // === MONEDAS ===
    
    @GetMapping(value = "/monedas", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Moneda> obtenerMonedas(@RequestParam(required = false) String nombre) {
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            return catalogoService.buscarMonedasPorNombre(nombre.trim());
        }
        
        return catalogoService.obtenerMonedas();
    }
    
    @GetMapping(value = "/monedas/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Moneda> obtenerMonedaPorCodigo(@PathVariable String codigo) {
        return catalogoService.obtenerMonedaPorCodigo(codigo);
    }
    
    // === MECANISMOS DE PAGO ===
    
    @GetMapping(value = "/mecanismos-pago", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<MecanismoPago> obtenerMecanismosPago(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String subtipo) {
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            return catalogoService.buscarMecanismosPagoPorNombre(nombre.trim());
        }
        
        if (subtipo != null && !subtipo.trim().isEmpty()) {
            return catalogoService.obtenerMecanismosPagoPorSubtipo(subtipo.trim());
        }
        
        return catalogoService.obtenerMecanismosPago();
    }
    
    @GetMapping(value = "/mecanismos-pago/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<MecanismoPago> obtenerMecanismoPagoPorCodigo(@PathVariable String codigo) {
        return catalogoService.obtenerMecanismoPagoPorCodigo(codigo);
    }
    
    // === MOTIVOS DE NOTA ===
    
    @GetMapping(value = "/motivos-nota", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<MotivoNota> obtenerMotivosNota(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String tipoNota) {
        
        if (nombre != null && !nombre.trim().isEmpty()) {
            return catalogoService.buscarMotivosPorNombre(nombre.trim());
        }
        
        if (tipoNota != null && !tipoNota.trim().isEmpty()) {
            try {
                TipoNota tipo = TipoNota.fromValor(tipoNota.toUpperCase());
                return catalogoService.obtenerMotivosPorTipoNota(tipo);
            } catch (IllegalArgumentException e) {
                return Flux.empty();
            }
        }
        
        return catalogoService.obtenerMotivosNota();
    }
    
    @GetMapping(value = "/motivos-nota/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<MotivoNota> obtenerMotivoNotaPorCodigo(@PathVariable String codigo) {
        return catalogoService.obtenerMotivoNotaPorCodigo(codigo);
    }
    
    // === ENDPOINTS DE APOYO ===
    
    @GetMapping(value = "/tipos-profesional", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<TipoProfesional> obtenerTiposProfesional() {
        return Flux.fromArray(TipoProfesional.values());
    }
    
    @GetMapping(value = "/tipos-nota", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<TipoNota> obtenerTiposNota() {
        return Flux.fromArray(TipoNota.values());
    }
}