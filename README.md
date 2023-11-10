## Endpoints
localhost:3000

A continuación se enumeran los endpoints disponibles en nuestra API:

| Método | Ruta               | Descripción                                                                                                          |
|--------|--------------------|----------------------------------------------------------------------------------------------------------------------|
| GET    | /api/estacion      | Obtiene todas las estaciones                                                                                         |
| GET    | /api/estacion/{id} | Obtiene una estacion por id                                                                                          |
| POST   | /api/estacion      | Crea una nueva estación                                                                                              |
| POST   | /api/alquiler      | Regsitra un alquiler para una estacion especifica                                                                    |
| PUT    | /api/alquiler      | Registra la devolucion de un alquiler                                                                                |
| GET    | /api/alquiler      | Devuelve las estaciones que coincidan con los filtros enviados, sino se mandan filtros devuelve todos los alquileres |
##Swagger Documentation
##Estaciones
http://localhost:3001/swagger-ui/index.html#
##Alquileres
http://localhost:3002/swagger-ui/index.html#