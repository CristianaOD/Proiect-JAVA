# Documentatie utilizare API

## Pornire aplicatie
1) Porneste MySQL si asigura-te ca schema `library` exista.
2) Verifica `src/main/resources/application.properties` pentru datele de conectare.
3) Ruleaza aplicatia:
   - IntelliJ: Run `LibraryManagementApplication`
   - CLI: `mvnw.cmd spring-boot:run`

## Swagger
- UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## Endpoint-uri
Base URL: `http://localhost:8080`

### Utilizatori
- `GET /utilizatori`
- `GET /utilizatori/{id}`
- `POST /utilizatori`
- `PUT /utilizatori/{id}`
- `DELETE /utilizatori/{id}`

Exemplu body (POST/PUT):
```json
{
  "nume": "Pop",
  "prenume": "Ana",
  "email": "ana.pop@email.com",
  "telefon": "0700000000",
  "adresa": {
    "oras": "Bucuresti",
    "strada": "Strada 1",
    "codPostal": "010101"
  }
}
```

### Autori
- `GET /autori`
- `GET /autori/{id}`
- `POST /autori`
- `PUT /autori/{id}`
- `DELETE /autori/{id}`

Body:
```json
{ "nume": "Mihai Eminescu" }
```

### Categorii
- `GET /categorii`
- `GET /categorii/{id}`
- `POST /categorii`
- `PUT /categorii/{id}`
- `DELETE /categorii/{id}`

Body:
```json
{ "nume": "Fictiune" }
```

### Carti
- `GET /carti`
- `GET /carti/{id}`
- `GET /carti/cautare?titlu=...`
- `POST /carti`
- `PUT /carti/{id}`
- `DELETE /carti/{id}`

Body:
```json
{
  "titlu": "Luceafarul",
  "stoc": 5,
  "autorId": 1,
  "categorieId": 1
}
```

### Imprumuturi
- `GET /imprumuturi`
- `GET /imprumuturi/{id}`
- `POST /imprumuturi`
- `POST /imprumuturi/{id}/returnare`
- `GET /imprumuturi/istoric/{utilizatorId}`

Body (imprumut):
```json
{
  "utilizatorId": 1,
  "carteId": 1
}
```

## Flux recomandat
1) Creeaza autor si categorie.
2) Creeaza carte cu `autorId` si `categorieId`.
3) Creeaza utilizator cu adresa.
4) Fa imprumut, apoi returnare.
5) Verifica istoricul utilizatorului.

## Postman
Importa colectia din `docs/postman/library-management.postman_collection.json`.
Seteaza variabila `baseUrl` la `http://localhost:8080`.
