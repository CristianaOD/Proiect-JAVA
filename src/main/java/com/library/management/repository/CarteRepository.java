package com.library.management.repository;

import com.library.management.entity.Carte;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteRepository extends JpaRepository<Carte, Integer> {

    List<Carte> findByTitluContainingIgnoreCase(String titlu);
}



