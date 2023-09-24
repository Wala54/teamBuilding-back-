package com.stage.teamb.repository;

import com.stage.teamb.models.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepsitory extends JpaRepository<Entreprise, Long> {

}
