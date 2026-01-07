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
class ImprumutServiceTest {

    @Mock
    private ImprumutRepository imprumutRepository;

    @Mock
    private UtilizatorRepository utilizatorRepository;

    @Mock
    private CarteRepository carteRepository;

    @InjectMocks
    private ImprumutService imprumutService;

    @Test
    void imprumutaCarteDecrementeazaStocSiSalveazaImprumut() {
        Utilizator utilizator = new Utilizator();
        utilizator.setId(1);

        Carte carte = new Carte();
        carte.setId(2);
        carte.setStoc(2);

        ImprumutRequest request = new ImprumutRequest();
        request.setUtilizatorId(1);
        request.setCarteId(2);

        when(utilizatorRepository.findById(1)).thenReturn(Optional.of(utilizator));
        when(carteRepository.findById(2)).thenReturn(Optional.of(carte));
        when(imprumutRepository.countByUtilizatorIdAndDataReturIsNull(1)).thenReturn(1);
        when(carteRepository.save(any(Carte.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(imprumutRepository.save(any(Imprumut.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Imprumut result = imprumutService.imprumutaCarte(request);

        assertThat(result.getDataImprumut()).isNotNull();
        assertThat(carte.getStoc()).isEqualTo(1);
        assertThat(result.getUtilizator()).isEqualTo(utilizator);
    }

    @Test
    void imprumutaCarteFailsWhenStocZero() {
        Utilizator utilizator = new Utilizator();
        utilizator.setId(1);

        Carte carte = new Carte();
        carte.setId(2);
        carte.setStoc(0);

        ImprumutRequest request = new ImprumutRequest();
        request.setUtilizatorId(1);
        request.setCarteId(2);

        when(utilizatorRepository.findById(1)).thenReturn(Optional.of(utilizator));
        when(carteRepository.findById(2)).thenReturn(Optional.of(carte));

        assertThatThrownBy(() -> imprumutService.imprumutaCarte(request))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Stoc indisponibil");
    }

    @Test
    void imprumutaCarteFailsWhenLimitReached() {
        Utilizator utilizator = new Utilizator();
        utilizator.setId(1);

        Carte carte = new Carte();
        carte.setId(2);
        carte.setStoc(2);

        ImprumutRequest request = new ImprumutRequest();
        request.setUtilizatorId(1);
        request.setCarteId(2);

        when(utilizatorRepository.findById(1)).thenReturn(Optional.of(utilizator));
        when(carteRepository.findById(2)).thenReturn(Optional.of(carte));
        when(imprumutRepository.countByUtilizatorIdAndDataReturIsNull(1)).thenReturn(5);

        assertThatThrownBy(() -> imprumutService.imprumutaCarte(request))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Limita de 5 carti a fost atinsa");
    }

    @Test
    void returneazaCarteIncrementeazaStoc() {
        Carte carte = new Carte();
        carte.setId(2);
        carte.setStoc(1);

        Imprumut imprumut = new Imprumut();
        imprumut.setId(1);
        imprumut.setCarte(carte);
        imprumut.setDataImprumut(LocalDateTime.now());

        when(imprumutRepository.findById(1)).thenReturn(Optional.of(imprumut));
        when(carteRepository.save(any(Carte.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(imprumutRepository.save(any(Imprumut.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Imprumut result = imprumutService.returneazaCarte(1);

        assertThat(result.getDataRetur()).isNotNull();
        assertThat(carte.getStoc()).isEqualTo(2);
    }

    @Test
    void returneazaCarteFailsWhenAlreadyReturned() {
        Carte carte = new Carte();
        carte.setId(2);
        carte.setStoc(1);

        Imprumut imprumut = new Imprumut();
        imprumut.setId(1);
        imprumut.setCarte(carte);
        imprumut.setDataImprumut(LocalDateTime.now());
        imprumut.setDataRetur(LocalDateTime.now());

        when(imprumutRepository.findById(1)).thenReturn(Optional.of(imprumut));

        assertThatThrownBy(() -> imprumutService.returneazaCarte(1))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Imprumutul este deja returnat");
    }

    @Test
    void istoricUtilizatorNotFoundThrows() {
        when(utilizatorRepository.existsById(9)).thenReturn(false);

        assertThatThrownBy(() -> imprumutService.istoricUtilizator(9))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("Utilizatorul nu a fost gasit");
    }

    @Test
    void istoricUtilizatorReturnsList() {
        Imprumut imprumut = new Imprumut();
        imprumut.setId(1);

        when(utilizatorRepository.existsById(1)).thenReturn(true);
        when(imprumutRepository.findByUtilizatorIdOrderByDataImprumutDesc(1)).thenReturn(List.of(imprumut));

        List<Imprumut> result = imprumutService.istoricUtilizator(1);

        assertThat(result).hasSize(1);
    }
}



