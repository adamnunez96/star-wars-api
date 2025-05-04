# Conexa Star Wars API

## Descripción

**Conexa Star Wars API** es una API diseñada para proporcionar información sobre el universo de Star Wars. Toda la informacion expuesta son consumidos desde la Api de [Swappi](https://www.swapi.tech/documentation). Incluye datos sobre personajes, peliculas, naves y vehiculos.

La aplicacion actualmente se encuentra expuesta en una [INSTANCIA](http://13.59.27.108:8080/swagger-ui/index.html#) de AWS (EC2) por la cual puede ser consumida sin restricciones.

## Requisitos
Para correr la aplicación necesita:

- [java jdk 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3.8.6](https://maven.apache.org)
- [Docker](https://www.docker.com/) (opcional)

1. Clonar el repositorio:
    ```bash
    git clone https://github.com/adamnunez96/star-wars-api.git
    ```
2. Configurar variable de entorno (Opcional). Esto en caso que se monte la aplicacion en un ambiente diferente al local de manera a manejar dinamicamente la DNS o IP para la paginacion. 
- En macOS/Linux:
    ```bash
    export SERVICE_URL=https://api.example.com/v1
    ```
- En Windows (CMD):
    ```bash
    $env:SERVICE_URL = "https://api.example.com/v1"
    ```


## Uso

Inicia el servidor con el siguiente comando:

```bash
npm start
```

La API estará disponible en `http://localhost:3000`.

## Endpoints

### Ejemplo de Endpoint

- **GET** `/api/characters`  
  Devuelve una lista de personajes.

  **Respuesta:**
  ```json
  [
     {
        "id": 1,
        "name": "Luke Skywalker",
        "species": "Human"
     },
     {
        "id": 2,
        "name": "Darth Vader",
        "species": "Human"
     }
  ]
  ```

Consulta la [documentación completa](docs/endpoints.md) para más detalles.

## Contribuciones

¡Las contribuciones son bienvenidas! Por favor, abre un issue o envía un pull request.

## Licencia

Este proyecto está bajo la licencia [MIT](LICENSE).

## Contacto

Si tienes preguntas o sugerencias, no dudes en contactarnos en [email@example.com](mailto:email@example.com).