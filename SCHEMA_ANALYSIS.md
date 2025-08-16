# CRITICAL SCHEMA MISMATCH ANALYSIS & RESOLUTION

## PROBLEM IDENTIFICATION

### Root Cause
The application entities are mapped to inconsistent database table and column naming conventions, causing `Invalid object name` runtime errors.

### Evidence Found

#### Table Naming Inconsistencies
- **FIXED**: Changed PascalCase schema (`Catalogo.MecanismosPago`) to lowercase (`catalogo.mecanismos_pago`)
- **Status**: ✅ All 6 PascalCase entities corrected to lowercase

#### Column Naming Patterns Identified

1. **PascalCase Columns** (Used in legacy entities - LIKELY INCORRECT):
   ```java
   @Column("AseguradoId")        // Should be: asegurado_id
   @Column("CodigoAsegurado")    // Should be: codigo_asegurado
   @Column("TipoDocumento")      // Should be: tipo_documento
   ```

2. **snake_case Columns** (Used in newer entities - CORRECT):
   ```java
   @Column("id_tipo_doc_autorizacion")  // ✅ CORRECT
   @Column("descripcion")               // ✅ CORRECT
   @Column("fecha_creacion")            // ✅ CORRECT
   ```

3. **UPPERCASE Columns** (TEDEF specification - CORRECT):
   ```java
   @Column("CODNUMPAGOSITA")     // ✅ CORRECT - TEDEF spec
   @Column("TIPODOCPAG")         // ✅ CORRECT - TEDEF spec
   ```

## RESOLUTION STRATEGY

### Phase 1: COMPLETED ✅
- Fixed table schema naming from `Catalogo.*` to `catalogo.*`
- Compilation successful

### Phase 2: CRITICAL - Column Mapping Standardization
Need to determine actual database column names for:

#### Entities with PascalCase columns (HIGH RISK):
1. `AseguradoEntity` - 12 PascalCase columns
2. `ProfesionalEntity` - 6 PascalCase columns  
3. `MecanismoPagoEntity` - 3 PascalCase columns
4. `MonedaEntity` - 3 PascalCase columns
5. `ClasificacionServicioEntity` - PascalCase columns
6. `MotivoNotaEntity` - PascalCase columns

#### Entities with correct naming (LOW RISK):
1. `TramaAtencionEntity` - Mixed (PascalCase + UPPERCASE TEDEF)
2. `TipoDocAutorizacionEntity` - snake_case ✅
3. `TipoHospitalizacionEntity` - snake_case ✅
4. All other `tipo_*` entities - snake_case ✅

### Phase 3: IMMEDIATE ACTIONS REQUIRED

1. **Database Schema Validation**: Connect to actual database and verify column names
2. **Column Mapping Correction**: Update entities with incorrect column mappings  
3. **Integration Test**: Verify all repository queries work with corrected mappings
4. **Runtime Validation**: Add validation to prevent future schema mismatches

## RISK ASSESSMENT

- **CRITICAL**: 6 entities with PascalCase columns will fail at runtime
- **HIGH**: Catalog queries (`MecanismosPago`, `Profesionales`) are core functionality
- **MEDIUM**: Some entities may work by accident due to R2DBC naming strategies

## NEXT STEPS

1. Test database connectivity with corrected table names
2. Identify actual column names in database
3. Standardize all column mappings to match database schema
4. Update integration tests to validate correct mappings