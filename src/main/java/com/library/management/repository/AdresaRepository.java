package com.library.management.repository;

import com.library.management.entity.Adresa;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository JPA pentru Adresa, CRUD este oferit de JpaRepository
public interface AdresaRepository extends JpaRepository<Adresa, Integer> {
}



