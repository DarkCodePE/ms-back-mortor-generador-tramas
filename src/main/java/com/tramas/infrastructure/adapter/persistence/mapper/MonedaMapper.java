package com.tramas.infrastructure.adapter.persistence.mapper;

import com.tramas.domain.model.Moneda;
import com.tramas.infrastructure.adapter.persistence.entity.MonedaEntity;

public class MonedaMapper {
    
    public static Moneda toDomain(MonedaEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new Moneda(
                entity.codigo(),
                entity.descripcion(), // Use descripcion as nombre
                entity.categoriaPrincipal() // Use categoriaPrincipal as simbolo
        );
    }
    
    public static MonedaEntity toEntity(Moneda domain) {
        if (domain == null) {
            return null;
        }
        
        return new MonedaEntity(
                domain.codigo(),
                domain.nombre(), // Maps to descripcion in entity
                domain.simbolo(), // Maps to categoriaPrincipal in entity
                java.time.LocalDateTime.now(), // fechaCreacion
                true // activo
        );
    }
}