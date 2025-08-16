package com.tramas.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TramaAtencionDto(
        Long stagingId,
        
        @NotNull(message = "El lote ID es obligatorio")
        Long loteId,
        
        @NotNull(message = "El número de línea es obligatorio")
        Integer numeroLinea,
        
        // Campos TRAMA_ATENCION
        @Size(max = 20, message = "CODNUMPAGOSITA no puede exceder 20 caracteres")
        String codNumPagoSita,
        
        @Size(max = 2, message = "TIPODOCPAG no puede exceder 2 caracteres")
        String tipoDocPago,
        
        @Size(max = 20, message = "NUMDOCPAG no puede exceder 20 caracteres")
        String numDocPago,
        
        @Size(max = 8, message = "FECEMI debe tener formato YYYYMMDD")
        String fechaEmision,
        
        @Size(max = 8, message = "CODIPRESS no puede exceder 8 caracteres")
        String codigoIpress,
        
        @Size(max = 22, message = "CODAFILPCNT no puede exceder 22 caracteres")
        String codigoAfiliado,
        
        @Size(max = 15, message = "NUMHISCLIN no puede exceder 15 caracteres")
        String numeroHistoriaClinica,
        
        @Size(max = 1, message = "TIPOAFILPCNT no puede exceder 1 caracter")
        String tipoAfiliacion,
        
        @Size(max = 20, message = "CODATENCION no puede exceder 20 caracteres")
        String codigoAtencion,
        
        @Size(max = 1, message = "TIPODOCIDEN no puede exceder 1 caracter")
        String tipoDocumentoPaciente,
        
        @Size(max = 20, message = "NUMDOCIDEN no puede exceder 20 caracteres")
        String numeroDocumentoPaciente,
        
        @Size(max = 100, message = "APENOM no puede exceder 100 caracteres")
        String apellidosNombres,
        
        @Size(max = 2, message = "TIPODOCAUT no puede exceder 2 caracteres")
        String tipoDocAutorizador,
        
        @Size(max = 20, message = "NUMDOCAUT no puede exceder 20 caracteres")
        String numDocAutorizador,
        
        @Size(max = 1, message = "SGNDODOCAUT no puede exceder 1 caracter")
        String segundoDocAutorizador,
        
        @Size(max = 40, message = "CODAUT no puede exceder 40 caracteres")
        String codigoAutorizacion,
        
        @Size(max = 1, message = "TIPOCOBERT no puede exceder 1 caracter")
        String tipoCobertura,
        
        @Size(max = 3, message = "SUBTIPOCOBERT no puede exceder 3 caracteres")
        String subtipoCobertura,
        
        @Size(max = 8, message = "FECATN debe tener formato YYYYMMDD")
        String fechaAtencion,
        
        @Size(max = 8, message = "FECSALIDA debe tener formato YYYYMMDD")
        String fechaSalida,
        
        @Size(max = 10, message = "CODDXPPAL no puede exceder 10 caracteres")
        String codigoDxPrincipal,
        
        @Size(max = 10, message = "CODDXSEC1 no puede exceder 10 caracteres")
        String codigoDxSecundario1,
        
        @Size(max = 10, message = "CODDXSEC2 no puede exceder 10 caracteres")
        String codigoDxSecundario2,
        
        @Size(max = 2, message = "TIPOPRNLRESP no puede exceder 2 caracteres")
        String tipoPersonalResponsable,
        
        @Size(max = 15, message = "CODPRNLRESP no puede exceder 15 caracteres")
        String codigoPersonalResponsable,
        
        @Size(max = 1, message = "TIPODOCPRNL no puede exceder 1 caracter")
        String tipoDocPersonalResponsable,
        
        @Size(max = 20, message = "NUMDOCPRNL no puede exceder 20 caracteres")
        String numDocPersonalResponsable,
        
        @Size(max = 100, message = "NOMBREDPRNL no puede exceder 100 caracteres")
        String nombrePersonalResponsable,
        
        BigDecimal montoCobertura,
        BigDecimal montoSalud,
        
        @Size(max = 1, message = "TIPOHOSP no puede exceder 1 caracter")
        String tipoHospitalizacion,
        
        @Size(max = 8, message = "FECING debe tener formato YYYYMMDD")
        String fechaIngresoHospital,
        
        @Size(max = 8, message = "FECEGR debe tener formato YYYYMMDD")
        String fechaEgreso,
        
        @Size(max = 2, message = "TIPOEGRHOSP no puede exceder 2 caracteres")
        String tipoEgresoHospitalario,
        
        // Campos de control
        String estadoRegistro,
        
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime fechaIngresoRegistro,
        
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime fechaProcesamiento,
        
        String usuarioProcesamiento,
        String observacionesValidacion,
        String erroresValidacion,
        String datosOriginales
) {}