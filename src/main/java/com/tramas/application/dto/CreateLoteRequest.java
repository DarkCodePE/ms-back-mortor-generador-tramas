package com.tramas.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateLoteRequest(
        @NotBlank(message = "El número de lote es obligatorio")
        @Size(max = 50, message = "El número de lote no puede exceder 50 caracteres")
        String numeroLote,
        
        @NotBlank(message = "El usuario de creación es obligatorio")
        @Size(max = 100, message = "El usuario de creación no puede exceder 100 caracteres")
        String usuarioCreacion,
        
        @NotNull(message = "El tipo de lote es obligatorio")
        String tipoLote,
        
        @Size(max = 500, message = "El archivo origen no puede exceder 500 caracteres")
        String archivoOrigen
) {}