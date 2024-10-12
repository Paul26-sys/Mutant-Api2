# Detector-mutant
 api demo en spring basica
Diagrama de secuencia para deteccion de ADN-Mutante
 Client                    Controller                 Service                 Repository
 |                         |                          |                          |
 |--- POST /mutant ------->|                          |                          |
 |                         |--- isMutant(dna) ------->|                          |
 |                         |                          |--- existsByDna(dna) ---->|
 |                         |                          |<--- true/false -----------|
 |                         |                          |                          |
 |                         |                          |--- findByDna(dna) ------>|
 |                         |                          |<--- DnaSequence ----------|
 |                         |                          |                          |
 |                         |                          |--- save(dnaSequence) ---->|
 |                         |                          |<--- void -----------------|
 |                         |<--- true/false -----------|                          |
 |<--- HTTP Response -------|                          |                          |
# Mutant API

Esta API permite verificar si una secuencia de ADN es mutante o no, y proporciona estadísticas sobre las verificaciones realizadas.

## Requisitos

- Java 11 o superior
- Maven
- H2 Database (incluida en el proyecto)

## Configuración

1. **Clonar el repositorio**:
    ```sh
    git clone https://github.com/tu-usuario/Mutant-Api.git
    cd Mutant-Api
    ```

2. **Compilar el proyecto**:
    ```sh
    mvn clean install
    ```

3. **Ejecutar la aplicación**:
    ```sh
    mvn spring-boot:run
    ```

## Base de Datos

La aplicación utiliza H2 Database, una base de datos en memoria. No necesitas configurar nada adicional para la base de datos, ya que se configura automáticamente al iniciar la aplicación.

## Endpoints

### Verificar ADN

**Endpoint**: `POST /mutant`

**Descripción**: Verifica si una secuencia de ADN es mutante o no.

**Cuerpo de la solicitud**:
```json
{
  "dna": [
    "ATGCGA",
    "CAGTGC",
    "TTATGT",
    "AGAAGG",
    "CCCCTA",
    "TCACTG"
  ]
}

    true si la secuencia es mutante.
    false si la secuencia no es mutante.

Estadísticas

Endpoint: GET /stats

Descripción: Devuelve estadísticas sobre las verificaciones de ADN.

{
  "count_mutant_dna": 40,
  "count_human_dna": 100,
  "ratio": 0.4
}
Cómo funciona

    Verificación de ADN:
        La API recibe una secuencia de ADN en formato de matriz NxN.
        Verifica si la secuencia ya existe en la base de datos.
        Si la secuencia no existe, la API busca secuencias de cuatro letras iguales en direcciones horizontales, verticales y diagonales.
        Si encuentra más de una secuencia de cuatro letras iguales, la secuencia se considera mutante.
        La secuencia y su resultado se almacenan en la base de datos.

    Estadísticas:
        La API proporciona un endpoint /stats que devuelve el número de secuencias mutantes y no mutantes, así como el ratio entre ellas.

Uso de Swagger

Para interactuar con la API, puedes utilizar Swagger UI.

    Abrir Swagger UI:
        Navega a http://localhost:8080/swagger-ui/index.html en tu navegador.

    Verificar ADN:
        Selecciona el endpoint POST /mutant.
        Pega el JSON de ejemplo en el campo de entrada.
        Haz clic en "Execute" para enviar la solicitud.

    Obtener Estadísticas:
        Selecciona el endpoint GET /stats.
        Haz clic en "Execute" para obtener las estadísticas.

Ejemplos de Solicitudes
Ejemplo de mutante
{
  "dna": [
    "ATGCGA",
    "CAGTGC",
    "TTATGT",
    "AGAAGG",
    "CCCCTA",
    "TCACTG"
  ]
}
Ejemplo de no mutante
{
  "dna": [
    "ATGCGA",
    "CAGTGC",
    "TTATGT",
    "AGACGG",
    "GCGTCA",
    "TCACTG"
  ]
}
Esta API proporciona una solución eficiente para verificar secuencias de ADN y obtener estadísticas sobre las verificaciones realizadas. Utiliza H2 Database para almacenar los resultados y Swagger para facilitar la interacción con la API.