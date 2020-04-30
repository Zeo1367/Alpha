package com.eduprimehub.alpha.repositories;

import com.eduprimehub.alpha.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByIdAndIsActive(String userId, Boolean isActive);
}
