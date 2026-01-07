package com.library.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "utilizatori")
@Getter
@Setter
@NoArgsConstructor
public class Utilizator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 45)
    @Column(name = "nume", nullable = false, length = 45)
    private String nume;

    @NotBlank
    @Size(max = 45)
    @Column(name = "prenume", nullable = false, length = 45)
    private String prenume;

    @NotBlank
    @Email
    @Size(max = 45)
    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @NotBlank
    @Size(max = 45)
    @Column(name = "telefon", nullable = false, unique = true, length = 45)
    private String telefon;

    @NotNull
    @Valid
    @OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_adresa", nullable = false)
    private Adresa adresa;

    @JsonIgnore
    @OneToMany(mappedBy = "utilizator")
    private List<Imprumut> imprumuturi = new ArrayList<>();
}



