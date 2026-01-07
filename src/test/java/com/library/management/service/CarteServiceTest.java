package com.library.management.service;

import com.library.management.dto.CarteRequest;
import com.library.management.entity.Autor;
import com.library.management.entity.Carte;
import com.library.management.entity.Categorie;
import com.library.management.repository.AutorRepository;
import com.library.management.repository.CarteRepository;
import com.library.management.repository.CategorieRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarteServiceTest {

    @Mock
    private CarteRepository carteRepository;

    @Mock
    private AutorRepository autorRepository;

    @Mock
    private CategorieRepository categorieRepository;

    @InjectMocks
    private CarteService carteService;

    @Test
    void cautaDupaTitluReturnsList() {
        Carte carte = new Carte();
        carte.setId(1);
        carte.setTitlu("Test");

        when(carteRepository.findByTitluContainingIgnoreCase("Test")).thenReturn(List.of(carte));

        List<Carte> result = carteService.cautaDupaTitlu("Test");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitlu()).isEqualTo("Test");
    }

    @Test
    void createSavesCarte() {
        CarteRequest request = new CarteRequest();
        request.setTitlu("Carte Noua");
        request.setStoc(3);
        request.setAutorId(1);
        request.setCategorieId(2);

        Autor autor = new Autor();
        autor.setId(1);
        Categorie categorie = new Categorie();
        categorie.setId(2);

        when(autorRepository.findById(1)).thenReturn(Optional.of(autor));
        when(categorieRepository.findById(2)).thenReturn(Optional.of(categorie));
        when(carteRepository.save(any(Carte.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Carte result = carteService.create(request);

        assertThat(result.getTitlu()).isEqualTo("Carte Noua");
        assertThat(result.getAutor()).isEqualTo(autor);
        assertThat(result.getCategorie()).isEqualTo(categorie);
    }

    @Test
    void updateUpdatesCarte() {
        Carte existing = new Carte();
        existing.setId(10);
        existing.setTitlu("Vechi");
        existing.setStoc(1);

        Autor autor = new Autor();
        autor.setId(1);
        Categorie categorie = new Categorie();
        categorie.setId(2);

        CarteRequest request = new CarteRequest();
        request.setTitlu("Actualizata");
        request.setStoc(5);
        request.setAutorId(1);
        request.setCategorieId(2);

        when(carteRepository.findById(10)).thenReturn(Optional.of(existing));
        when(autorRepository.findById(1)).thenReturn(Optional.of(autor));
        when(categorieRepository.findById(2)).thenReturn(Optional.of(categorie));
        when(carteRepository.save(any(Carte.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Carte result = carteService.update(10, request);

        assertThat(result.getTitlu()).isEqualTo("Actualizata");
        assertThat(result.getStoc()).isEqualTo(5);
    }

    @Test
    void getByIdNotFoundThrows() {
        when(carteRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> carteService.getById(99))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Cartea nu a fost gasita");
    }
}



