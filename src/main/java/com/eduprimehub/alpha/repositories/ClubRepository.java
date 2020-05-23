package com.eduprimehub.alpha.repositories;

import com.eduprimehub.alpha.models.entities.Club;
import com.eduprimehub.alpha.models.entities.Country;
import com.eduprimehub.alpha.models.entities.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club,Long> {

    List<Club> findAllBySport(Sport sport);
    List<Club> findAllBySportAndCountr(Sport sport, Country country);
}

