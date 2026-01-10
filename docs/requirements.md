# Sistem de management al bibliotecii - Cerințe și MVP

## Prezentare generală
Aplicație backend pentru gestionarea utilizatorilor, cărților și împrumuturilor, cu evidența stocului și a istoricului.

## Cerințe de business (10)
1. Sistemul trebuie să permită crearea utilizatorilor.
2. Sistemul trebuie să permită adăugarea și gestionarea cărților.
3. Fiecare carte trebuie să aparțină unei singure categorii.
4. Fiecare carte trebuie să aibă exact un autor.
5. O carte poate fi împrumutată doar dacă stocul este mai mare decât 0.
6. La împrumutarea unei cărți, stocul de cărți trebuie decrementat.
7. La returnarea unei cărți, stocul de cărți trebuie incrementat.
8. Un utilizator nu poate împrumuta mai mult de 5 cărți simultan.
9. Sistemul trebuie să păstreze istoricul complet al împrumuturilor.
10. Sistemul trebuie să valideze datele introduse (de ex. email valid, stoc >= 0).

## Funcționalități MVP (5)
1. Gestionare utilizatori: creare, actualizare, listare, ștergere.
2. Gestionare cărți: creare carte, asociere autor/categorie, gestiune stoc.
3. Gestionare autori și categorii: CRUD pentru autori și CRUD pentru categorii.
4. Împrumut și returnare: verificare stoc, limită de 5 cărți per utilizator, returnare (incrementare stoc).
5. Căutare și istoric: căutare cărți, istoric împrumuturi per utilizator.
