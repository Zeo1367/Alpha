package com.eduprimehub.alpha.models.entities;

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
public class User extends BaseActorEntity{

    @Column(name = "is_active",nullable = false)
    private Boolean isActive;

    @Column(name = "is_premium_user")
    private Boolean isPremiumUser;

    @Column(name = "user_type",columnDefinition = "varchar(20)",nullable = false)
    private UserType userType;
}
