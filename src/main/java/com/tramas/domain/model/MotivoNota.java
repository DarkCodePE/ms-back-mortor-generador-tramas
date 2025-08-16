package com.tramas.domain.model;

public record MotivoNota(
        String codigo,
        String nombre,
        TipoNota tipoNota
) {}