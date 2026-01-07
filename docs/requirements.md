# Sistem de Management al Bibliotecii - Cerinte si MVP

## Prezentare generala
Aplicatie backend pentru gestionarea utilizatorilor, cartilor si imprumuturilor, cu evidenta stocului si a istoricului.

## Cerinte de business (10)
1. Sistemul trebuie sa permita crearea si gestionarea utilizatorilor.
2. Sistemul trebuie sa permita adaugarea si gestionarea cartilor.
3. Fiecare carte trebuie sa apartina unei singure categorii.
4. Fiecare carte trebuie sa aiba exact un autor.
5. O carte poate fi imprumutata doar daca stocul este mai mare decat 0.
6. La imprumutarea unei carti, stocul trebuie decrementat.
7. La returnarea unei carti, stocul trebuie incrementat.
8. Un utilizator nu poate imprumuta mai mult de 5 carti simultan.
9. Sistemul trebuie sa pastreze istoricul complet al imprumuturilor.
10. Sistemul trebuie sa valideze datele introduse (de ex. email valid, stoc >= 0).

## Functionalitati MVP (5)
1. Gestionare utilizatori: creare, actualizare, listare, stergere.
2. Gestionare carti: creare carte, asociere autor/categorie, gestiune stoc.
3. Gestionare autori si categorii: CRUD pentru autori si CRUD pentru categorii.
4. Imprumut si returnare: verificare stoc, limita de 5 carti per utilizator, returnare.
5. Cautare si istoric: cautare carti, istoric imprumuturi per utilizator.
