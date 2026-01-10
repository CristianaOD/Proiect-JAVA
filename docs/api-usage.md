# Documentație utilizare API

## Pornire aplicație
1) Pornește MySQL și asigură-te că schema `library` există.
2) Verifică `src/main/resources/application.properties` pentru datele de conectare.
3) Rulează aplicația:
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

### Cărți
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

### Împrumuturi
- `GET /imprumuturi`
- `GET /imprumuturi/{id}`
- `POST /imprumuturi`
- `POST /imprumuturi/{id}/returnare`
- `GET /imprumuturi/istoric/{utilizatorId}`

Body (împrumut):
```json
{
  "utilizatorId": 1,
  "carteId": 1
}
```

## Flux recomandat
1) Creează autor și categorie.
2) Creează carte cu `autorId` și `categorieId`.
3) Creează utilizator cu adresa.
4) Fă împrumut, apoi returnare.
5) Verifică istoricul utilizatorului.

## Postman
Importă colecția din `docs/postman/library-management.postman_collection.json`.
Setează variabila `baseUrl` la `http://localhost:8080`.
