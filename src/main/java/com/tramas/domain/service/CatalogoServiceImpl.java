package com.tramas.domain.service;

import com.tramas.domain.model.*;
import com.tramas.infrastructure.adapter.persistence.*;
import com.tramas.infrastructure.adapter.persistence.mapper.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CatalogoServiceImpl implements CatalogoService {
    
    private final ProfesionalR2dbcRepository profesionalRepository;
    private final ClasificacionServicioR2dbcRepository clasificacionServicioRepository;
    private final MonedaR2dbcRepository monedaRepository;
    private final MecanismoPagoR2dbcRepository mecanismoPagoRepository;
    private final MotivoNotaR2dbcRepository motivoNotaRepository;
    
    public CatalogoServiceImpl(
            ProfesionalR2dbcRepository profesionalRepository,
            ClasificacionServicioR2dbcRepository clasificacionServicioRepository,
            MonedaR2dbcRepository monedaRepository,
            MecanismoPagoR2dbcRepository mecanismoPagoRepository,
            MotivoNotaR2dbcRepository motivoNotaRepository) {
        this.profesionalRepository = profesionalRepository;
        this.clasificacionServicioRepository = clasificacionServicioRepository;
        this.monedaRepository = monedaRepository;
        this.mecanismoPagoRepository = mecanismoPagoRepository;
        this.motivoNotaRepository = motivoNotaRepository;
    }
    
    // Profesionales
    @Override
    public Flux<Profesional> obtenerProfesionales() {
        return profesionalRepository.findAll()
                .map(ProfesionalMapper::toDomain);
    }
    
    @Override
    public Mono<Profesional> obtenerProfesionalPorId(Integer id) {
        return profesionalRepository.findById(id)
                .map(ProfesionalMapper::toDomain);
    }
    
    @Override
    public Flux<Profesional> buscarProfesionalesPorNombre(String nombre) {
        return profesionalRepository.findByNombresContainingIgnoreCase(nombre)
                .map(ProfesionalMapper::toDomain);
    }
    
    @Override
    public Flux<Profesional> obtenerProfesionalesPorTipo(TipoProfesional tipo) {
        return profesionalRepository.findByTipoProfesionalOrderByNombres(tipo.getCodigo())
                .map(ProfesionalMapper::toDomain);
    }
    
    @Override
    public Flux<Profesional> buscarProfesionalPorDocumento(String tipoDoc, String numDoc) {
        return profesionalRepository.findByTipoDocumentoAndNumeroDocumento(tipoDoc, numDoc)
                .flux()
                .map(ProfesionalMapper::toDomain);
    }
    
    // Clasificaciones de Servicio
    @Override
    public Flux<ClasificacionServicio> obtenerClasificacionesServicio() {
        return clasificacionServicioRepository.findAllActiveByOrderByDescripcion()
                .map(ClasificacionServicioMapper::toDomain);
    }
    
    @Override
    public Mono<ClasificacionServicio> obtenerClasificacionServicioPorCodigo(String codigo) {
        return clasificacionServicioRepository.findById(codigo)
                .map(ClasificacionServicioMapper::toDomain);
    }
    
    @Override
    public Flux<ClasificacionServicio> buscarClasificacionesServicioPorNombre(String nombre) {
        return clasificacionServicioRepository.findByDescripcionContainingIgnoreCase(nombre)
                .map(ClasificacionServicioMapper::toDomain);
    }
    
    // Monedas
    @Override
    public Flux<Moneda> obtenerMonedas() {
        return monedaRepository.findAllActiveByOrderByDescripcion()
                .map(MonedaMapper::toDomain);
    }
    
    @Override
    public Mono<Moneda> obtenerMonedaPorCodigo(String codigo) {
        return monedaRepository.findById(codigo)
                .map(MonedaMapper::toDomain);
    }
    
    @Override
    public Flux<Moneda> buscarMonedasPorNombre(String nombre) {
        return monedaRepository.findByDescripcionContainingIgnoreCase(nombre)
                .map(MonedaMapper::toDomain);
    }
    
    // Mecanismos de Pago
    @Override
    public Flux<MecanismoPago> obtenerMecanismosPago() {
        return mecanismoPagoRepository.findAllActiveByOrderByDescripcion()
                .map(MecanismoPagoMapper::toDomain);
    }
    
    @Override
    public Mono<MecanismoPago> obtenerMecanismoPagoPorCodigo(String codigo) {
        return mecanismoPagoRepository.findById(codigo)
                .map(MecanismoPagoMapper::toDomain);
    }
    
    @Override
    public Flux<MecanismoPago> buscarMecanismosPagoPorNombre(String nombre) {
        return mecanismoPagoRepository.findByDescripcionContainingIgnoreCase(nombre)
                .map(MecanismoPagoMapper::toDomain);
    }
    
    @Override
    public Flux<MecanismoPago> obtenerMecanismosPagoPorSubtipo(String subtipo) {
        return mecanismoPagoRepository.findAllActive() // Note: Subtipo filter removed due to schema change
                .map(MecanismoPagoMapper::toDomain);
    }
    
    // Motivos de Nota
    @Override
    public Flux<MotivoNota> obtenerMotivosNota() {
        return motivoNotaRepository.findAllActiveByOrderByDescripcion()
                .map(MotivoNotaMapper::toDomain);
    }
    
    @Override
    public Mono<MotivoNota> obtenerMotivoNotaPorCodigo(String codigo) {
        return motivoNotaRepository.findById(codigo)
                .map(MotivoNotaMapper::toDomain);
    }
    
    @Override
    public Flux<MotivoNota> obtenerMotivosPorTipoNota(TipoNota tipoNota) {
        return motivoNotaRepository.findAllActive() // Note: TipoNota filter removed due to schema change
                .map(MotivoNotaMapper::toDomain);
    }
    
    @Override
    public Flux<MotivoNota> buscarMotivosPorNombre(String nombre) {
        return motivoNotaRepository.findByDescripcionContainingIgnoreCase(nombre)
                .map(MotivoNotaMapper::toDomain);
    }
}