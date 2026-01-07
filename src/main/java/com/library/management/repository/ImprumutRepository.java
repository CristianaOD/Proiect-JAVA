package com.library.management.repository;

import com.library.management.entity.Imprumut;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImprumutRepository extends JpaRepository<Imprumut, Integer> {

    Integer countByUtilizatorIdAndDataReturIsNull(Integer utilizatorId);

    List<Imprumut> findByUtilizatorIdOrderByDataImprumutDesc(Integer utilizatorId);
}



