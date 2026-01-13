package com.library.management.service;

import com.library.management.dto.AutorRequest;
import com.library.management.entity.Autor;
import com.library.management.repository.AutorRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

// Service pentru logica de business a autorilor
// Marcheaza clasa ca serviciu Spring pentru logica de business
@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> getAll() {
        return autorRepository.findAll();
    }

    public Autor getById(Integer id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autorul nu a fost gasit"));
    }

    public Autor create(AutorRequest request) {
        Autor autor = new Autor();
        autor.setNume(request.getNume());
        return autorRepository.save(autor);
    }

    public Autor update(Integer id, AutorRequest request) {
        Autor autor = getById(id);
        autor.setNume(request.getNume());
        return autorRepository.save(autor);
    }

    public void delete(Integer id) {
        Autor autor = getById(id);
        autorRepository.delete(autor);
    }
}



