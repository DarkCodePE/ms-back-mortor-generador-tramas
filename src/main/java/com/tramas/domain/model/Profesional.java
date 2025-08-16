package com.tramas.domain.model;

public record Profesional(
        Integer profesionalId,
        String tipoDocIdentidad,
        String numDocIdentidad,
        String nombreCompleto,
        String numColegiatura,
        TipoProfesional tipoProfesional
) {}