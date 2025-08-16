package com.tramas.domain.service;

import com.tramas.domain.model.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CatalogoService {
    
    // Profesionales
    Flux<Profesional> obtenerProfesionales();
    Mono<Profesional> obtenerProfesionalPorId(Integer id);
    Flux<Profesional> buscarProfesionalesPorNombre(String nombre);
    Flux<Profesional> obtenerProfesionalesPorTipo(TipoProfesional tipo);
    Flux<Profesional> buscarProfesionalPorDocumento(String tipoDoc, String numDoc);
    
    // Clasificaciones de Servicio
    Flux<ClasificacionServicio> obtenerClasificacionesServicio();
    Mono<ClasificacionServicio> obtenerClasificacionServicioPorCodigo(String codigo);
    Flux<ClasificacionServicio> buscarClasificacionesServicioPorNombre(String nombre);
    
    // Monedas
    Flux<Moneda> obtenerMonedas();
    Mono<Moneda> obtenerMonedaPorCodigo(String codigo);
    Flux<Moneda> buscarMonedasPorNombre(String nombre);
    
    // Mecanismos de Pago
    Flux<MecanismoPago> obtenerMecanismosPago();
    Mono<MecanismoPago> obtenerMecanismoPagoPorCodigo(String codigo);
    Flux<MecanismoPago> buscarMecanismosPagoPorNombre(String nombre);
    Flux<MecanismoPago> obtenerMecanismosPagoPorSubtipo(String subtipo);
    
    // Motivos de Nota
    Flux<MotivoNota> obtenerMotivosNota();
    Mono<MotivoNota> obtenerMotivoNotaPorCodigo(String codigo);
    Flux<MotivoNota> obtenerMotivosPorTipoNota(TipoNota tipoNota);
    Flux<MotivoNota> buscarMotivosPorNombre(String nombre);
}