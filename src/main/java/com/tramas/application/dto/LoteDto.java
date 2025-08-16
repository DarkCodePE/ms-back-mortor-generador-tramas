package com.tramas.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record LoteDto(
        Long loteId,
        
        @NotBlank(message = "El número de lote es obligatorio")
        @Size(max = 50, message = "El número de lote no puede exceder 50 caracteres")
        String numeroLote,
        
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime fechaCreacion,
        
        @NotBlank(message = "El usuario de creación es obligatorio")
        @Size(max = 100, message = "El usuario de creación no puede exceder 100 caracteres")
        String usuarioCreacion,
        
        @NotNull(message = "El estado del lote es obligatorio")
        String estadoLote,
        
        @NotNull(message = "El tipo de lote es obligatorio")
        String tipoLote,
        
        @Size(max = 500, message = "El archivo origen no puede exceder 500 caracteres")
        String archivoOrigen,
        
        Integer totalRegistros,
        Integer registrosProcesados,
        Integer registrosConError,
        Integer registrosValidos,
        
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime fechaInicioProceso,
        
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime fechaFinProceso,
        
        String observaciones,
        String rutaArchivo,
        
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime fechaModificacion,
        
        String usuarioModificacion
) {}