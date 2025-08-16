package com.tramas.infrastructure.adapter.persistence;

import com.tramas.infrastructure.adapter.persistence.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.test.StepVerifier;

/**
 * Integration test to validate that our updated entity mappings
 * work correctly with the actual database schema.
 */
@SpringBootTest
class EntityMappingValidationTest {

    @Autowired
    private DatabaseClient databaseClient;
    
    @Autowired
    private MecanismoPagoR2dbcRepository mecanismoPagoRepository;
    
    @Autowired
    private MonedaR2dbcRepository monedaRepository;
    
    @Autowired
    private ClasificacionServicioR2dbcRepository clasificacionServicioRepository;
    
    @Autowired
    private ProfesionalR2dbcRepository profesionalRepository;

    @Test
    void testMecanismoPagoEntityMapping() {
        // Test that the MecanismoPagoEntity mapping works with discriminator
        System.out.println("Testing MecanismoPagoEntity mapping...");
        
        StepVerifier.create(
                mecanismoPagoRepository.findAllActive()
                        .doOnNext(entity -> {
                            System.out.println("MecanismoPago found: " + entity);
                            System.out.println("  Codigo: " + entity.codigo());
                            System.out.println("  Descripcion: " + entity.descripcion());
                            System.out.println("  CategoriaPrincipal: " + entity.categoriaPrincipal());
                            System.out.println("  Activo: " + entity.activo());
                        })
                        .doOnError(error -> System.err.println("MecanismoPago mapping error: " + error.getMessage()))
        ).thenConsumeWhile(entity -> {
            // Validate entity structure
            return entity.codigo() != null && entity.descripcion() != null;
        }).verifyComplete();
    }

    @Test
    void testMonedaEntityMapping() {
        // Test that the MonedaEntity mapping works with discriminator
        System.out.println("Testing MonedaEntity mapping...");
        
        StepVerifier.create(
                monedaRepository.findAllActive()
                        .doOnNext(entity -> {
                            System.out.println("Moneda found: " + entity);
                            System.out.println("  Codigo: " + entity.codigo());
                            System.out.println("  Descripcion: " + entity.descripcion());
                            System.out.println("  CategoriaPrincipal: " + entity.categoriaPrincipal());
                        })
                        .doOnError(error -> System.err.println("Moneda mapping error: " + error.getMessage()))
        ).thenConsumeWhile(entity -> {
            return entity.codigo() != null && entity.descripcion() != null;
        }).verifyComplete();
    }

    @Test
    void testClasificacionServicioEntityMapping() {
        // Test that the ClasificacionServicioEntity mapping works with discriminator
        System.out.println("Testing ClasificacionServicioEntity mapping...");
        
        StepVerifier.create(
                clasificacionServicioRepository.findAllActive()
                        .doOnNext(entity -> {
                            System.out.println("ClasificacionServicio found: " + entity);
                            System.out.println("  Codigo: " + entity.codigo());
                            System.out.println("  Descripcion: " + entity.descripcion());
                            System.out.println("  CategoriaPrincipal: " + entity.categoriaPrincipal());
                        })
                        .doOnError(error -> System.err.println("ClasificacionServicio mapping error: " + error.getMessage()))
        ).thenConsumeWhile(entity -> {
            return entity.codigo() != null && entity.descripcion() != null;
        }).verifyComplete();
    }

    @Test
    void testProfesionalEntityMapping() {
        // Test that the ProfesionalEntity mapping works with FiliacionPaciente table
        System.out.println("Testing ProfesionalEntity mapping...");
        
        StepVerifier.create(
                profesionalRepository.findAllActiveProfesionales()
                        .doOnNext(entity -> {
                            System.out.println("Profesional found: " + entity);
                            System.out.println("  Id: " + entity.id());
                            System.out.println("  NombresCompletos: " + entity.nombresCompletos());
                            System.out.println("  TipoDocumento: " + entity.tipoDocumento());
                            System.out.println("  NumeroDocumento: " + entity.numeroDocumento());
                        })
                        .doOnError(error -> System.err.println("Profesional mapping error: " + error.getMessage()))
        ).thenConsumeWhile(entity -> {
            return entity.id() != null && entity.nombresCompletos() != null;
        }).verifyComplete();
    }

    @Test
    void testDirectCodigoRubroQuery() {
        // Test direct query to CodigoRubro to see actual data structure
        System.out.println("Testing direct CodigoRubro query...");
        
        String directQuery = "SELECT TOP 5 * FROM Catalogo.CodigoRubro ORDER BY Codigo";
        
        StepVerifier.create(
                databaseClient.sql(directQuery)
                        .fetch()
                        .all()
                        .doOnNext(result -> System.out.println("CodigoRubro record: " + result))
                        .doOnError(error -> System.err.println("CodigoRubro query error: " + error.getMessage()))
        ).thenConsumeWhile(result -> {
            System.out.println("CodigoRubro data: " + result);
            return true;
        }).verifyComplete();
    }

    @Test
    void testDirectFiliacionPacienteQuery() {
        // Test direct query to FiliacionPaciente to see actual data structure
        System.out.println("Testing direct FiliacionPaciente query...");
        
        String directQuery = "SELECT TOP 5 * FROM Catalogo.FiliacionPaciente ORDER BY Id";
        
        StepVerifier.create(
                databaseClient.sql(directQuery)
                        .fetch()
                        .all()
                        .doOnNext(result -> System.out.println("FiliacionPaciente record: " + result))
                        .doOnError(error -> System.err.println("FiliacionPaciente query error: " + error.getMessage()))
        ).thenConsumeWhile(result -> {
            System.out.println("FiliacionPaciente data: " + result);
            return true;
        }).verifyComplete();
    }
}