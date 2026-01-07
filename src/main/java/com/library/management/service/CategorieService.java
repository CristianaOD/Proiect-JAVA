package com.library.management.service;

import com.library.management.dto.CategorieRequest;
import com.library.management.entity.Categorie;
import com.library.management.repository.CategorieRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CategorieService {

    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public List<Categorie> getAll() {
        return categorieRepository.findAll();
    }

    public Categorie getById(Integer id) {
        return categorieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria nu a fost gasita"));
    }

    public Categorie create(CategorieRequest request) {
        Categorie categorie = new Categorie();
        categorie.setNume(request.getNume());
        return categorieRepository.save(categorie);
    }

    public Categorie update(Integer id, CategorieRequest request) {
        Categorie categorie = getById(id);
        categorie.setNume(request.getNume());
        return categorieRepository.save(categorie);
    }

    public void delete(Integer id) {
        Categorie categorie = getById(id);
        categorieRepository.delete(categorie);
    }
}



