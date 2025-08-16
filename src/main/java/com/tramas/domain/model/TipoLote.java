package com.tramas.domain.model;

public enum TipoLote {
    TRAMA_ATENCION("TRAMA_ATENCION"),
    TRAMA_FARMACIA("TRAMA_FARMACIA"),
    TRAMA_SERVICIO("TRAMA_SERVICIO"),
    TRAMA_DENTAL("TRAMA_DENTAL"),
    TRAMA_FACTURA("TRAMA_FACTURA");
    
    private final String valor;
    
    TipoLote(String valor) {
        this.valor = valor;
    }
    
    public String getValor() {
        return valor;
    }
    
    public static TipoLote fromString(String valor) {
        if (valor == null) return null;
        
        for (TipoLote tipo : TipoLote.values()) {
            if (tipo.valor.equals(valor)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo lote no válido: " + valor);
    }
}