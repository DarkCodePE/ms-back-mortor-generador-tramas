package com.tramas.domain.model;

public enum TipoNota {
    CREDITO("CREDITO", "Nota de Crédito"),
    DEBITO("DEBITO", "Nota de Débito");
    
    private final String valor;
    private final String descripcion;
    
    TipoNota(String valor, String descripcion) {
        this.valor = valor;
        this.descripcion = descripcion;
    }
    
    public String getValor() {
        return valor;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public static TipoNota fromValor(String valor) {
        for (TipoNota tipo : values()) {
            if (tipo.valor.equals(valor)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor de tipo nota no válido: " + valor);
    }
}