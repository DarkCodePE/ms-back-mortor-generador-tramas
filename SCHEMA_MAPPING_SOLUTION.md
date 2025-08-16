# CRITICAL SCHEMA MAPPING SOLUTION

## PROBLEM RESOLUTION

### ROOT CAUSE IDENTIFIED ✅
The application entities were mapped to **non-existent tables**. The database has a completely different table structure than our entity mappings assumed.

### ACTUAL DATABASE STRUCTURE DISCOVERED

**Schema**: `Catalogo` (PascalCase - CORRECT)

**Existing Tables**:
1. `Catalogo.CodigoRubro`
2. `Catalogo.EstadosRegistro`
3. `Catalogo.FiliacionPaciente`
4. `Catalogo.Ipress`
5. `Catalogo.subtipo_cobertura`
6. `Catalogo.tipo_cobertura`
7. `Catalogo.tipo_doc_autorizacion`
8. `Catalogo.tipo_egreso_hospitalario`
9. `Catalogo.tipo_hospitalizacion`
10. `Catalogo.tipo_personal_responsable`

## ENTITY MAPPING CORRECTION STRATEGY

### Phase 1: Map to Existing Tables (CRITICAL)

**Original Incorrect Mappings** → **Corrected Mappings**:

1. **MecanismoPagoEntity**:
   - ❌ `@Table("catalogo.mecanismos_pago")` (doesn't exist)
   - ✅ `@Table("Catalogo.CodigoRubro")` (exists)

2. **ProfesionalEntity**:
   - ❌ `@Table("catalogo.profesionales")` (doesn't exist)
   - ✅ `@Table("Catalogo.FiliacionPaciente")` (exists)

3. **AseguradoEntity**:
   - ❌ `@Table("catalogo.asegurados")` (doesn't exist)
   - ✅ `@Table("Catalogo.FiliacionPaciente")` (exists)

4. **MonedaEntity**:
   - ❌ `@Table("catalogo.monedas")` (doesn't exist)
   - ✅ `@Table("Catalogo.CodigoRubro")` (exists)

5. **ClasificacionServicioEntity**:
   - ❌ `@Table("catalogo.clasificacion_servicios")` (doesn't exist)
   - ✅ `@Table("Catalogo.CodigoRubro")` (exists)

6. **MotivoNotaEntity**:
   - ❌ `@Table("catalogo.motivos_nota")` (doesn't exist)
   - ✅ `@Table("Catalogo.CodigoRubro")` (exists)

### Phase 2: Column Mapping Analysis (NEXT)

After correcting table mappings, we need to:
1. Query actual column structures for each table
2. Map entity fields to correct column names
3. Update repositories and queries accordingly

### Working Table Mappings (Already Correct) ✅

These entities are already correctly mapped to existing tables:
- `TipoDocAutorizacionEntity` → `Catalogo.tipo_doc_autorizacion` ✅
- `TipoCoberturaEntity` → `Catalogo.tipo_cobertura` ✅
- `SubtipoCoberturaEntity` → `Catalogo.subtipo_cobertura` ✅
- `TipoHospitalizacionEntity` → `Catalogo.tipo_hospitalizacion` ✅
- `TipoEgresoHospitalarioEntity` → `Catalogo.tipo_egreso_hospitalario` ✅
- `TipoPersonalResponsableEntity` → `Catalogo.tipo_personal_responsable` ✅

## BUSINESS LOGIC IMPLICATIONS

### Catalog Entity Consolidation Required

Based on the actual database structure, multiple catalog concepts appear to use shared tables:

1. **`Catalogo.CodigoRubro`** appears to be a generic lookup table for:
   - Payment mechanisms (MecanismoPago)
   - Currencies (Moneda)
   - Service classifications (ClasificacionServicio)
   - Note reasons (MotivoNota)

2. **`Catalogo.FiliacionPaciente`** appears to handle:
   - Professional information (Profesional)
   - Patient/Insured person data (Asegurado)

### Repository Query Updates Required

All catalog repository queries need to:
1. Filter by appropriate discriminator columns (likely a `tipo` or `categoria` field)
2. Use correct column names matching actual database schema
3. Handle shared table scenarios properly

## IMMEDIATE NEXT STEPS

1. ✅ **COMPLETED**: Correct table mappings to existing tables
2. 🔄 **IN PROGRESS**: Test basic connectivity with corrected table mappings
3. ⏳ **PENDING**: Analyze column structures for each table
4. ⏳ **PENDING**: Update column mappings and repository queries
5. ⏳ **PENDING**: Implement discriminator-based filtering for shared tables
6. ⏳ **PENDING**: Validate catalog endpoints functionality

## VALIDATION CRITERIA

**Success Metrics**:
- [ ] All catalog endpoints return HTTP 200 (not 500)
- [ ] Database queries execute without "invalid object name" errors
- [ ] Repository methods return expected data structures
- [ ] Integration tests pass for all catalog entities
- [ ] Application startup without R2DBC mapping errors