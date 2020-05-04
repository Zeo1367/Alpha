package com.eduprimehub.alpha.models.entities;

import com.eduprimehub.alpha.models.enums.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
@Data
public class User extends BaseActorEntity implements Serializable {

    @Column(name = "username", columnDefinition = "varchar(20)", nullable = false)
    private String userName;

    @Column(name = "user_type", columnDefinition = "varchar(50)", nullable = false)
    private String userType;
}
