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


## Contractos

### Creacion de solicitud de recoleccion
Endpoint: `/collections/createRequestCollection`
Method: `POST`

Required JSON parameters in Request:
* collectionPointId: Long
* pickupDate: LocalDateTime
Optional JSON parameters in Request:
* commentary: String

Body:
```json
{
    "collectionPointId": 3,
    "pickupDate": "2024-03-29T12:00:00",
    "commentary": "fadsfsa"
}
```

Example CURL:
```bash
curl --location 'http://localhost:8081/collections/createRequestCollection' \
--header 'Content-Type: application/json' \
--data '{
    "collectionPointId": 3,
    "pickupDate": "2024-03-29T12:00:00",
    "commentary": "fadsfsa"
}'
```
Example response:
```json
{
  "message": "Solicitud de recogida creada exitosamente",
  "statusCode": 200,
  "errorCode": null,
  "data": null
}
```

### Obtener solicitudes de recoleccion como administrador con filtro
Description: Obtener solicitudes de recoleccion con informacion de los puntos de recoleccion y el recolector asignado en caso de tener. Ademas con la posibilidad de agregar filtros

El filtro que aplica es de los siguientes campos obteniendo valor para los que aplique:
* Estado de la solicitud
* Dirección del punto de acopio
* Ciudad del punto de acopio
* Departamento del punto de acopio
* Pais del punto de acopio
* Nombre del recolector
* Apellido del recolector
* Correo del recolector

Endpoint: `/collections/requestCollectionsAdmin`

Method: `GET`

Optional Query String parameters in Request:
* pickupRequestStatusId: Long
* filterSearchValue: String

Example:
```
http://localhost:8081/collections/requestCollectionsAdmin
http://localhost:8081/collections/requestCollectionsAdmin?pickupRequestStatusId=1
http://localhost:8081/collections/requestCollectionsAdmin?pickupRequestStatusId=1&filterSearchValue=2024-03-29
http://localhost:8081/collections/requestCollectionsAdmin?filterSearchValue=2024-03-29
```
Example Response:
```json
{
    "message": "OK",
    "statusCode": 200,
    "errorCode": null,
    "data": [
        {
            "id": 3,
            "user": {
                "id": "Cedula1",
                "name": "Usuario1",
                "lastName": "ApellidoUsuario1",
                "email": "usuario1@example.com",
                "phoneNumber": "Numero1",
                "password": null,
                "roleId": 1
            },
            "collectionPoint": {
                "id": 3,
                "user": {
                    "id": "Cedula3",
                    "name": "Usuario3",
                    "lastName": "ApellidoUsuario3",
                    "email": "usuario3@example.com",
                    "phoneNumber": "Numero3",
                    "password": null,
                    "roleId": 3
                },
                "statusId": null,
                "agreementCode": null,
                "address": "Calle Central 789",
                "city": "Ciudad3",
                "state": "Estado3",
                "country": "País3"
            },
            "kilograms": null,
            "pickupDate": null,
            "commentary": null,
            "dateCreate": null,
            "pickupRequestStatusId": null
        }
    ]
}
```

### Obtener estados de solicitud de recoleccion
Endpoint: `/pickup-request-status/status`

Method: `GET`

Example JSON Response:
```json
{
    "message": "OK",
    "statusCode": 200,
    "errorCode": null,
    "data": [
        {
            "id": 3,
            "name": "Agendada"
        },
        {
            "id": 2,
            "name": "Completado"
        },
        {
            "id": 1,
            "name": "Pendiente"
        }
    ]
}
```

### Asignar recolector o fecha a solicitud de recoleccion por parte del administrador
Endpoint: `/collections/updateRecollectorOrPickupDate`

Method: `POST`
Required JSON parameters in Request:
* pickupRequestId: Long

Optional JSON parameters in Request:
* pickupDate: LocalDateTime
* recollectorId: String

Example JSON Request:
```json
{
    "pickupRequestId": "6",
    "pickupDate": "2024-03-29T14:00:00",
    "recollectorId": "Cedula2"
}
```
Example JSON Response:
```json
{
    "message": "Solicitud de recogida actualizada exitosamente",
    "statusCode": 200,
    "errorCode": null,
    "data": null
}
```

### Obtener solicitudes de recoleccion como recolector con filtro
Description: Obtener solicitudes de recoleccion con informacion de los puntos de recoleccion y el recolector asignado en caso de tener. Ademas con la posibilidad de agregar filtros

El filtro que aplica es de los siguientes campos obteniendo valor para los que aplique:
* Estado de la solicitud
* Dirección del punto de acopio
* Ciudad del punto de acopio
* Departamento del punto de acopio
* Pais del punto de acopio
* Nombre del recolector
* Apellido del recolector
* Correo del recolector

Endpoint: `/collections/requestCollectionsRecollector`

Method: `GET`

Optional Query String parameters in Request:
* pickupRequestStatusId: Long
* filterSearchValue: String

Example:
```
http://localhost:8081/collections/requestCollectionsAdmin
http://localhost:8081/collections/requestCollectionsAdmin?pickupRequestStatusId=1
http://localhost:8081/collections/requestCollectionsAdmin?pickupRequestStatusId=1&filterSearchValue=2024-03-29
http://localhost:8081/collections/requestCollectionsAdmin?filterSearchValue=2024-03-29
```