package com.eduprimehub.alpha.repositories;

import com.eduprimehub.alpha.models.entities.Sport;
import com.eduprimehub.alpha.models.entities.UserAccessDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {

    List<Sport> findByActive(Boolean active);
    List<Sport> findBySportsName(String sportsName);

    Sport findByIdAndActive(Integer id,Boolean active);
}
