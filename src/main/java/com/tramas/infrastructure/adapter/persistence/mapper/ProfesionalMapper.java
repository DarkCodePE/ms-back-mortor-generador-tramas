package com.tramas.infrastructure.adapter.persistence.mapper;

import com.tramas.domain.model.Profesional;
import com.tramas.domain.model.TipoProfesional;
import com.tramas.infrastructure.adapter.persistence.entity.ProfesionalEntity;

public class ProfesionalMapper {
    
    public static Profesional toDomain(ProfesionalEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new Profesional(
                entity.id(),
                entity.tipoDocumento(),
                entity.numeroDocumento(),
                entity.nombresCompletos(),
                entity.numeroColegiaturo(),
                TipoProfesional.fromCodigo(entity.tipoProfesional())
        );
    }
    
    public static ProfesionalEntity toEntity(Profesional domain) {
        if (domain == null) {
            return null;
        }
        
        return new ProfesionalEntity(
                domain.profesionalId(),
                domain.tipoDocIdentidad(),
                domain.numDocIdentidad(),
                domain.nombreCompleto(),
                domain.numColegiatura(),
                domain.tipoProfesional().getCodigo(),
                java.time.LocalDateTime.now(), // fechaCreacion
                true // estado
        );
    }
}