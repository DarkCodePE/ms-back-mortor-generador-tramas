package com.tramas.infrastructure.adapter.persistence.mapper;

import com.tramas.domain.model.MotivoNota;
import com.tramas.domain.model.TipoNota;
import com.tramas.infrastructure.adapter.persistence.entity.MotivoNotaEntity;

public class MotivoNotaMapper {
    
    public static MotivoNota toDomain(MotivoNotaEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new MotivoNota(
                entity.codigo(),
                entity.descripcion(), // Use descripcion as nombre
                TipoNota.fromValor(entity.categoriaPrincipal()) // Use categoriaPrincipal as tipoNota
        );
    }
    
    public static MotivoNotaEntity toEntity(MotivoNota domain) {
        if (domain == null) {
            return null;
        }
        
        return new MotivoNotaEntity(
                domain.codigo(),
                domain.nombre(), // Maps to descripcion in entity
                domain.tipoNota().getValor(), // Maps to categoriaPrincipal in entity
                java.time.LocalDateTime.now(), // fechaCreacion
                true // activo
        );
    }
}