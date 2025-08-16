package com.tramas.domain.model;

public enum Sexo {
    MASCULINO("M"),
    FEMENINO("F");
    
    private final String valor;
    
    Sexo(String valor) {
        this.valor = valor;
    }
    
    public String getValor() {
        return valor;
    }
    
    public static Sexo fromString(String valor) {
        if (valor == null) return null;
        
        for (Sexo sexo : Sexo.values()) {
            if (sexo.valor.equals(valor)) {
                return sexo;
            }
        }
        throw new IllegalArgumentException("Sexo no válido: " + valor);
    }
}