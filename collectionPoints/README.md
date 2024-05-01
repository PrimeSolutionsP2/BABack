# Proyecto Base Implementando Clean Architecture

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por último el inicio y configuración de la aplicación.

Lee el artículo [Clean Architecture — Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patrón de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## Application

Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**


---

# Documentación de la api

## Obtener puntos de acopio

### Descripción
Para obtener los puntos de acopio que han sido registrados en el sistema con anterioridad se debe tener en cuenta la siguiente información:

| Endpoint               | Method |
|:-----------------------|:-------|
|api/v1/collectionPoints/|GET     |

Los puntos de acopio permiten ser filtrados a través de los siguientes campos:
* Id del usuario (Cédula)
* Nombre del punto de acopio
* Dirección del punto de acopio
* Ciudad del punto de acopio
* Departamento del punto de acopio
* País del punto de acopio
* Estado del punto de acopio

### Parámetros
* search: String (query)
* statusId: String (query)

### Respuestas
| Status Code | Descripción |
|:------------|:------------|
|200          | Ok |

```json
{
    "statusCode": 200,
    "data": [
        {
            "id": 1,
            "userId": "12345",
            "name": "Punto de acopio #1",
            "agreement": "ABC123",
            "address": "Calle 10#10-10",
            "city": "Medellin",
            "state": "Antioquia",
            "country": "Colombia",
            "statusId": 3,
            "status": {
                "id": 3,
                "name": "ACTIVO"
            }
        },
    ],
    "message": "OK"
}
```

## Obtener un punto de acopio por id

### Descripción

Para obtener un punto de acopio que ha sido registrado en el sistema con anterioridad, acorde al id de este punto, se debe tener en cuenta la siguiente información:

| Endpoint                   | Method |
|:---------------------------|:-------|
|api/v1/collectionPoints/{id}|GET     |

### Parámetros
* id: String

### Respuestas
| Status Code | Descripción |
|:------------|:------------|
|200          | Ok |

#### Ejemplo de respuesta
```json
{
    "statusCode": 200,
    "data": [
        {
            "id": 1,
            "userId": "12345",
            "name": "Punto de acopio #1",
            "agreement": "ABC123",
            "address": "Calle 10#10-10",
            "city": "Medellin",
            "state": "Antioquia",
            "country": "Colombia",
            "statusId": 3,
            "status": {
                "id": 3,
                "name": "ACTIVO"
            }
        },
    ],
    "message": "OK"
}
```

| Status Code | Descripción |
|:------------|:------------|
|404          | Not Found   |

#### Ejemplo de respuesta
```json
{
    "statusCode": 404,
    "message": "Collection point with id: 7, was not found!"
}
```

## Creación de un punto de acopio

### Descripción
Para crear un nuevo punto de acopio, se debe tener en cuenta la siguiente información:

| Endpoint                   | Method |
|:---------------------------|:-------|
|api/v1/collectionPoints/request/|POST     |

### Request body
Para crear un punto de acopio en el sistema se debe enviar un cuerpo en la petición que tiene la siguiente estructura:

```json
{
    "userId": String,
    "userName": String,
    "lastName": String,
    "email": String,
    "phoneNumber": String,
    "name": String,
    "agreement": String,
    "address": String,
    "city": String,
    "state": String,
    "country": String
}
```

### Respuestas
| Status Code | Descripción |
|:------------|:------------|
|201          | Created |

#### Ejemplo de respuesta
```json
{
    "statusCode": 201,
    "data": [
        {
            "id": 1,
            "userId": "12345",
            "name": "Punto de acopio #1",
            "agreement": "ABC123",
            "address": "Calle 10#10-10",
            "city": "Medellin",
            "state": "Antioquia",
            "country": "Colombia",
            "statusId": 1,
            "status": null
        },
    ],
    "message": "CREATED SUCCESSFULLY"
}
```

## Cambiar estado de un punto de acopio

### Descripción
Para cambiar el estado de un punto de acopio, se debe tener en cuenta la siguiente información:

| Endpoint                   | Method |
|:---------------------------|:-------|
|api/v1/collectionPoints/change-status/{id}|PATCH     |

### Parámetros
* id: String

### Respuestas
| Status Code | Descripción |
|:------------|:------------|
|200          | Ok |

#### Ejemplo de respuesta
```json
{
    "statusCode": 200,
    "data": [
        {
            "id": 1,
            "userId": "12345",
            "name": "Punto de acopio #1",
            "agreement": "ABC123",
            "address": "Calle 10#10-10",
            "city": "Medellin",
            "state": "Antioquia",
            "country": "Colombia",
            "statusId": 3,
            "status": {
                "id": 3,
                "name": "ACTIVO"
            }
        },
    ],
    "message": "OK"
}
```

| Status Code | Descripción |
|:------------|:------------|
|404          | Not Found |

#### Ejemplo de respuesta
```json
{
    "statusCode": 404,
    "message": "Collection point with id: 7, was not found!"
}
```

| Status Code | Descripción |
|:------------|:------------|
|404          | Not Found |

#### Ejemplo de respuesta
```json
{
    "statusCode": 400,
    "message": "Cannot change status to 1 (PENDIENTE)"
}
```

## Actualizar información de un punto de acopio

### Descripción
Para actualizar la información de un punto de acopio, se debe tener en cuenta la siguiente información:

| Endpoint                   | Method |
|:---------------------------|:-------|
|api/v1/collectionPoints/{id}|PATCH     |

### Parámetros
* id: String

### Request body
Para actualizar la información de un punto de acopio se debe enviar un cuerpo en la petición conteniendo los valores de los campos que se desean actualizar, este cuerpo debería tener una estructura parecida a la siguiente donde cada llave valor es opcional ya que no se necesita enviar toda la información para poder actualizarse:

```json
{
    "name": String,
    "agreement": String,
    "address": String,
    "city": String,
    "state": String,
    "country": String
}
```

### Respuestas
| Status Code | Descripción |
|:------------|:------------|
|200          | Ok |

#### Ejemplo de respuesta
```json
{
    "statusCode": 200,
    "data": [
        {
            "id": 1,
            "userId": "12345",
            "name": "Punto de acopio editado #1",
            "agreement": "ABC123",
            "address": "Calle 10#10-10",
            "city": "Medellin",
            "state": "Antioquia",
            "country": "Colombia",
            "statusId": 3,
            "status": {
                "id": 3,
                "name": "ACTIVO"
            }
        },
    ],
    "message": "OK"
}
```

| Status Code | Descripción |
|:------------|:------------|
|404          | Not Found |

#### Ejemplo de respuesta
```json
{
    "statusCode": 404,
    "message": "Collection point with id: 7, was not found!"
}
```