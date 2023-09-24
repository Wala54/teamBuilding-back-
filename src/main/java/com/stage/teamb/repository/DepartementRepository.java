package com.stage.teamb.repository;

import com.stage.teamb.models.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {

    List<Departement> findAllByEntreprise_Id(Long id);
}
