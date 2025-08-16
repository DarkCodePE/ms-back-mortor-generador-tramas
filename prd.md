Documento de Requerimientos de Producto (PRD)
=============================================

Sistema de Registro y Generación de Tramas TEDEF
------------------------------------------------

**Versión:** 1.2 (Foco en Entrada de Datos)**Autor:** Orlando Kuan**Fecha:** 25 de julio de 2025**Estado:** Finalizado

### 1\. Visión General del Producto

#### 1.1. Problema a Resolver

Actualmente no existe una herramienta centralizada y estructurada para registrar la información requerida para las tramas de facturación TEDEF. Los datos se recopilan de manera manual y dispersa, lo que dificulta su consolidación, validación y posterior formateo en los archivos .txt exigidos por la aseguradora, resultando en un proceso lento y propenso a errores.

#### 1.2. Objetivo Principal del Producto

El objetivo de este proyecto es desarrollar un **servicio de backend** que soporte una aplicación de **entrada de datos** (data entry). Este motor permitirá a los usuarios **registrar, a través de formularios, toda la información de las atenciones, servicios y facturas**, la cual será **almacenada en una base de datos SQL Server dedicada (TRAMAS\_DB)**.

Una vez que la información esté registrada y validada en la base de datos, el mismo motor se encargará de **generar los archivos de trama TEDEF** conformes a la especificación.

#### 1.3. Pila Tecnológica

*   **Lenguaje/Framework:** Java 17+ / Spring Boot 3+ con WebFlux
    
*   **Base de Datos:** TRAMAS\_DB (SQL Server) con la estructura modular ya definida.
    

### 2\. Personas de Usuario y Casos de Uso

*   **Usuario Principal (Operador de Facturación):**
    
    *   **Caso de Uso:** Utilizar una interfaz (soportada por este backend) para registrar los detalles de una nueva factura y todas sus atenciones asociadas, incluyendo servicios, farmacia y datos del paciente.
        
    *   **Caso de Uso:** Iniciar el proceso de generación de tramas para una factura ya registrada y validada.
        
*   **Administrador del Sistema:**
    
    *   **Caso de Uso:** Gestionar las tablas de catálogo (Catalogo.\*) y las 16 tablas de reglas de negocio (Reglas.\*) para asegurar que los formularios de registro ofrezcan opciones válidas y actualizadas.
        

### 3\. Arquitectura del Sistema

El sistema será una aplicación autocontenida. El backend servirá como la API para una aplicación cliente (frontend) y se comunicará exclusivamente con su propia base de datos TRAMAS\_DB.

codeMermaid

Plain textANTLR4BashCC#CSSCoffeeScriptCMakeDartDjangoDockerEJSErlangGitGoGraphQLGroovyHTMLJavaJavaScriptJSONJSXKotlinLaTeXLessLuaMakefileMarkdownMATLABMarkupObjective-CPerlPHPPowerShell.propertiesProtocol BuffersPythonRRubySass (Sass)Sass (Scss)SchemeSQLShellSwiftSVGTSXTypeScriptWebAssemblyYAMLXML`   graph TD      subgraph "Módulo de Tramas (Esta Solución)"          Frontend[Aplicación Cliente   (Formularios de Registro)]          BackendAPI[**Backend API de Registro y Generación**   (Spring WebFlux / Java)]          TRAMAS_DB[(**TRAMAS_DB**   SQL Server   _Almacén de datos de tramas_)]      end      Frontend -- "1. Envía datos de formularios (API Calls)" --> BackendAPI      BackendAPI -- "2. Valida y Almacena datos" --> TRAMAS_DB      Frontend -- "3. Solicita Generación de Trama" --> BackendAPI      BackendAPI -- "4. Lee datos almacenados" --> TRAMAS_DB      BackendAPI -- "5. Genera Archivo .zip" --> Frontend   `

### 4\. Requerimientos Funcionales del Motor

#### RF-01: Gestión de Registros de Tramas (CRUD a través de Formularios)

El motor debe proveer endpoints que soporten operaciones CRUD para que los usuarios puedan registrar la información necesaria para las tramas.

