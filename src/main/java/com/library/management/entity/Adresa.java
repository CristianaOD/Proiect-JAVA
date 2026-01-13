package com.library.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "adresa")
@Getter
@Setter
@NoArgsConstructor
public class Adresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 45)
    @Column(name = "oras", nullable = false, length = 45)
    private String oras;

    @NotBlank
    @Size(max = 10)
    @Column(name = "strada", nullable = false, length = 10)
    private String strada;

    @Size(max = 10)
    @Column(name = "cod_postal", length = 10)
    private String codPostal;
}



