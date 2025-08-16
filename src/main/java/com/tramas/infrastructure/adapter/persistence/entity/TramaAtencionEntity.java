package com.tramas.infrastructure.adapter.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table("Staging.TramaAtencion")
public record TramaAtencionEntity(
        @Id
        @Column("StagingId")
        Long stagingId,
        
        @Column("LoteId")
        Long loteId,
        
        @Column("NumeroLinea")
        Integer numeroLinea,
        
        // Campos de la TRAMA_ATENCION
        @Column("CODNUMPAGOSITA")
        String codNumPagoSita,
        
        @Column("TIPODOCPAG")
        String tipoDocPago,
        
        @Column("NUMDOCPAG")
        String numDocPago,
        
        @Column("FECEMI")
        String fechaEmision,
        
        @Column("CODIPRESS")
        String codigoIpress,
        
        @Column("CODAFILPCNT")
        String codigoAfiliado,
        
        @Column("NUMHISCLIN")
        String numeroHistoriaClinica,
        
        @Column("TIPOAFILPCNT")
        String tipoAfiliacion,
        
        @Column("CODATENCION")
        String codigoAtencion,
        
        @Column("TIPODOCIDEN")
        String tipoDocumentoPaciente,
        
        @Column("NUMDOCIDEN")
        String numeroDocumentoPaciente,
        
        @Column("APENOM")
        String apellidosNombres,
        
        @Column("TIPODOCAUT")
        String tipoDocAutorizador,
        
        @Column("NUMDOCAUT")
        String numDocAutorizador,
        
        @Column("SGNDODOCAUT")
        String segundoDocAutorizador,
        
        @Column("CODAUT")
        String codigoAutorizacion,
        
        @Column("TIPOCOBERT")
        String tipoCobertura,
        
        @Column("SUBTIPOCOBERT")
        String subtipoCobertura,
        
        @Column("FECATN")
        String fechaAtencion,
        
        @Column("FECSALIDA")
        String fechaSalida,
        
        @Column("CODDXPPAL")
        String codigoDxPrincipal,
        
        @Column("CODDXSEC1")
        String codigoDxSecundario1,
        
        @Column("CODDXSEC2")
        String codigoDxSecundario2,
        
        @Column("TIPOPRNLRESP")
        String tipoPersonalResponsable,
        
        @Column("CODPRNLRESP")
        String codigoPersonalResponsable,
        
        @Column("TIPODOCPRNL")
        String tipoDocPersonalResponsable,
        
        @Column("NUMDOCPRNL")
        String numDocPersonalResponsable,
        
        @Column("NOMBREDPRNL")
        String nombrePersonalResponsable,
        
        @Column("MTOCOBERT")
        BigDecimal montoCobertura,
        
        @Column("MTOSALUD")
        BigDecimal montoSalud,
        
        @Column("TIPOHOSP")
        String tipoHospitalizacion,
        
        @Column("FECING")
        String fechaIngreso,
        
        @Column("FECEGR")
        String fechaEgreso,
        
        @Column("TIPOEGRHOSP")
        String tipoEgresoHospitalario,
        
        // Campos de control
        @Column("EstadoRegistro")
        String estadoRegistro,
        
        @Column("FechaIngreso")
        LocalDateTime fechaIngresoRegistro,
        
        @Column("FechaProcesamiento")
        LocalDateTime fechaProcesamiento,
        
        @Column("UsuarioProcesamiento")
        String usuarioProcesamiento,
        
        @Column("ObservacionesValidacion")
        String observacionesValidacion,
        
        @Column("ErroresValidacion")
        String erroresValidacion,
        
        @Column("DatosOriginales")
        String datosOriginales
) {}