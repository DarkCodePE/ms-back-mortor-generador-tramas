package com.tramas.application.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateEstadoLoteRequest(
        @NotNull(message = "El nuevo estado es obligatorio")
        String nuevoEstado
) {}