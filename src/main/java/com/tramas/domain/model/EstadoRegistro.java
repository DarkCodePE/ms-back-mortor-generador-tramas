package com.tramas.domain.model;

public enum EstadoRegistro {
    PENDIENTE("PENDIENTE"),
    VALIDANDO("VALIDANDO"),
    VALIDADO("VALIDADO"),
    ERROR("ERROR"),
    PROCESADO("PROCESADO"),
    INTEGRADO("INTEGRADO");
    
    private final String valor;
    
    EstadoRegistro(String valor) {
        this.valor = valor;
    }
    
    public String getValor() {
        return valor;
    }
    
    public static EstadoRegistro fromString(String valor) {
        if (valor == null) return null;
        
        for (EstadoRegistro estado : EstadoRegistro.values()) {
            if (estado.valor.equals(valor)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado registro no válido: " + valor);
    }
}