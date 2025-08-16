package com.tramas.infrastructure.adapter.persistence.mapper;

import com.tramas.domain.model.EstadoLote;
import com.tramas.domain.model.Lote;
import com.tramas.domain.model.TipoLote;
import com.tramas.infrastructure.adapter.persistence.entity.LoteEntity;

public class LoteMapper {
    
    public static LoteEntity toEntity(Lote lote) {
        if (lote == null) return null;
        
        return new LoteEntity(
                lote.loteId(),
                lote.numeroLote(),
                lote.fechaCreacion(),
                lote.usuarioCreacion(),
                lote.estadoLote() != null ? lote.estadoLote().getValor() : null,
                lote.tipoLote() != null ? lote.tipoLote().getValor() : null,
                lote.archivoOrigen(),
                lote.totalRegistros(),
                lote.registrosProcesados(),
                lote.registrosConError(),
                lote.registrosValidos(),
                lote.fechaInicioProceso(),
                lote.fechaFinProceso(),
                lote.observaciones(),
                lote.rutaArchivo(),
                lote.fechaModificacion(),
                lote.usuarioModificacion()
        );
    }
    
    public static Lote toDomain(LoteEntity entity) {
        if (entity == null) return null;
        
        return new Lote(
                entity.loteId(),
                entity.numeroLote(),
                entity.fechaCreacion(),
                entity.usuarioCreacion(),
                EstadoLote.fromString(entity.estadoLote()),
                TipoLote.fromString(entity.tipoLote()),
                entity.archivoOrigen(),
                entity.totalRegistros(),
                entity.registrosProcesados(),
                entity.registrosConError(),
                entity.registrosValidos(),
                entity.fechaInicioProceso(),
                entity.fechaFinProceso(),
                entity.observaciones(),
                entity.rutaArchivo(),
                entity.fechaModificacion(),
                entity.usuarioModificacion()
        );
    }
}