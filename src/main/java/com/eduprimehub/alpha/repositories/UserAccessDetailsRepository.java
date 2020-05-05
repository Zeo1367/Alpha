package com.eduprimehub.alpha.repositories;

import com.eduprimehub.alpha.models.entities.User;
import com.eduprimehub.alpha.models.entities.UserAccessDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccessDetailsRepository extends JpaRepository<UserAccessDetails, Long> {

    /**
     * @param uuid              unique user id
     * @param userAccountStatus status of the user's account
     * @return userAccessDetails if found from the given parameters
     */
    UserAccessDetails findUserAccessDetailsByUuidAndAndUserAccountStatus(User uuid, String userAccountStatus);
}
