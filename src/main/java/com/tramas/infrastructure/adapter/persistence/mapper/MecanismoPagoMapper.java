package com.tramas.infrastructure.adapter.persistence.mapper;

import com.tramas.domain.model.MecanismoPago;
import com.tramas.infrastructure.adapter.persistence.entity.MecanismoPagoEntity;

public class MecanismoPagoMapper {
    
    public static MecanismoPago toDomain(MecanismoPagoEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new MecanismoPago(
                entity.codigo(),
                entity.descripcion(), // Use descripcion as nombre
                entity.categoriaPrincipal() // Use categoriaPrincipal as subtipo
        );
    }
    
    public static MecanismoPagoEntity toEntity(MecanismoPago domain) {
        if (domain == null) {
            return null;
        }
        
        return new MecanismoPagoEntity(
                domain.codigo(),
                domain.nombre(), // Maps to descripcion in entity
                domain.subtipo(), // Maps to categoriaPrincipal in entity
                java.time.LocalDateTime.now(), // fechaCreacion
                true // activo
        );
    }
}