# Motor Generador de Tramas TEDEF: Un Caso de Estudio para la Investigación en IA Autónoma

## 1. Visión General del Proyecto

Este repositorio contiene el código y la documentación del proyecto **"Motor Generador de Tramas TEDEF"**, un sistema diseñado para la gestión y generación de tramas de facturación en el sector salud peruano.

El proyecto tiene un doble propósito:
1.  **A Nivel de Investigación:** Servir como un caso de estudio práctico y un "sandbox" para la investigación y desarrollo de arquitecturas de Inteligencia Artificial avanzadas en el marco de la Maestría en Inteligencia Artificial.

---

## 2. Contexto y Fuente de Datos

### 2.1. Uso de Información Pública de SUSALUD

La Superintendencia Nacional de Salud (SUSALUD) de Perú, en su rol de ente regulador, publica diversas normativas y estándares técnicos con el objetivo de unificar y mejorar los procesos en el sector salud. Esta información es de **carácter público** y fundamental para el desarrollo de cualquier sistema que busque interoperar en el ecosistema de salud peruano.

Los documentos clave utilizados en este proyecto, y que sirven como base para el modelado de datos y la lógica de negocio, incluyen:

*   **Norma Técnica de la Historia Clínica de los Establecimientos del Sector Salud (N.T. N° 022-MINSA/DGSP-V.02):** Define la estructura y el contenido mínimo de los registros clínicos.
*   **Manual de Normas de Facturación, Auditoría Médica y Procesos de Atención (Documento de Consenso):** Establece las reglas de negocio para la facturación entre clínicas y aseguradoras.
*   **Estructuras de Trama TEDEF:** Define el formato técnico exacto para el intercambio de información de facturación.

**Nota de Transparencia:** Este proyecto utiliza las estructuras y reglas definidas en estos documentos públicos para simular un entorno realista. No se utiliza información privada o sensible de pacientes o instituciones.

### 2.2. Relación con la Investigación en IA (Arquitectura CODA)

El campo de los agentes de Inteligencia Artificial avanza hacia sistemas auto-mejorables, capaces de adquirir nuevas habilidades con mínima supervisión. Sin embargo, los enfoques actuales enfrentan limitaciones críticas: sus procesos de mejora son a menudo rígidos y dependen de bucles de retroalimentación extrínsecos, diseñados por humanos, que limitan su escalabilidad y adaptabilidad. La investigación de vanguardia argumenta que el siguiente paso evolutivo requiere **aprendizaje metacognitivo intrínseco**: la habilidad de un agente para evaluar, planificar y adaptar activamente sus propios procesos de razonamiento y aprendizaje.

Frente a este panorama, este proyecto sirve como el dominio de aplicación para la validación de **CODA (Cognitive Orchestration and Dynamic Assembly)**, una novedosa arquitectura de IA autónoma diseñada explícitamente para instanciar este aprendizaje metacognitivo intrínseco.

El sistema "Motor Generador de Tramas" representa un problema complejo, rico en reglas de negocio y con una estructura de datos jerárquica, lo que lo convierte en un campo de pruebas ideal para CODA. El objetivo de la investigación es utilizar CODA para:

1.  **Aprender de los Rechazos:** En un futuro, que el agente pueda analizar las tramas rechazadas, inferir la regla de negocio que se incumplió y auto-corregir su proceso de generación para futuros casos, demostrando un ciclo de aprendizaje metacognitivo intrínseco.

