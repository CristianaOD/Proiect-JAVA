package com.library.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "imprumuturi")
@Getter
@Setter
@NoArgsConstructor
public class Imprumut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private Utilizator utilizator;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_carte", nullable = false)
    private Carte carte;

    @NotNull
    @Column(name = "data_imprumut", nullable = false)
    private LocalDateTime dataImprumut;

    @Column(name = "data_retur")
    private LocalDateTime dataRetur;
}



