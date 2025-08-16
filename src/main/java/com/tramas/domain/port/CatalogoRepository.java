package com.tramas.domain.port;

import com.tramas.domain.model.TipoAfiliacion;
import com.tramas.domain.model.TipoDocumentoPago;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CatalogoRepository {
    
    // Tipo Documento Pago
    Mono<TipoDocumentoPago> saveTipoDocumentoPago(TipoDocumentoPago tipoDocumentoPago);
    
    Mono<TipoDocumentoPago> findTipoDocumentoPagoById(String codigo);
    
    Flux<TipoDocumentoPago> findAllTipoDocumentoPago();
    
    Flux<TipoDocumentoPago> findTipoDocumentoPagoActivos();
    
    Mono<Boolean> existsTipoDocumentoPago(String codigo);
    
    Mono<Void> deleteTipoDocumentoPagoById(String codigo);
    
    // Tipo Afiliación
    Mono<TipoAfiliacion> saveTipoAfiliacion(TipoAfiliacion tipoAfiliacion);
    
    Mono<TipoAfiliacion> findTipoAfiliacionById(String codigo);
    
    Flux<TipoAfiliacion> findAllTipoAfiliacion();
    
    Flux<TipoAfiliacion> findTipoAfiliacionActivos();
    
    Mono<Boolean> existsTipoAfiliacion(String codigo);
    
    Mono<Void> deleteTipoAfiliacionById(String codigo);
}