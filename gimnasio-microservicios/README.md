# Sistema de Gestión de Gimnasio - Arquitectura de Microservicios

**Asignatura:** DSY1103 - Desarrollo FullStack 1
**Evaluación:** Parcial 2 - Encargo con Defensa Técnica
**Equipo:** [Completar con nombres y número de equipo]

---

## Descripción del Proyecto

Sistema distribuido para la gestión integral de un gimnasio, construido bajo una arquitectura de **microservicios independientes**. Cada microservicio se encarga de un área funcional específica del dominio, posee su propia base de datos PostgreSQL y se comunica con los demás mediante **Feign Client** sobre endpoints REST.

El sistema cubre desde el registro de socios y la gestión de membresías, hasta el agendamiento de clases, control de asistencia, pagos, rutinas personalizadas y administración de equipamiento y sucursales.

---

## Integrantes

| Nombre | Apellido | Rol |
|--------|----------|-----|
| [Nombre 1] | [Apellido 1] | [Rol / microservicios a cargo] |
| [Nombre 2] | [Apellido 2] | [Rol / microservicios a cargo] |
| [Nombre 3] | [Apellido 3] | [Rol / microservicios a cargo] |

---

## Microservicios implementados

| # | Microservicio | Puerto | Responsabilidad |
|---|---------------|--------|-----------------|
| 1 | `ms-socios` | 8081 | Gestión de socios del gimnasio (CRUD, datos personales, estado) |
| 2 | `ms-membresias` | 8082 | Planes de membresía y suscripciones activas de socios |
| 3 | `ms-instructores` | 8083 | Registro y administración de instructores |
| 4 | `ms-clases` | 8084 | Catálogo de clases grupales y horarios |
| 5 | `ms-reservas` | 8085 | Reserva de cupos en clases por parte de los socios |
| 6 | `ms-pagos` | 8086 | Registro y consulta de pagos efectuados |
| 7 | `ms-equipos` | 8087 | Inventario de máquinas y equipos del gimnasio |
| 8 | `ms-rutinas` | 8088 | Rutinas de entrenamiento asignadas a socios |
| 9 | `ms-asistencias` | 8089 | Registro de ingresos diarios de socios |
| 10 | `ms-sucursales` | 8090 | Sucursales físicas y su capacidad |

---

## Stack Tecnológico

- **Java 17**
- **Spring Boot 3.2.x**
- **Spring Data JPA + Hibernate** (persistencia)
- **PostgreSQL** (base de datos por microservicio)
- **Bean Validation (JSR 380)** (validación de DTOs)
- **Spring Cloud OpenFeign** (comunicación entre microservicios)
- **SLF4J** (logging estructurado)
- **Maven** (gestión de dependencias)
- **Postman** (pruebas de integración)

---

## Estructura de carpetas

```
gimnasio-microservicios/
├── README.md
├── ms-socios/
├── ms-membresias/
├── ms-instructores/
├── ms-clases/
├── ms-reservas/
├── ms-pagos/
├── ms-equipos/
├── ms-rutinas/
├── ms-asistencias/
├── ms-sucursales/
├── docs/
│   ├── modelo-datos.md
│   ├── reglas-negocio.md
│   └── comunicacion-entre-servicios.md
└── postman/
    └── Gimnasio-Microservicios.postman_collection.json
```

Cada microservicio sigue el patrón **CSR (Controller → Service → Repository)** con la siguiente estructura interna:

```
ms-xxxx/
├── pom.xml
└── src/main/
    ├── java/com/gimnasio/xxxx/
    │   ├── XxxxApplication.java
    │   ├── controller/
    │   ├── service/
    │   ├── repository/
    │   ├── model/
    │   ├── dto/
    │   ├── exception/
    │   └── client/         (solo si consume otros microservicios)
    └── resources/
        ├── application.properties
        └── db/migration/
            └── V1__init.sql
```

---

## Configuración y ejecución

### Requisitos previos

1. **JDK 17** instalado (`java -version`).
2. **Maven 3.8+** instalado (`mvn -version`).
3. **PostgreSQL 14+** corriendo en `localhost:5432`.
4. **Postman** para pruebas (opcional pero recomendado).

### 1. Crear las bases de datos

Conéctate a PostgreSQL y ejecuta:

```sql
CREATE DATABASE gimnasio_socios;
CREATE DATABASE gimnasio_membresias;
CREATE DATABASE gimnasio_instructores;
CREATE DATABASE gimnasio_clases;
CREATE DATABASE gimnasio_reservas;
CREATE DATABASE gimnasio_pagos;
CREATE DATABASE gimnasio_equipos;
CREATE DATABASE gimnasio_rutinas;
CREATE DATABASE gimnasio_asistencias;
CREATE DATABASE gimnasio_sucursales;
```

Por defecto cada microservicio usa el usuario `postgres` con contraseña `postgres`. Si tu instalación es distinta, edita el `application.properties` de cada microservicio.

### 2. Compilar todos los microservicios

Desde la raíz del proyecto:

```bash
cd ms-socios && mvn clean install -DskipTests && cd ..
cd ms-membresias && mvn clean install -DskipTests && cd ..
cd ms-instructores && mvn clean install -DskipTests && cd ..
cd ms-clases && mvn clean install -DskipTests && cd ..
cd ms-reservas && mvn clean install -DskipTests && cd ..
cd ms-pagos && mvn clean install -DskipTests && cd ..
cd ms-equipos && mvn clean install -DskipTests && cd ..
cd ms-rutinas && mvn clean install -DskipTests && cd ..
cd ms-asistencias && mvn clean install -DskipTests && cd ..
cd ms-sucursales && mvn clean install -DskipTests && cd ..
```

### 3. Levantar los microservicios

En **terminales separadas**, levanta cada uno:

```bash
cd ms-socios       && mvn spring-boot:run
cd ms-membresias   && mvn spring-boot:run
cd ms-instructores && mvn spring-boot:run
cd ms-clases       && mvn spring-boot:run
cd ms-reservas     && mvn spring-boot:run
cd ms-pagos        && mvn spring-boot:run
cd ms-equipos      && mvn spring-boot:run
cd ms-rutinas      && mvn spring-boot:run
cd ms-asistencias  && mvn spring-boot:run
cd ms-sucursales   && mvn spring-boot:run
```

Cada microservicio queda escuchando en su puerto asignado (ver tabla más arriba).

### 4. Probar los endpoints

Importa la colección `postman/Gimnasio-Microservicios.postman_collection.json` en Postman y ejecuta las requests organizadas por microservicio.

---

## Funcionalidades implementadas

- CRUD completo en los 10 microservicios.
- Validaciones con Bean Validation en todos los DTOs de entrada.
- Manejo centralizado de excepciones con `@ControllerAdvice` por microservicio.
- Logs estructurados con SLF4J en controllers y services.
- Comunicación entre microservicios mediante Feign Client (ver `docs/comunicacion-entre-servicios.md`).
- Reglas de negocio del dominio (ver `docs/reglas-negocio.md`).
- Migraciones SQL iniciales en `src/main/resources/db/migration/V1__init.sql` por microservicio.

---

## Documentación adicional

- [`docs/modelo-datos.md`](docs/modelo-datos.md) – Modelo de datos y relaciones por microservicio.
- [`docs/reglas-negocio.md`](docs/reglas-negocio.md) – Reglas de negocio implementadas.
- [`docs/comunicacion-entre-servicios.md`](docs/comunicacion-entre-servicios.md) – Diagrama de comunicación Feign entre microservicios.
