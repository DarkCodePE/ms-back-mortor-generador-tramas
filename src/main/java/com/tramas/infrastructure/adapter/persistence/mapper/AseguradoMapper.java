package com.tramas.infrastructure.adapter.persistence.mapper;

import com.tramas.domain.model.Asegurado;
import com.tramas.domain.model.Sexo;
import com.tramas.infrastructure.adapter.persistence.entity.AseguradoEntity;

public class AseguradoMapper {
    
    public static AseguradoEntity toEntity(Asegurado asegurado) {
        if (asegurado == null) return null;
        
        return new AseguradoEntity(
                asegurado.aseguradoId(),
                asegurado.codigoAsegurado(),
                asegurado.tipoDocumento(),
                asegurado.numeroDocumento(),
                asegurado.nombreCompleto(),
                asegurado.fechaNacimiento(),
                asegurado.sexo() != null ? asegurado.sexo().getValor() : null,
                "PACIENTE", // tipoPersona - discriminator for FiliacionPaciente table
                asegurado.estado(),
                asegurado.fechaCreacion(),
                asegurado.fechaModificacion()
        );
    }
    
    public static Asegurado toDomain(AseguradoEntity entity) {
        if (entity == null) return null;
        
        return new Asegurado(
                entity.id(),
                entity.codigo(),
                entity.tipoDocumento(),
                entity.numeroDocumento(),
                entity.nombresCompletos(),
                entity.fechaNacimiento(),
                Sexo.fromString(entity.sexo()),
                entity.estado(),
                entity.fechaCreacion(),
                entity.fechaModificacion(),
                null // fechaSincronizacion not in entity
        );
    }
}