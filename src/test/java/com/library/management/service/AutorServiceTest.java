package com.library.management.service;

import com.library.management.dto.AutorRequest;
import com.library.management.entity.Autor;
import com.library.management.repository.AutorRepository;
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

// Teste de service cu Mockito pentru logica de business a autorilor
// Activeaza extensia Mockito pentru JUnit
@ExtendWith(MockitoExtension.class)
class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorService autorService;

    @Test
    void getAllReturnsList() {
        Autor autor = new Autor();
        autor.setId(1);
        autor.setNume("Autor Test");

        when(autorRepository.findAll()).thenReturn(List.of(autor));

        List<Autor> result = autorService.getAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getNume()).isEqualTo("Autor Test");
    }

    @Test
    void getByIdNotFoundThrows() {
        when(autorRepository.findById(1)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> autorService.getById(1))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Autorul nu a fost gasit");
    }

    @Test
    void createSavesAutor() {
        AutorRequest request = new AutorRequest();
        request.setNume("Autor Nou");

        when(autorRepository.save(any(Autor.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Autor result = autorService.create(request);

        ArgumentCaptor<Autor> captor = ArgumentCaptor.forClass(Autor.class);
        verify(autorRepository).save(captor.capture());
        assertThat(captor.getValue().getNume()).isEqualTo("Autor Nou");
        assertThat(result.getNume()).isEqualTo("Autor Nou");
    }

    @Test
    void updateUpdatesAutor() {
        Autor existing = new Autor();
        existing.setId(2);
        existing.setNume("Autor Vechi");

        when(autorRepository.findById(2)).thenReturn(Optional.of(existing));
        when(autorRepository.save(any(Autor.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AutorRequest request = new AutorRequest();
        request.setNume("Autor Actualizat");

        Autor result = autorService.update(2, request);

        assertThat(result.getNume()).isEqualTo("Autor Actualizat");
    }

    @Test
    void deleteRemovesAutor() {
        Autor existing = new Autor();
        existing.setId(3);

        when(autorRepository.findById(3)).thenReturn(Optional.of(existing));

        autorService.delete(3);

        verify(autorRepository).delete(existing);
    }
}



