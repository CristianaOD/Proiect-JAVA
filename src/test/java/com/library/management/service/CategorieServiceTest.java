package com.library.management.service;

import com.library.management.dto.CategorieRequest;
import com.library.management.entity.Categorie;
import com.library.management.repository.CategorieRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CategorieServiceTest {

    @Mock
    private CategorieRepository categorieRepository;

    @InjectMocks
    private CategorieService categorieService;

    @Test
    void getAllReturnsList() {
        Categorie categorie = new Categorie();
        categorie.setId(1);
        categorie.setNume("Fictiune");

        when(categorieRepository.findAll()).thenReturn(List.of(categorie));

        List<Categorie> result = categorieService.getAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNume()).isEqualTo("Fictiune");
    }

    @Test
    void getByIdNotFoundThrows() {
        when(categorieRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> categorieService.getById(1))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Categoria nu a fost gasita");
    }

    @Test
    void createSavesCategorie() {
        CategorieRequest request = new CategorieRequest();
        request.setNume("Istorie");

        when(categorieRepository.save(any(Categorie.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Categorie result = categorieService.create(request);

        ArgumentCaptor<Categorie> captor = ArgumentCaptor.forClass(Categorie.class);
        verify(categorieRepository).save(captor.capture());
        assertThat(captor.getValue().getNume()).isEqualTo("Istorie");
        assertThat(result.getNume()).isEqualTo("Istorie");
    }

    @Test
    void updateUpdatesCategorie() {
        Categorie existing = new Categorie();
        existing.setId(2);
        existing.setNume("Vechi");

        when(categorieRepository.findById(2)).thenReturn(Optional.of(existing));
        when(categorieRepository.save(any(Categorie.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CategorieRequest request = new CategorieRequest();
        request.setNume("Actualizat");

        Categorie result = categorieService.update(2, request);

        assertThat(result.getNume()).isEqualTo("Actualizat");
    }

    @Test
    void deleteRemovesCategorie() {
        Categorie existing = new Categorie();
        existing.setId(3);

        when(categorieRepository.findById(3)).thenReturn(Optional.of(existing));

        categorieService.delete(3);

        verify(categorieRepository).delete(existing);
    }
}



