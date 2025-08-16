package com.tramas.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record TramaAtencion(
        Long stagingId,
        Long loteId,
        Integer numeroLinea,
        
        // Campos de la TRAMA_ATENCION según especificación
        String codNumPagoSita,        // Campo 1
        String tipoDocPago,           // Campo 2
        String numDocPago,            // Campo 3
        String fechaEmision,          // Campo 4 (YYYYMMDD)
        String codigoIpress,          // Campo 5
        String codigoAfiliado,        // Campo 6
        String numeroHistoriaClinica, // Campo 7
        String tipoAfiliacion,        // Campo 8
        String codigoAtencion,        // Campo 9
        String tipoDocumentoPaciente, // Campo 10
        String numeroDocumentoPaciente, // Campo 11
        String apellidosNombres,      // Campo 12
        String tipoDocAutorizador,    // Campo 13
        String numDocAutorizador,     // Campo 14
        String segundoDocAutorizador, // Campo 15
        String codigoAutorizacion,    // Campo 16
        String tipoCobertura,         // Campo 17
        String subtipoCobertura,      // Campo 18
        String fechaAtencion,         // Campo 19 (YYYYMMDD)
        String fechaSalida,           // Campo 20 (YYYYMMDD)
        String codigoDxPrincipal,     // Campo 21
        String codigoDxSecundario1,   // Campo 22
        String codigoDxSecundario2,   // Campo 23
        String tipoPersonalResponsable, // Campo 24
        String codigoPersonalResponsable, // Campo 25
        String tipoDocPersonalResponsable, // Campo 26
        String numDocPersonalResponsable,  // Campo 27
        String nombrePersonalResponsable,  // Campo 28
        BigDecimal montoCobertura,    // Campo 29
        BigDecimal montoSalud,        // Campo 30
        String tipoHospitalizacion,   // Campo 31
        String fechaIngresoHospital,  // Campo 32 (YYYYMMDD)
        String fechaEgreso,           // Campo 33 (YYYYMMDD)
        String tipoEgresoHospitalario, // Campo 34
        
        // Campos de control y auditoría
        EstadoRegistro estadoRegistro,
        LocalDateTime fechaIngresoRegistro,
        LocalDateTime fechaProcesamiento,
        String usuarioProcesamiento,
        String observacionesValidacion,
        String erroresValidacion,
        String datosOriginales
) {
    public TramaAtencion {
        Objects.requireNonNull(loteId, "Lote ID cannot be null");
        Objects.requireNonNull(numeroLinea, "Numero linea cannot be null");
        
        if (estadoRegistro == null) estadoRegistro = EstadoRegistro.PENDIENTE;
        if (fechaIngresoRegistro == null) fechaIngresoRegistro = LocalDateTime.now();
    }
    
    public static TramaAtencion create(Long loteId, Integer numeroLinea) {
        return new TramaAtencion(
                null, // stagingId
                loteId, // loteId
                numeroLinea, // numeroLinea
                // 34 campos TRAMA_ATENCION
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null,
                // Campos de control
                EstadoRegistro.PENDIENTE,
                LocalDateTime.now(),
                null,
                null,
                null,
                null,
                null
        );
    }
    
    public TramaAtencion withEstado(EstadoRegistro nuevoEstado) {
        return new TramaAtencion(
                stagingId, loteId, numeroLinea,
                codNumPagoSita, tipoDocPago, numDocPago, fechaEmision, codigoIpress,
                codigoAfiliado, numeroHistoriaClinica, tipoAfiliacion, codigoAtencion,
                tipoDocumentoPaciente, numeroDocumentoPaciente, apellidosNombres,
                tipoDocAutorizador, numDocAutorizador, segundoDocAutorizador,
                codigoAutorizacion, tipoCobertura, subtipoCobertura, fechaAtencion,
                fechaSalida, codigoDxPrincipal, codigoDxSecundario1, codigoDxSecundario2,
                tipoPersonalResponsable, codigoPersonalResponsable, tipoDocPersonalResponsable,
                numDocPersonalResponsable, nombrePersonalResponsable, montoCobertura,
                montoSalud, tipoHospitalizacion, fechaIngresoHospital, fechaEgreso,
                tipoEgresoHospitalario,
                nuevoEstado,
                fechaIngresoRegistro,
                LocalDateTime.now(),
                usuarioProcesamiento,
                observacionesValidacion,
                erroresValidacion,
                datosOriginales
        );
    }
    
    public TramaAtencion withValidacion(String observaciones, String errores) {
        return new TramaAtencion(
                stagingId, loteId, numeroLinea,
                codNumPagoSita, tipoDocPago, numDocPago, fechaEmision, codigoIpress,
                codigoAfiliado, numeroHistoriaClinica, tipoAfiliacion, codigoAtencion,
                tipoDocumentoPaciente, numeroDocumentoPaciente, apellidosNombres,
                tipoDocAutorizador, numDocAutorizador, segundoDocAutorizador,
                codigoAutorizacion, tipoCobertura, subtipoCobertura, fechaAtencion,
                fechaSalida, codigoDxPrincipal, codigoDxSecundario1, codigoDxSecundario2,
                tipoPersonalResponsable, codigoPersonalResponsable, tipoDocPersonalResponsable,
                numDocPersonalResponsable, nombrePersonalResponsable, montoCobertura,
                montoSalud, tipoHospitalizacion, fechaIngresoHospital, fechaEgreso,
                tipoEgresoHospitalario,
                estadoRegistro,
                fechaIngresoRegistro,
                fechaProcesamiento,
                usuarioProcesamiento,
                observaciones,
                errores,
                datosOriginales
        );
    }
}