# 🚀 Guía de Pruebas con Postman - TRAMAS Catálogos

## 📥 Importación de la Colección

### 1. Importar Colección
1. Abre Postman
2. Haz clic en **"Import"**
3. Arrastra y suelta el archivo `TRAMAS_Catalogos.postman_collection.json`
4. Confirma la importación

### 2. Importar Environment
1. En Postman, ve a **"Environments"** (icono de engranaje)
2. Haz clic en **"Import"**
3. Arrastra y suelta el archivo `TRAMAS_Environment.postman_environment.json`
4. Selecciona el environment **"TRAMAS - Local Development"**

## 🏃‍♂️ Preparación del Entorno

### 1. Iniciar la Aplicación
```bash
cd ms-back-mortor-generador-tramas
./mvnw spring-boot:run
```

### 2. Verificar que la Aplicación está Ejecutándose
- La aplicación debe estar disponible en `http://localhost:8080`
- Verifica con el endpoint de health check: `GET http://localhost:8080/actuator/health`

## 🧪 Estructura de Pruebas

### 📋 Colección Organizada por Catálogos

#### 🏥 **Profesionales** (6 endpoints)
- **Obtener todos** - Lista completa de profesionales
- **Buscar por nombre** - Filtrar por nombre (ej: "García")
- **Filtrar por tipo** - Médicos (01), Enfermeros (02), etc.
- **Obtener por ID** - Buscar profesional específico
- **Buscar por documento** - DNI, CE, etc.

#### 🔧 **Clasificaciones de Servicio** (3 endpoints)
- **Obtener todas** - Lista completa de clasificaciones
- **Buscar por nombre** - Filtrar por texto
- **Obtener por código** - Clasificación específica

#### 💰 **Monedas** (4 endpoints)
- **Obtener todas** - Lista de monedas disponibles
- **Buscar por nombre** - Filtrar por texto
- **PEN** - Soles peruanos específicamente
- **USD** - Dólares americanos específicamente

#### 💳 **Mecanismos de Pago** (4 endpoints)
- **Obtener todos** - Lista completa de mecanismos
- **Buscar por nombre** - Filtrar por texto
- **Filtrar por subtipo** - Categorías específicas
- **Obtener por código** - Mecanismo específico

#### 📝 **Motivos de Nota** (5 endpoints)
- **Obtener todos** - Lista completa de motivos
- **Notas de crédito** - Solo motivos de CREDITO
- **Notas de débito** - Solo motivos de DEBITO
- **Buscar por nombre** - Filtrar por texto
- **Obtener por código** - Motivo específico

#### 📋 **Endpoints de Apoyo** (2 endpoints)
- **Tipos de profesional** - Enum completo
- **Tipos de nota** - Enum CREDITO/DEBITO

#### 🔍 **Health Check** (2 endpoints)
- **Health** - Estado de la aplicación
- **Info** - Información de la aplicación

## 🎯 Casos de Prueba Recomendados

### Flujo de Pruebas Básicas

#### 1. **Verificación de Infraestructura**
```
✅ Health Check
✅ Application Info
```

#### 2. **Pruebas de Enums (sin BD)**
```
✅ GET /catalogos/tipos-profesional
✅ GET /catalogos/tipos-nota
```

#### 3. **Pruebas con Base de Datos**
```
⚠️  GET /catalogos/profesionales
⚠️  GET /catalogos/monedas  
⚠️  GET /catalogos/clasificaciones-servicio
⚠️  GET /catalogos/mecanismos-pago
⚠️  GET /catalogos/motivos-nota
```

### Pruebas de Filtros
```
🔍 Buscar profesionales por nombre: "García"
🔍 Filtrar médicos: tipoProfesional=01
🔍 Buscar por documento: tipoDoc=1&numeroDoc=12345678
🔍 Motivos de crédito: tipoNota=CREDITO
🔍 Motivos de débito: tipoNota=DEBITO
```

## 🧪 Tests Automáticos Incluidos

Cada request incluye tests automáticos que verifican:

### ✅ **Tests de Status HTTP**
- Status code es 200
- Tiempo de respuesta < 2000ms
- Content-Type es application/json

### ✅ **Tests de Estructura**
- Arrays para listas
- Objetos para elementos individuales
- Formato JSON válido

### ✅ **Tests de Performance**
- Tiempo de respuesta medido
- Alertas si es > 2 segundos

## 📊 Interpretación de Resultados

### 🟢 **Respuesta Exitosa**
```json
[
  {
    "profesionalId": 1,
    "tipoDocIdentidad": "1",
    "numDocIdentidad": "12345678",
    "nombreCompleto": "Dr. Juan García Pérez",
    "numColegiatura": "12345",
    "tipoProfesional": "MEDICO"
  }
]
```

### 🟡 **Array Vacío (Esperado si no hay datos)**
```json
[]
```

### 🔴 **Error de Conexión DB**
```json
{
  "timestamp": "2025-08-16T05:15:00.000Z",
  "status": 500,
  "error": "Internal Server Error",
  "message": "Unable to acquire connection from pool"
}
```

### 🔴 **Error 404 (Recurso no encontrado)**
```json
{
  "timestamp": "2025-08-16T05:15:00.000Z",
  "status": 404,
  "error": "Not Found",
  "path": "/api/v1/catalogos/profesionales/999"
}
```

## 🗄️ Preparación de Datos de Prueba

### Scripts SQL Recomendados
```sql
-- Insertar datos de prueba para profesionales
INSERT INTO Catalogo.Profesionales (TipoDocIdentidad, NumDocIdentidad, NombreCompleto, NumColegiatura, TipoProfesional) VALUES
('1', '12345678', 'Dr. Juan García Pérez', '12345', '01'),
('1', '87654321', 'Enf. María López Ruiz', '54321', '02'),
('1', '11223344', 'Dr. Carlos Mendoza', '67890', '01');

-- Insertar monedas
INSERT INTO Catalogo.Monedas (Codigo, Nombre, Simbolo) VALUES
('PEN', 'Soles', 'S/'),
('USD', 'Dólares Americanos', '$');

-- Insertar motivos de nota
INSERT INTO Catalogo.MotivosNota (Codigo, Nombre, TipoNota) VALUES
('0001', 'Descuento por pronto pago', 'CREDITO'),
('0002', 'Intereses por mora', 'DEBITO');
```

## 🔧 Troubleshooting

### Problema: "Connection refused"
**Solución:** Verificar que la aplicación esté ejecutándose en puerto 8080

### Problema: "Arrays vacíos en todas las respuestas"
**Solución:** La BD no tiene datos. Ejecutar scripts de inserción de datos de prueba

### Problema: "500 Internal Server Error"
**Solución:** Verificar conexión a la base de datos SQL Server

### Problema: "404 Not Found en algunos endpoints"
**Solución:** Verificar que la URL esté correcta y el mapping del controller

## 📈 Métricas de Éxito

### ✅ **Pruebas Básicas Exitosas**
- Health check responde 200
- Enums devuelven datos correctos
- Estructura JSON válida

### ✅ **Pruebas con BD Exitosas**  
- Al menos un catálogo devuelve datos
- Filtros funcionan correctamente
- Búsquedas por ID funcionan

### ✅ **Performance Aceptable**
- Tiempo de respuesta < 2 segundos
- Sin errores de timeout
- Memoria estable durante las pruebas

## 🎯 Próximos Pasos

1. **Ejecutar todas las pruebas básicas**
2. **Insertar datos de prueba en la BD**
3. **Validar filtros y búsquedas**
4. **Probar casos edge (IDs inexistentes, parámetros inválidos)**
5. **Documentar casos de uso específicos del negocio**