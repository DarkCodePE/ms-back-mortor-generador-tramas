package com.tramas.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public record Lote(
        Long loteId,
        String numeroLote,
        LocalDateTime fechaCreacion,
        String usuarioCreacion,
        EstadoLote estadoLote,
        TipoLote tipoLote,
        String archivoOrigen,
        Integer totalRegistros,
        Integer registrosProcesados,
        Integer registrosConError,
        Integer registrosValidos,
        LocalDateTime fechaInicioProceso,
        LocalDateTime fechaFinProceso,
        String observaciones,
        String rutaArchivo,
        LocalDateTime fechaModificacion,
        String usuarioModificacion
) {
    public Lote {
        Objects.requireNonNull(numeroLote, "Numero lote cannot be null");
        Objects.requireNonNull(fechaCreacion, "Fecha creacion cannot be null");
        Objects.requireNonNull(usuarioCreacion, "Usuario creacion cannot be null");
        Objects.requireNonNull(estadoLote, "Estado lote cannot be null");
        Objects.requireNonNull(tipoLote, "Tipo lote cannot be null");
        
        if (totalRegistros == null) totalRegistros = 0;
        if (registrosProcesados == null) registrosProcesados = 0;
        if (registrosConError == null) registrosConError = 0;
        if (registrosValidos == null) registrosValidos = 0;
    }
    
    public static Lote create(String numeroLote, String usuarioCreacion, TipoLote tipoLote) {
        return new Lote(
                null,
                numeroLote,
                LocalDateTime.now(),
                usuarioCreacion,
                EstadoLote.PENDIENTE,
                tipoLote,
                null,
                0,
                0,
                0,
                0,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
    
    public Lote withEstado(EstadoLote nuevoEstado) {
        return new Lote(
                loteId, numeroLote, fechaCreacion, usuarioCreacion, nuevoEstado,
                tipoLote, archivoOrigen, totalRegistros, registrosProcesados,
                registrosConError, registrosValidos, fechaInicioProceso,
                fechaFinProceso, observaciones, rutaArchivo,
                LocalDateTime.now(), usuarioCreacion
        );
    }
    
    public Lote withProgreso(int procesados, int conError, int validos) {
        return new Lote(
                loteId, numeroLote, fechaCreacion, usuarioCreacion, estadoLote,
                tipoLote, archivoOrigen, totalRegistros, procesados,
                conError, validos, fechaInicioProceso, fechaFinProceso,
                observaciones, rutaArchivo, LocalDateTime.now(), usuarioCreacion
        );
    }
}