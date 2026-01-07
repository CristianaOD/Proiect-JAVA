package com.library.management.service;

import com.library.management.dto.AdresaRequest;
import com.library.management.dto.UtilizatorRequest;
import com.library.management.entity.Adresa;
import com.library.management.entity.Utilizator;
import com.library.management.repository.UtilizatorRepository;
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
class UtilizatorServiceTest {

    @Mock
    private UtilizatorRepository utilizatorRepository;

    @InjectMocks
    private UtilizatorService utilizatorService;

    @Test
    void createSavesUtilizator() {
        UtilizatorRequest request = createRequest("Ana", "Pop", "ana@test.com", "0700");

        when(utilizatorRepository.save(any(Utilizator.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Utilizator result = utilizatorService.create(request);

        assertThat(result.getNume()).isEqualTo("Ana");
        assertThat(result.getAdresa().getOras()).isEqualTo("Bucuresti");
    }

    @Test
    void createWithoutAdresaThrows() {
        UtilizatorRequest request = new UtilizatorRequest();
        request.setNume("Ana");
        request.setPrenume("Pop");
        request.setEmail("ana@test.com");
        request.setTelefon("0700");
        request.setAdresa(null);

        assertThatThrownBy(() -> utilizatorService.create(request))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Adresa este obligatorie");
    }

    @Test
    void updateUpdatesExisting() {
        Utilizator existing = new Utilizator();
        existing.setId(1);
        existing.setAdresa(new Adresa());

        when(utilizatorRepository.findById(1)).thenReturn(Optional.of(existing));
        when(utilizatorRepository.save(any(Utilizator.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UtilizatorRequest request = createRequest("Ion", "Ionescu", "ion@test.com", "0711");

        Utilizator result = utilizatorService.update(1, request);

        assertThat(result.getPrenume()).isEqualTo("Ionescu");
        assertThat(result.getAdresa().getStrada()).isEqualTo("Strada 1");
    }

    @Test
    void getByIdNotFoundThrows() {
        when(utilizatorRepository.findById(9)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> utilizatorService.getById(9))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Utilizatorul nu a fost gasit");
    }

    private UtilizatorRequest createRequest(String nume, String prenume, String email, String telefon) {
        AdresaRequest adresaRequest = new AdresaRequest();
        adresaRequest.setOras("Bucuresti");
        adresaRequest.setStrada("Strada 1");
        adresaRequest.setCodPostal("010101");

        UtilizatorRequest request = new UtilizatorRequest();
        request.setNume(nume);
        request.setPrenume(prenume);
        request.setEmail(email);
        request.setTelefon(telefon);
        request.setAdresa(adresaRequest);
        return request;
    }
}



