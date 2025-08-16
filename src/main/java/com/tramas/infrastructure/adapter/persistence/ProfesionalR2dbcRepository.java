package com.tramas.infrastructure.adapter.persistence;

import com.tramas.infrastructure.adapter.persistence.entity.ProfesionalEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProfesionalR2dbcRepository extends ReactiveCrudRepository<ProfesionalEntity, Integer> {
    
    @Query("SELECT * FROM Catalogo.FiliacionPaciente WHERE TipoPersona = 'PROFESIONAL' AND TipoDocumento = :tipoDocumento AND NumeroDocumento = :numeroDocumento AND Estado = 1")
    Mono<ProfesionalEntity> findByTipoDocumentoAndNumeroDocumento(String tipoDocumento, String numeroDocumento);
    
    @Query("SELECT * FROM Catalogo.FiliacionPaciente WHERE TipoPersona = 'PROFESIONAL' AND NombresCompletos LIKE '%' + :nombres + '%' AND Estado = 1 ORDER BY NombresCompletos")
    Flux<ProfesionalEntity> findByNombresContainingIgnoreCase(String nombres);
    
    @Query("SELECT * FROM Catalogo.FiliacionPaciente WHERE TipoPersona = 'PROFESIONAL' AND NumeroColegiaturo = :numeroColegiaturo AND Estado = 1")
    Mono<ProfesionalEntity> findByNumeroColegiaturo(String numeroColegiaturo);
    
    @Query("SELECT * FROM Catalogo.FiliacionPaciente WHERE TipoPersona = 'PROFESIONAL' AND TipoProfesional = :tipoProfesional AND Estado = 1 ORDER BY NombresCompletos")
    Flux<ProfesionalEntity> findByTipoProfesionalOrderByNombres(String tipoProfesional);
    
    @Query("SELECT * FROM Catalogo.FiliacionPaciente WHERE TipoPersona = 'PROFESIONAL' AND Estado = 1 ORDER BY NombresCompletos")
    Flux<ProfesionalEntity> findAllActiveProfesionales();
}