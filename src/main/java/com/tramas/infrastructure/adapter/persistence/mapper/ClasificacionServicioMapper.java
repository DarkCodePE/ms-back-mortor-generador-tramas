package com.tramas.infrastructure.adapter.persistence.mapper;

import com.tramas.domain.model.ClasificacionServicio;
import com.tramas.infrastructure.adapter.persistence.entity.ClasificacionServicioEntity;

public class ClasificacionServicioMapper {
    
    public static ClasificacionServicio toDomain(ClasificacionServicioEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new ClasificacionServicio(
                entity.codigo(),
                entity.descripcion(), // Use descripcion as nombre
                entity.categoriaPrincipal() // Use categoriaPrincipal as descripcion
        );
    }
    
    public static ClasificacionServicioEntity toEntity(ClasificacionServicio domain) {
        if (domain == null) {
            return null;
        }
        
        return new ClasificacionServicioEntity(
                domain.codigo(),
                domain.nombre(), // Maps to descripcion in entity
                domain.descripcion(), // Maps to categoriaPrincipal in entity
                java.time.LocalDateTime.now(), // fechaCreacion
                true // activo
        );
    }
}