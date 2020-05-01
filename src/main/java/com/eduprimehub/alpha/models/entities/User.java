package com.eduprimehub.alpha.models.entities;

import com.eduprimehub.alpha.models.enums.Status;
import com.eduprimehub.alpha.models.enums.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
@Data
public class User extends BaseActorEntity {

    @Column(name = "is_premium_user", nullable = false)
    private Boolean isPremiumUser;

    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "user_type", columnDefinition = "varchar(15)", nullable = false)
    private UserType userType;
}
