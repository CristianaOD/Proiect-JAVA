package com.library.management.service;

import com.library.management.dto.ImprumutRequest;
import com.library.management.entity.Carte;
import com.library.management.entity.Imprumut;
import com.library.management.entity.Utilizator;
import com.library.management.repository.CarteRepository;
import com.library.management.repository.ImprumutRepository;
import com.library.management.repository.UtilizatorRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ImprumutService {

    private final ImprumutRepository imprumutRepository;
    private final UtilizatorRepository utilizatorRepository;
    private final CarteRepository carteRepository;

    public ImprumutService(ImprumutRepository imprumutRepository,
                           UtilizatorRepository utilizatorRepository,
                           CarteRepository carteRepository) {
        this.imprumutRepository = imprumutRepository;
        this.utilizatorRepository = utilizatorRepository;
        this.carteRepository = carteRepository;
    }

    public List<Imprumut> getAll() {
        return imprumutRepository.findAll();
    }

    public Imprumut getById(Integer id) {
        return imprumutRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Imprumutul nu a fost gasit"));
    }

    @Transactional
    public Imprumut imprumutaCarte(ImprumutRequest request) {
        Utilizator utilizator = utilizatorRepository.findById(request.getUtilizatorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilizatorul nu a fost gasit"));
        Carte carte = carteRepository.findById(request.getCarteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartea nu a fost gasita"));

        if (carte.getStoc() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stoc indisponibil");
        }

        Integer imprumuturiActive = imprumutRepository.countByUtilizatorIdAndDataReturIsNull(utilizator.getId());
        if (imprumuturiActive >= 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Limita de 5 carti a fost atinsa");
        }

        carte.setStoc(carte.getStoc() - 1);
        carteRepository.save(carte);

        Imprumut imprumut = new Imprumut();
        imprumut.setUtilizator(utilizator);
        imprumut.setCarte(carte);
        imprumut.setDataImprumut(LocalDateTime.now());
        return imprumutRepository.save(imprumut);
    }

    @Transactional
    public Imprumut returneazaCarte(Integer imprumutId) {
        Imprumut imprumut = getById(imprumutId);
        if (imprumut.getDataRetur() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Imprumutul este deja returnat");
        }

        imprumut.setDataRetur(LocalDateTime.now());
        Carte carte = imprumut.getCarte();
        carte.setStoc(carte.getStoc() + 1);
        carteRepository.save(carte);

        return imprumutRepository.save(imprumut);
    }

    public List<Imprumut> istoricUtilizator(Integer utilizatorId) {
        if (!utilizatorRepository.existsById(utilizatorId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilizatorul nu a fost gasit");
        }
        return imprumutRepository.findByUtilizatorIdOrderByDataImprumutDesc(utilizatorId);
    }
}