*   **RF-01.1: Registrar Factura y Atenciones:** El backend expondrá endpoints POST para crear nuevos registros en las tablas de Staging. Esto incluye:
    
    *   Un endpoint para registrar los datos de la cabecera de la factura (Staging.TramaFactura).
        
    *   Un endpoint para añadir una o más atenciones a esa factura (Staging.TramaAtencion).
        
    *   Endpoints para añadir detalles a una atención (servicios en Staging.TramaServicio, farmacia en Staging.TramaFarmacia, etc.).
        
*   **RF-01.2: Validación en Tiempo de Registro:** Al recibir datos de un formulario, el backend debe realizar validaciones inmediatas contra las tablas de Catalogo y Reglas para asegurar la integridad de los datos. Por ejemplo, no se podrá registrar una atención con un TipoAfiliacion que no exista en Catalogo.TipoAfiliacion.
    
*   **RF-01.3: Consulta y Modificación:** El sistema debe proveer endpoints GET, PUT y DELETE para que los usuarios puedan buscar, visualizar y corregir la información que han registrado antes de generar la trama final.
    

#### RF-02: Gestión de Catálogos y Reglas de Negocio

*   **RF-02.1: API de Consulta de Catálogos:** El backend debe exponer endpoints GET para que el frontend pueda poblar las listas desplegables de los formularios (ej. GET /api/v1/catalogs/tipo-doc-pago).
    
*   **RF-02.2: API de Gestión de Reglas (CRUD):** El backend debe proveer endpoints CRUD completos para que el rol de Administrador pueda mantener actualizadas las 16 tablas de Reglas de negocio.
    

#### RF-03: Motor de Generación de Archivos de Trama

*   **RF-03.1: Proceso de Generación:** El motor debe incluir una funcionalidad (GET /api/v1/facturas/{facturaId}/export) que:
    
    1.  Tome como entrada el identificador de una factura (facturaId) cuyo estado sea 'VALIDADO'.
        
    2.  Lea toda la información asociada a esa factura desde las tablas Staging en TRAMAS\_DB.
        
    3.  Ensamble los datos en el formato de texto plano (.txt) especificado por TEDEF para cada tipo de trama.
        
*   **RF-03.2: Salida en Formato .zip:** La salida final del proceso debe ser un único archivo .zip que contenga todos los archivos de trama (Factura.txt, Atenciones.txt, etc.) listos para ser enviados a la aseguradora.
    

#### RF-04: Seguridad y Auditoría

*   **RF-04.1: Autenticación y Autorización:** El acceso a la API estará protegido por roles, gestionados en la tabla Control.Usuarios. Un "Operador" podrá registrar y modificar datos, mientras que un "Administrador" podrá gestionar catálogos y reglas.
    
*   **RF-04.2: Trazabilidad:** Cada registro creado o modificado en las tablas Staging debe tener una traza de auditoría, indicando qué usuario (UsuarioId) realizó la acción y cuándo (FechaCreacion/FechaModificacion).
    

### 5\. Requerimientos No Funcionales

*   **Rendimiento de la API:** Los endpoints de la API deben responder rápidamente para ofrecer una experiencia de usuario fluida en los formularios. El objetivo es un tiempo de respuesta inferior a 200ms para las operaciones CRUD.
    
*   **Integridad de Datos:** El diseño de la base de datos TRAMAS\_DB es crítico. El uso de llaves foráneas, constraints CHECK y tipos de datos correctos es mandatorio para prevenir la entrada de datos basura.
    
*   **Usabilidad (para el Frontend):** El backend debe proporcionar mensajes de error claros y específicos para que el frontend pueda guiar al usuario en la corrección de datos en los formularios.
    

### 6\. Fuera de Alcance

*   La implementación de la interfaz de usuario (Frontend).
    
*   **Cualquier conexión o dependencia con la base de datos de la historia clínica (ClinicalDB).** El nuevo sistema es completamente autónomo.
    
*   La implementación de capacidades de Inteligencia Artificial (Agentes de IA), que se podrá considerar en una fase futura sobre los datos ya recopilados en TRAMAS\_DB.
    

### 7\. Métricas de Éxito

*   Un usuario puede registrar exitosamente una factura completa con múltiples atenciones y detalles a través de los endpoints de la API.
    
*   Los datos guardados en TRAMAS\_DB son consistentes y válidos según las reglas definidas en los catálogos.
    
*   La funcionalidad de exportación genera un archivo .zip que es 100% compatible con el validador de la aseguradora.