package com.tramas.infrastructure.adapter.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table("Catalogo.FiliacionPaciente")
public record AseguradoEntity(
        @Id
        @Column("Id")
        Long id,
        
        @Column("Codigo")
        String codigo,
        
        @Column("TipoDocumento")
        String tipoDocumento,
        
        @Column("NumeroDocumento")
        String numeroDocumento,
        
        @Column("NombresCompletos")
        String nombresCompletos,
        
        @Column("FechaNacimiento")
        LocalDate fechaNacimiento,
        
        @Column("Sexo")
        String sexo,
        
        @Column("TipoPersona")
        String tipoPersona,
        
        @Column("Estado")
        Boolean estado,
        
        @Column("FechaCreacion")
        LocalDateTime fechaCreacion,
        
        @Column("FechaModificacion")
        LocalDateTime fechaModificacion
) {}