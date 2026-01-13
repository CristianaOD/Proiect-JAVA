# Sistem de management al bibliotecii

Aplicație backend pentru gestionarea utilizatorilor, cărților și împrumuturilor, cu evidența stocului și istoricului.

## Cerințe și MVP
Documentul cu cerințe și funcționalități MVP este în `docs/requirements.md`.

## Tehnologii
- Java 21
- Spring Boot 3
- Spring Data JPA + Validation
- MySQL (runtime)
- H2 (test)
- Swagger/OpenAPI (springdoc)

## Model date (entități)
- Adresa, Autor, Categorie, Carte, Imprumut, Utilizator
- Relații: Utilizator->Adresa (OneToOne), Autor->Carte (OneToMany), Categorie->Carte (OneToMany), Utilizator->Imprumut (OneToMany), Carte->Imprumut (OneToMany)

## Configurare bază de date
1) Creează schema `library` în MySQL.
2) Verifică datele în `src/main/resources/application.properties`:
   - `spring.datasource.url`
   - `spring.datasource.username`
   - `spring.datasource.password`
3) `spring.jpa.hibernate.ddl-auto=validate`

## Rulare aplicație
```bash
mvnw.cmd spring-boot:run
```

## Swagger
- UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## Teste
```bash
mvnw.cmd test
```
Testele folosesc H2 și profilul `test` din `src/test/resources/application-test.properties`.

## Postman
Colecția este în `docs/postman/library-management.postman_collection.json`.
Setează variabila `baseUrl` la `http://localhost:8080`.

## Documentație API
Ghidul de utilizare și exemplele de request sunt în `docs/api-usage.md`.
