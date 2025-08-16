package com.tramas.domain.model;

public enum TipoProfesional {
    MEDICO("01", "Médico"),
    ENFERMERO("02", "Enfermero"),
    OBSTETRA("03", "Obstetra"),
    ODONTOLOGO("04", "Odontólogo"),
    PSICOLOGO("05", "Psicólogo"),
    NUTRICIONISTA("06", "Nutricionista"),
    TECNICO_ENFERMERIA("07", "Técnico en Enfermería"),
    OTROS("99", "Otros");
    
    private final String codigo;
    private final String descripcion;
    
    TipoProfesional(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public static TipoProfesional fromCodigo(String codigo) {
        for (TipoProfesional tipo : values()) {
            if (tipo.codigo.equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código de tipo profesional no válido: " + codigo);
    }
}