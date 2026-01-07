package com.library.management.service;

import com.library.management.dto.AdresaRequest;
import com.library.management.dto.UtilizatorRequest;
import com.library.management.entity.Adresa;
import com.library.management.entity.Utilizator;
import com.library.management.repository.UtilizatorRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UtilizatorService {

    private final UtilizatorRepository utilizatorRepository;

    public UtilizatorService(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }

    public List<Utilizator> getAll() {
        return utilizatorRepository.findAll();
    }

    public Utilizator getById(Integer id) {
        return utilizatorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilizatorul nu a fost gasit"));
    }

    public Utilizator create(UtilizatorRequest request) {
        Utilizator utilizator = new Utilizator();
        aplicaDate(utilizator, request);
        return utilizatorRepository.save(utilizator);
    }

    public Utilizator update(Integer id, UtilizatorRequest request) {
        Utilizator utilizator = getById(id);
        aplicaDate(utilizator, request);
        return utilizatorRepository.save(utilizator);
    }

    public void delete(Integer id) {
        Utilizator utilizator = getById(id);
        utilizatorRepository.delete(utilizator);
    }

    private void aplicaDate(Utilizator utilizator, UtilizatorRequest request) {
        utilizator.setNume(request.getNume());
        utilizator.setPrenume(request.getPrenume());
        utilizator.setEmail(request.getEmail());
        utilizator.setTelefon(request.getTelefon());

        AdresaRequest adresaRequest = request.getAdresa();
        if (adresaRequest == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adresa este obligatorie");
        }

        Adresa adresa = utilizator.getAdresa();
        if (adresa == null) {
            adresa = new Adresa();
            utilizator.setAdresa(adresa);
        }
        adresa.setOras(adresaRequest.getOras());
        adresa.setStrada(adresaRequest.getStrada());
        adresa.setCodPostal(adresaRequest.getCodPostal());
    }
}



