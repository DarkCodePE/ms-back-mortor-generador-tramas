package com.tramas.infrastructure.adapter.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("Catalogo.tipo_egreso_hospitalario")
public record TipoEgresoHospitalarioEntity(
        @Id
        @Column("IdTipoEgresoHospitalario")
        String idTipoEgresoHospitalario,
        
        @Column("Descripcion")
        String descripcion,
        
        @Column("FechaCreacion")
        LocalDateTime fechaCreacion,
        
        @Column("FechaModificacion")
        LocalDateTime fechaModificacion,
        
        @Column("UsuarioCreacion")
        String usuarioCreacion,
        
        @Column("UsuarioModificacion")
        String usuarioModificacion,
        
        @Column("Estado")
        Boolean estado
) {}