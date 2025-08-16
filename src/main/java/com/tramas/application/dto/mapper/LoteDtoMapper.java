package com.tramas.application.dto.mapper;

import com.tramas.application.dto.CreateLoteRequest;
import com.tramas.application.dto.LoteDto;
import com.tramas.domain.model.EstadoLote;
import com.tramas.domain.model.Lote;
import com.tramas.domain.model.TipoLote;

public class LoteDtoMapper {
    
    public static LoteDto toDto(Lote lote) {
        if (lote == null) return null;
        
        return new LoteDto(
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
    
    public static Lote toDomain(CreateLoteRequest request) {
        if (request == null) return null;
        
        TipoLote tipoLote = TipoLote.fromString(request.tipoLote());
        
        return Lote.create(
                request.numeroLote(),
                request.usuarioCreacion(),
                tipoLote
        );
    }
    
    public static EstadoLote parseEstadoLote(String estado) {
        return EstadoLote.fromString(estado);
    }
    
    public static TipoLote parseTipoLote(String tipo) {
        return TipoLote.fromString(tipo);
    }
}