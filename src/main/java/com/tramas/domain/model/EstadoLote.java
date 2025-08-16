package com.tramas.domain.model;

public enum EstadoLote {
    PENDIENTE("PENDIENTE"),
    EN_PROCESO("EN_PROCESO"),
    PROCESADO("PROCESADO"),
    VALIDADO("VALIDADO"),
    ERROR("ERROR"),
    COMPLETADO("COMPLETADO"),
    CANCELADO("CANCELADO");
    
    private final String valor;
    
    EstadoLote(String valor) {
        this.valor = valor;
    }
    
    public String getValor() {
        return valor;
    }
    
    public static EstadoLote fromString(String valor) {
        if (valor == null) return null;
        
        for (EstadoLote estado : EstadoLote.values()) {
            if (estado.valor.equals(valor)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado lote no válido: " + valor);
    }
}