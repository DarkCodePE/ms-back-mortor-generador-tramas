package com.tramas.application.dto;

import jakarta.validation.constraints.NotNull;

public record CreateTramaAtencionRequest(
        @NotNull(message = "El lote ID es obligatorio")
        Long loteId,
        
        @NotNull(message = "El número de línea es obligatorio")
        Integer numeroLinea
) {}