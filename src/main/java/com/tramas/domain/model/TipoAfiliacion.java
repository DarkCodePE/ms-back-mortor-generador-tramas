package com.tramas.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public record TipoAfiliacion(
        String codigo,
        String descripcion,
        Boolean activo,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaModificacion
) {
    public TipoAfiliacion {
        Objects.requireNonNull(codigo, "Codigo cannot be null");
        Objects.requireNonNull(descripcion, "Descripcion cannot be null");
        
        if (activo == null) activo = true;
        if (fechaCreacion == null) fechaCreacion = LocalDateTime.now();
    }
    
    public static TipoAfiliacion create(String codigo, String descripcion) {
        return new TipoAfiliacion(
                codigo,
                descripcion,
                true,
                LocalDateTime.now(),
                null
        );
    }
    
    public TipoAfiliacion withEstado(Boolean nuevoEstado) {
        return new TipoAfiliacion(
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