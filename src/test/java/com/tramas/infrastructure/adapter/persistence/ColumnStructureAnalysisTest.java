package com.tramas.infrastructure.adapter.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.test.StepVerifier;

/**
 * Test to analyze actual column structures in the existing tables
 * to correct entity column mappings.
 */
@SpringBootTest
class ColumnStructureAnalysisTest {

    @Autowired
    private DatabaseClient databaseClient;

    @Test
    void analizarColumnasCodigoRubro() {
        // Analyze CodigoRubro table structure (used by multiple entities)
        String columnQuery = """
            SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE, CHARACTER_MAXIMUM_LENGTH
            FROM INFORMATION_SCHEMA.COLUMNS 
            WHERE TABLE_SCHEMA = 'Catalogo' 
            AND TABLE_NAME = 'CodigoRubro'
            ORDER BY ORDINAL_POSITION
            """;

        StepVerifier.create(
                databaseClient.sql(columnQuery)
                        .fetch()
                        .all()
                        .doOnNext(result -> System.out.println("CodigoRubro column: " + result))
        ).thenConsumeWhile(result -> {
            System.out.println("CodigoRubro COLUMN: " + result);
            return true;
        }).verifyComplete();
    }

    @Test
    void analizarColumnasFiliacionPaciente() {
        // Analyze FiliacionPaciente table structure
        String columnQuery = """
            SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE, CHARACTER_MAXIMUM_LENGTH
            FROM INFORMATION_SCHEMA.COLUMNS 
            WHERE TABLE_SCHEMA = 'Catalogo' 
            AND TABLE_NAME = 'FiliacionPaciente'
            ORDER BY ORDINAL_POSITION
            """;

        StepVerifier.create(
                databaseClient.sql(columnQuery)
                        .fetch()
                        .all()
                        .doOnNext(result -> System.out.println("FiliacionPaciente column: " + result))
        ).thenConsumeWhile(result -> {
            System.out.println("FiliacionPaciente COLUMN: " + result);
            return true;
        }).verifyComplete();
    }

    @Test
    void analizarColumnasIpress() {
        // Analyze Ipress table structure
        String columnQuery = """
            SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE, CHARACTER_MAXIMUM_LENGTH
            FROM INFORMATION_SCHEMA.COLUMNS 
            WHERE TABLE_SCHEMA = 'Catalogo' 
            AND TABLE_NAME = 'Ipress'
            ORDER BY ORDINAL_POSITION
            """;

        StepVerifier.create(
                databaseClient.sql(columnQuery)
                        .fetch()
                        .all()
                        .doOnNext(result -> System.out.println("Ipress column: " + result))
        ).thenConsumeWhile(result -> {
            System.out.println("Ipress COLUMN: " + result);
            return true;
        }).verifyComplete();
    }

    @Test
    void testQueryDirectaCodigoRubro() {
        // Test direct query to CodigoRubro to see actual data and column names
        String directQuery = "SELECT TOP 3 * FROM Catalogo.CodigoRubro";

        StepVerifier.create(
                databaseClient.sql(directQuery)
                        .fetch()
                        .all()
                        .doOnNext(result -> System.out.println("CodigoRubro DATA: " + result))
                        .doOnError(error -> System.err.println("CodigoRubro query error: " + error.getMessage()))
        ).thenConsumeWhile(result -> {
            System.out.println("CodigoRubro RECORD: " + result);
            return true;
        }).verifyComplete();
    }

    @Test
    void testQueryDirectaFiliacionPaciente() {
        // Test direct query to FiliacionPaciente
        String directQuery = "SELECT TOP 3 * FROM Catalogo.FiliacionPaciente";

        StepVerifier.create(
                databaseClient.sql(directQuery)
                        .fetch()
                        .all()
                        .doOnNext(result -> System.out.println("FiliacionPaciente DATA: " + result))
                        .doOnError(error -> System.err.println("FiliacionPaciente query error: " + error.getMessage()))
        ).thenConsumeWhile(result -> {
            System.out.println("FiliacionPaciente RECORD: " + result);
            return true;
        }).verifyComplete();
    }
}