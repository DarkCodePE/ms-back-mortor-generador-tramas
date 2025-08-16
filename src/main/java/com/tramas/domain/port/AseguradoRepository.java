package com.tramas.domain.port;

import com.tramas.domain.model.Asegurado;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AseguradoRepository {
    
    Mono<Asegurado> save(Asegurado asegurado);
    
    Mono<Asegurado> findById(Long aseguradoId);
    
    Mono<Asegurado> findByCodigoAsegurado(String codigoAsegurado);
    
    Mono<Asegurado> findByTipoYNumeroDocumento(String tipoDocumento, String numeroDocumento);
    
    Flux<Asegurado> findByNombreCompletoContaining(String nombre);
    
    Flux<Asegurado> findByEstado(Boolean estado);
    
    Flux<Asegurado> findAll();
    
    Mono<Boolean> existsByCodigoAsegurado(String codigoAsegurado);
    
    Mono<Boolean> existsByTipoYNumeroDocumento(String tipoDocumento, String numeroDocumento);
    
    Mono<Void> deleteById(Long aseguradoId);
    
    Mono<Long> countByEstado(Boolean estado);
}