package com.library.management.service;

import com.library.management.dto.CarteRequest;
import com.library.management.entity.Autor;
import com.library.management.entity.Carte;
import com.library.management.entity.Categorie;
import com.library.management.repository.AutorRepository;
import com.library.management.repository.CarteRepository;
import com.library.management.repository.CategorieRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CarteService {

    private final CarteRepository carteRepository;
    private final AutorRepository autorRepository;
    private final CategorieRepository categorieRepository;

    public CarteService(CarteRepository carteRepository,
                        AutorRepository autorRepository,
                        CategorieRepository categorieRepository) {
        this.carteRepository = carteRepository;
        this.autorRepository = autorRepository;
        this.categorieRepository = categorieRepository;
    }

    public List<Carte> getAll() {
        return carteRepository.findAll();
    }

    public Carte getById(Integer id) {
        return carteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartea nu a fost gasita"));
    }

    public List<Carte> cautaDupaTitlu(String titlu) {
        return carteRepository.findByTitluContainingIgnoreCase(titlu);
    }

    public Carte create(CarteRequest request) {
        Autor autor = autorRepository.findById(request.getAutorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autorul nu a fost gasit"));
        Categorie categorie = categorieRepository.findById(request.getCategorieId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria nu a fost gasita"));

        Carte carte = new Carte();
        carte.setTitlu(request.getTitlu());
        carte.setStoc(request.getStoc());
        carte.setAutor(autor);
        carte.setCategorie(categorie);
        return carteRepository.save(carte);
    }

    public Carte update(Integer id, CarteRequest request) {
        Carte carte = getById(id);
        Autor autor = autorRepository.findById(request.getAutorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autorul nu a fost gasit"));
        Categorie categorie = categorieRepository.findById(request.getCategorieId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria nu a fost gasita"));

        carte.setTitlu(request.getTitlu());
        carte.setStoc(request.getStoc());
        carte.setAutor(autor);
        carte.setCategorie(categorie);
        return carteRepository.save(carte);
    }

    public void delete(Integer id) {
        Carte carte = getById(id);
        carteRepository.delete(carte);
    }
}



