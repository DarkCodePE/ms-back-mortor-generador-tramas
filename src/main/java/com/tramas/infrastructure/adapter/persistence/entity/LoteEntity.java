package com.tramas.infrastructure.adapter.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("Control.Lotes")
public record LoteEntity(
        @Id
        @Column("LoteId")
        Long loteId,
        
        @Column("NumeroLote")
        String numeroLote,
        
        @Column("FechaCreacion")
        LocalDateTime fechaCreacion,
        
        @Column("UsuarioCreacion")
        String usuarioCreacion,
        
        @Column("EstadoLote")
        String estadoLote,
        
        @Column("TipoLote")
        String tipoLote,
        
        @Column("ArchivoOrigen")
        String archivoOrigen,
        
        @Column("TotalRegistros")
        Integer totalRegistros,
        
        @Column("RegistrosProcesados")
        Integer registrosProcesados,
        
        @Column("RegistrosConError")
        Integer registrosConError,
        
        @Column("RegistrosValidos")
        Integer registrosValidos,
        
        @Column("FechaInicioProceso")
        LocalDateTime fechaInicioProceso,
        
        @Column("FechaFinProceso")
        LocalDateTime fechaFinProceso,
        
        @Column("Observaciones")
        String observaciones,
        
        @Column("RutaArchivo")
        String rutaArchivo,
        
        @Column("FechaModificacion")
        LocalDateTime fechaModificacion,
        
        @Column("UsuarioModificacion")
        String usuarioModificacion
) {}