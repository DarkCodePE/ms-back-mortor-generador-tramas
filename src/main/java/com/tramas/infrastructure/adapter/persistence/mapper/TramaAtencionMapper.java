package com.tramas.infrastructure.adapter.persistence.mapper;

import com.tramas.domain.model.EstadoRegistro;
import com.tramas.domain.model.TramaAtencion;
import com.tramas.infrastructure.adapter.persistence.entity.TramaAtencionEntity;

public class TramaAtencionMapper {
    
    public static TramaAtencionEntity toEntity(TramaAtencion trama) {
        if (trama == null) return null;
        
        return new TramaAtencionEntity(
                trama.stagingId(),
                trama.loteId(),
                trama.numeroLinea(),
                trama.codNumPagoSita(),
                trama.tipoDocPago(),
                trama.numDocPago(),
                trama.fechaEmision(),
                trama.codigoIpress(),
                trama.codigoAfiliado(),
                trama.numeroHistoriaClinica(),
                trama.tipoAfiliacion(),
                trama.codigoAtencion(),
                trama.tipoDocumentoPaciente(),
                trama.numeroDocumentoPaciente(),
                trama.apellidosNombres(),
                trama.tipoDocAutorizador(),
                trama.numDocAutorizador(),
                trama.segundoDocAutorizador(),
                trama.codigoAutorizacion(),
                trama.tipoCobertura(),
                trama.subtipoCobertura(),
                trama.fechaAtencion(),
                trama.fechaSalida(),
                trama.codigoDxPrincipal(),
                trama.codigoDxSecundario1(),
                trama.codigoDxSecundario2(),
                trama.tipoPersonalResponsable(),
                trama.codigoPersonalResponsable(),
                trama.tipoDocPersonalResponsable(),
                trama.numDocPersonalResponsable(),
                trama.nombrePersonalResponsable(),
                trama.montoCobertura(),
                trama.montoSalud(),
                trama.tipoHospitalizacion(),
                trama.fechaIngresoHospital(),
                trama.fechaEgreso(),
                trama.tipoEgresoHospitalario(),
                trama.estadoRegistro() != null ? trama.estadoRegistro().getValor() : null,
                trama.fechaIngresoRegistro(),
                trama.fechaProcesamiento(),
                trama.usuarioProcesamiento(),
                trama.observacionesValidacion(),
                trama.erroresValidacion(),
                trama.datosOriginales()
        );
    }
    
    public static TramaAtencion toDomain(TramaAtencionEntity entity) {
        if (entity == null) return null;
        
        return new TramaAtencion(
                entity.stagingId(),
                entity.loteId(),
                entity.numeroLinea(),
                entity.codNumPagoSita(),
                entity.tipoDocPago(),
                entity.numDocPago(),
                entity.fechaEmision(),
                entity.codigoIpress(),
                entity.codigoAfiliado(),
                entity.numeroHistoriaClinica(),
                entity.tipoAfiliacion(),
                entity.codigoAtencion(),
                entity.tipoDocumentoPaciente(),
                entity.numeroDocumentoPaciente(),
                entity.apellidosNombres(),
                entity.tipoDocAutorizador(),
                entity.numDocAutorizador(),
                entity.segundoDocAutorizador(),
                entity.codigoAutorizacion(),
                entity.tipoCobertura(),
                entity.subtipoCobertura(),
                entity.fechaAtencion(),
                entity.fechaSalida(),
                entity.codigoDxPrincipal(),
                entity.codigoDxSecundario1(),
                entity.codigoDxSecundario2(),
                entity.tipoPersonalResponsable(),
                entity.codigoPersonalResponsable(),
                entity.tipoDocPersonalResponsable(),
                entity.numDocPersonalResponsable(),
                entity.nombrePersonalResponsable(),
                entity.montoCobertura(),
                entity.montoSalud(),
                entity.tipoHospitalizacion(),
                entity.fechaIngreso(),
                entity.fechaEgreso(),
                entity.tipoEgresoHospitalario(),
                EstadoRegistro.fromString(entity.estadoRegistro()),
                entity.fechaIngresoRegistro(),
                entity.fechaProcesamiento(),
                entity.usuarioProcesamiento(),
                entity.observacionesValidacion(),
                entity.erroresValidacion(),
                entity.datosOriginales()
        );
    }
}