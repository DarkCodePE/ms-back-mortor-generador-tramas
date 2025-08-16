package com.tramas.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public record TipoDocumentoPago(
        String codigo,
        String descripcion,
        Boolean activo,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaModificacion
) {
    public TipoDocumentoPago {
        Objects.requireNonNull(codigo, "Codigo cannot be null");
        Objects.requireNonNull(descripcion, "Descripcion cannot be null");
        
        if (activo == null) activo = true;
        if (fechaCreacion == null) fechaCreacion = LocalDateTime.now();
    }
    
    public static TipoDocumentoPago create(String codigo, String descripcion) {
        return new TipoDocumentoPago(
                codigo,
                descripcion,
                true,
                LocalDateTime.now(),
                null
        );
    }
    
    public TipoDocumentoPago withEstado(Boolean nuevoEstado) {
        return new TipoDocumentoPago(
                codigo,
                descripcion,
                nuevoEstado,
                fechaCreacion,
                LocalDateTime.now()
        );
    }
    
    public boolean isActivo() {
        return Boolean.TRUE.equals(activo);
    }
}