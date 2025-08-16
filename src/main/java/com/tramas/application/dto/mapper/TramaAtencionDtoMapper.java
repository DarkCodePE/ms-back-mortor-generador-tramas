package com.tramas.application.dto.mapper;

import com.tramas.application.dto.TramaAtencionDto;
import com.tramas.domain.model.EstadoRegistro;
import com.tramas.domain.model.TramaAtencion;

public class TramaAtencionDtoMapper {
    
    public static TramaAtencionDto toDto(TramaAtencion trama) {
        if (trama == null) return null;
        
        return new TramaAtencionDto(
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
    
    public static TramaAtencion toDomain(TramaAtencionDto dto) {
        if (dto == null) return null;
        
        return new TramaAtencion(
                dto.stagingId(),
                dto.loteId(),
                dto.numeroLinea(),
                dto.codNumPagoSita(),
                dto.tipoDocPago(),
                dto.numDocPago(),
                dto.fechaEmision(),
                dto.codigoIpress(),
                dto.codigoAfiliado(),
                dto.numeroHistoriaClinica(),
                dto.tipoAfiliacion(),
                dto.codigoAtencion(),
                dto.tipoDocumentoPaciente(),
                dto.numeroDocumentoPaciente(),
                dto.apellidosNombres(),
                dto.tipoDocAutorizador(),
                dto.numDocAutorizador(),
                dto.segundoDocAutorizador(),
                dto.codigoAutorizacion(),
                dto.tipoCobertura(),
                dto.subtipoCobertura(),
                dto.fechaAtencion(),
                dto.fechaSalida(),
                dto.codigoDxPrincipal(),
                dto.codigoDxSecundario1(),
                dto.codigoDxSecundario2(),
                dto.tipoPersonalResponsable(),
                dto.codigoPersonalResponsable(),
                dto.tipoDocPersonalResponsable(),
                dto.numDocPersonalResponsable(),
                dto.nombrePersonalResponsable(),
                dto.montoCobertura(),
                dto.montoSalud(),
                dto.tipoHospitalizacion(),
                dto.fechaIngresoHospital(),
                dto.fechaEgreso(),
                dto.tipoEgresoHospitalario(),
                EstadoRegistro.fromString(dto.estadoRegistro()),
                dto.fechaIngresoRegistro(),
                dto.fechaProcesamiento(),
                dto.usuarioProcesamiento(),
                dto.observacionesValidacion(),
                dto.erroresValidacion(),
                dto.datosOriginales()
        );
    }
}