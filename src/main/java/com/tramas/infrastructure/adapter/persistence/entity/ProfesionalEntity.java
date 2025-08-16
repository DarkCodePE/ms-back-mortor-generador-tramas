package com.tramas.infrastructure.adapter.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("Catalogo.FiliacionPaciente")
public record ProfesionalEntity(
        @Id
        @Column("Id")
        Integer id,
        
        @Column("TipoDocumento")
        String tipoDocumento,
        
        @Column("NumeroDocumento")
        String numeroDocumento,
        
        @Column("NombresCompletos")
        String nombresCompletos,
        
        @Column("NumeroColegiaturo")
        String numeroColegiaturo,
        
        @Column("TipoProfesional")
        String tipoProfesional,
        
        @Column("FechaCreacion")
        java.time.LocalDateTime fechaCreacion,
        
        @Column("Estado")
        Boolean estado
) {}