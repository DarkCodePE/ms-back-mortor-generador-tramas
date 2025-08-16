package com.tramas.infrastructure.adapter.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Table("Catalogo.CodigoRubro")
public record MonedaEntity(
        @Id
        @Column("Codigo")
        String codigo,
        
        @Column("Descripcion")
        String descripcion,
        
        @Column("CategoriaPrincipal")
        String categoriaPrincipal,
        
        @Column("FechaCreacion")
        java.time.LocalDateTime fechaCreacion,
        
        @Column("Activo")
        Boolean activo
) {}