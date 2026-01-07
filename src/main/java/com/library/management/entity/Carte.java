package com.library.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "carti")
@Getter
@Setter
@NoArgsConstructor
public class Carte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carte")
    private Integer id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "titlu", nullable = false, length = 100)
    private String titlu;

    @NotNull
    @Min(0)
    @Column(name = "stoc", nullable = false)
    private Integer stoc;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_autor", nullable = false)
    private Autor autor;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_categorie", nullable = false)
    private Categorie categorie;

    @JsonIgnore
    @OneToMany(mappedBy = "carte")
    private List<Imprumut> imprumuturi = new ArrayList<>();
}



