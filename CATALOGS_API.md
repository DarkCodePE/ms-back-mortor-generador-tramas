# Catálogos API - TRAMAS Backend Service

## Resumen

Se han creado nuevos endpoints para gestionar todos los catálogos del sistema TRAMAS, basados en las tablas de catálogo definidas en el schema de base de datos.

## Endpoints Disponibles

### 🏥 Profesionales

**Obtener todos los profesionales**
```
GET /api/v1/catalogos/profesionales
Query Params:
  - nombre (opcional): Filtrar por nombre
  - tipoProfesional (opcional): Filtrar por tipo (01-99)
```

**Obtener profesional por ID**
```
GET /api/v1/catalogos/profesionales/{id}
```

**Buscar profesional por documento**
```
GET /api/v1/catalogos/profesionales/buscar-por-documento
Query Params:
  - tipoDoc: Tipo de documento
  - numeroDoc: Número de documento
```

### 🔧 Clasificaciones de Servicio

**Obtener clasificaciones**
```
GET /api/v1/catalogos/clasificaciones-servicio
Query Params:
  - nombre (opcional): Filtrar por nombre
```

**Obtener clasificación por código**
```
GET /api/v1/catalogos/clasificaciones-servicio/{codigo}
```

### 💰 Monedas

**Obtener monedas**
```
GET /api/v1/catalogos/monedas
Query Params:
  - nombre (opcional): Filtrar por nombre
```

**Obtener moneda por código**
```
GET /api/v1/catalogos/monedas/{codigo}
```

### 💳 Mecanismos de Pago

**Obtener mecanismos de pago**
```
GET /api/v1/catalogos/mecanismos-pago
Query Params:
  - nombre (opcional): Filtrar por nombre
  - subtipo (opcional): Filtrar por subtipo
```

**Obtener mecanismo por código**
```
GET /api/v1/catalogos/mecanismos-pago/{codigo}
```

### 📝 Motivos de Nota

**Obtener motivos de nota**
```
GET /api/v1/catalogos/motivos-nota
Query Params:
  - nombre (opcional): Filtrar por nombre
  - tipoNota (opcional): CREDITO o DEBITO
```

**Obtener motivo por código**
```
GET /api/v1/catalogos/motivos-nota/{codigo}
```

### 📋 Endpoints de Apoyo

**Tipos de Profesional**
```
GET /api/v1/catalogos/tipos-profesional
```

**Tipos de Nota**
```
GET /api/v1/catalogos/tipos-nota
```

## Ejemplos de Uso

### Buscar médicos
```bash
curl "http://localhost:8080/api/v1/catalogos/profesionales?tipoProfesional=01"
```

### Obtener motivos de nota de crédito
```bash
curl "http://localhost:8080/api/v1/catalogos/motivos-nota?tipoNota=CREDITO"
```

### Buscar profesional por DNI
```bash
curl "http://localhost:8080/api/v1/catalogos/profesionales/buscar-por-documento?tipoDoc=1&numeroDoc=12345678"
```

## Nuevas Entidades Creadas

### Entities (R2DBC)
- `ProfesionalEntity`
- `ClasificacionServicioEntity`
- `MonedaEntity`
- `MecanismoPagoEntity`
- `MotivoNotaEntity`
- `TipoDocAutorizacionEntity`
- `TipoCoberturaEntity`
- `SubtipoCoberturaEntity`
- `TipoPersonalResponsableEntity`
- `TipoHospitalizacionEntity`
- `TipoEgresoHospitalarioEntity`

### Domain Models
- `Profesional`
- `ClasificacionServicio`
- `Moneda`
- `MecanismoPago`
- `MotivoNota`
- `TipoProfesional` (enum)
- `TipoNota` (enum)

### Repositories (R2DBC)
- Repositories correspondientes para cada entidad con métodos de búsqueda optimizados

### Services
- `CatalogoService` - Servicio unificado para todos los catálogos
- `CatalogoServiceImpl` - Implementación con lógica de negocio

### Controllers
- `CatalogoController` - REST API endpoints reactivos

## Características Técnicas

- ✅ **Reactive Programming**: Todos los endpoints usan WebFlux
- ✅ **R2DBC**: Base de datos no-bloqueante
- ✅ **Clean Architecture**: Separación clara de capas
- ✅ **Type Safety**: Uso de enums para valores constantes
- ✅ **Query Optimization**: Métodos de búsqueda eficientes
- ✅ **Error Handling**: Manejo robusto de errores
- ✅ **Documentation**: Endpoints autodocumentados

## Estado de las Tablas

### ✅ Implementadas Completamente
- Catalogo.Profesionales
- Catalogo.ClasificacionServicios
- Catalogo.Monedas
- Catalogo.MecanismosPago
- Catalogo.MotivosNota

### 📋 Entidades Creadas (Pendientes de API)
- catalogo.tipo_doc_autorizacion
- catalogo.tipo_cobertura
- catalogo.subtipo_cobertura
- catalogo.tipo_personal_responsable
- catalogo.tipo_hospitalizacion
- catalogo.tipo_egreso_hospitalario

> **Nota**: Las entidades restantes están creadas y pueden agregarse fácilmente al CatalogoService y CatalogoController siguiendo el mismo patrón.