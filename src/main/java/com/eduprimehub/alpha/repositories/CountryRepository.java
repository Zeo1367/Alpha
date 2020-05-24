package com.eduprimehub.alpha.repositories;

import com.eduprimehub.alpha.models.entities.Country;
import com.eduprimehub.alpha.models.entities.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {

    List<Country> findAllBySportAndActive(Sport sport,Boolean active);
}
