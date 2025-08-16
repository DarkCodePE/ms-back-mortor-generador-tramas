package com.tramas.infrastructure.adapter.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("Catalogo.tipo_personal_responsable")
public record TipoPersonalResponsableEntity(
        @Id
        @Column("IdTipoPersonalResponsable")
        String idTipoPersonalResponsable,
        
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