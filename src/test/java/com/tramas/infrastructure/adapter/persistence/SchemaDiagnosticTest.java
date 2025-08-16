package com.tramas.infrastructure.adapter.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.test.StepVerifier;

/**
 * Diagnostic test to verify actual database schema structure
 * and identify column mapping issues.
 */
@SpringBootTest
class SchemaDiagnosticTest {

    @Autowired
    private DatabaseClient databaseClient;

    @Test
    void diagnosticarEsquemaCatalogo() {
        // Test 1: Verify catalog schema exists
        String schemaQuery = """
            SELECT SCHEMA_NAME 
            FROM INFORMATION_SCHEMA.SCHEMATA 
            WHERE SCHEMA_NAME = 'catalogo'
            """;

        StepVerifier.create(
                databaseClient.sql(schemaQuery)
                        .fetch()
                        .all()
                        .doOnNext(result -> System.out.println("Schema found: " + result))
        ).expectNextCount(1).verifyComplete();
    }

    @Test
    void diagnosticarTablasMecanismosPago() {
        // Test 2: Check if mecanismos_pago table exists
        String tableQuery = """
            SELECT TABLE_NAME, TABLE_SCHEMA
            FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_SCHEMA = 'catalogo' 
            AND TABLE_NAME LIKE '%mecanismo%'
            """;

        StepVerifier.create(
                databaseClient.sql(tableQuery)
                        .fetch()
                        .all()
                        .doOnNext(result -> System.out.println("Mecanismos table: " + result))
        ).expectNextCount(1).verifyComplete();
    }

    @Test
    void diagnosticarColumnasMecanismosPago() {
        // Test 3: Get actual column names for mecanismos_pago
        String columnQuery = """
            SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE
            FROM INFORMATION_SCHEMA.COLUMNS 
            WHERE TABLE_SCHEMA = 'catalogo' 
            AND TABLE_NAME = 'mecanismos_pago'
            ORDER BY ORDINAL_POSITION
            """;

        StepVerifier.create(
                databaseClient.sql(columnQuery)
                        .fetch()
                        .all()
                        .doOnNext(result -> System.out.println("MecanismosPago column: " + result))
        ).expectNextMatches(result -> result.containsKey("COLUMN_NAME"))
        .verifyComplete();
    }

    @Test
    void diagnosticarTablasProfesionales() {
        // Test 4: Check if profesionales table exists
        String tableQuery = """
            SELECT TABLE_NAME, TABLE_SCHEMA
            FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_SCHEMA = 'catalogo' 
            AND TABLE_NAME LIKE '%profesional%'
            """;

        StepVerifier.create(
                databaseClient.sql(tableQuery)
                        .fetch()
                        .all()
                        .doOnNext(result -> System.out.println("Profesionales table: " + result))
        ).expectNextCount(1).verifyComplete();
    }

    @Test
    void diagnosticarColumnasProfesionales() {
        // Test 5: Get actual column names for profesionales
        String columnQuery = """
            SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE
            FROM INFORMATION_SCHEMA.COLUMNS 
            WHERE TABLE_SCHEMA = 'catalogo' 
            AND TABLE_NAME = 'profesionales'
            ORDER BY ORDINAL_POSITION
            """;

        StepVerifier.create(
                databaseClient.sql(columnQuery)
                        .fetch()
                        .all()
                        .doOnNext(result -> System.out.println("Profesionales column: " + result))
        ).expectNextMatches(result -> result.containsKey("COLUMN_NAME"))
        .verifyComplete();
    }

    @Test
    void listarTodasLasTablasCatalogo() {
        // Test 6: List ALL tables in catalogo schema to see what actually exists
        String allTablesQuery = """
            SELECT TABLE_NAME, TABLE_SCHEMA
            FROM INFORMATION_SCHEMA.TABLES 
            WHERE TABLE_SCHEMA = 'catalogo'
            ORDER BY TABLE_NAME
            """;

        StepVerifier.create(
                databaseClient.sql(allTablesQuery)
                        .fetch()
                        .all()
                        .doOnNext(result -> System.out.println("ACTUAL CATALOG TABLE: " + result))
        ).thenConsumeWhile(result -> {
            System.out.println("ACTUAL CATALOG TABLE: " + result);
            return true;
        })
        .verifyComplete();
    }

    @Test
    void listarTodosLosEsquemas() {
        // Test 7: List ALL schemas to understand database structure
        String allSchemasQuery = """
            SELECT SCHEMA_NAME 
            FROM INFORMATION_SCHEMA.SCHEMATA 
            ORDER BY SCHEMA_NAME
            """;

        StepVerifier.create(
                databaseClient.sql(allSchemasQuery)
                        .fetch()
                        .all()
                        .doOnNext(result -> System.out.println("AVAILABLE SCHEMA: " + result))
        ).expectNextCount(0)  // Allow any number of schemas
        .verifyComplete();
    }
}