package com.tramas.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public record Asegurado(
        Long aseguradoId,
        String codigoAsegurado,
        String tipoDocumento,
        String numeroDocumento,
        String nombreCompleto,
        LocalDate fechaNacimiento,
        Sexo sexo,
        Boolean estado,
        LocalDateTime fechaCreacion,
        LocalDateTime fechaModificacion,
        LocalDateTime fechaSincronizacion
) {
    public Asegurado {
        Objects.requireNonNull(codigoAsegurado, "Codigo asegurado cannot be null");
        Objects.requireNonNull(tipoDocumento, "Tipo documento cannot be null");
        Objects.requireNonNull(numeroDocumento, "Numero documento cannot be null");
        Objects.requireNonNull(nombreCompleto, "Nombre completo cannot be null");
        
        if (estado == null) estado = true;
        if (fechaCreacion == null) fechaCreacion = LocalDateTime.now();
    }
    
    public static Asegurado create(
            String codigoAsegurado,
            String tipoDocumento,
            String numeroDocumento,
            String nombreCompleto,
            LocalDate fechaNacimiento,
            Sexo sexo
    ) {
        return new Asegurado(
                null,
                codigoAsegurado,
                tipoDocumento,
                numeroDocumento,
                nombreCompleto,
                fechaNacimiento,
                sexo,
                true,
                LocalDateTime.now(),
                null,
                null
        );
    }
    
    public Asegurado withEstado(Boolean nuevoEstado) {
        return new Asegurado(
                aseguradoId, codigoAsegurado, tipoDocumento, numeroDocumento,
                nombreCompleto, fechaNacimiento, sexo, nuevoEstado,
                fechaCreacion, LocalDateTime.now(), fechaSincronizacion
        );
    }
    
    public Asegurado withSincronizacion(LocalDateTime fechaSincronizacion) {
        return new Asegurado(
                aseguradoId, codigoAsegurado, tipoDocumento, numeroDocumento,
                nombreCompleto, fechaNacimiento, sexo, estado,
                fechaCreacion, LocalDateTime.now(), fechaSincronizacion
        );
    }
    
    public boolean isActivo() {
        return Boolean.TRUE.equals(estado);
    }
}