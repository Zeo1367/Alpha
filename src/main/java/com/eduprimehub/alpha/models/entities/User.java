package com.eduprimehub.alpha.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
@Data
public class User extends BaseActorEntity implements Serializable {

    @Column(name = "username", columnDefinition = "varchar(20)", nullable = false)
    private String userName;

    @Column(name = "user_type", columnDefinition = "varchar(50)", nullable = false)
    private String userType;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "favorite_sports", columnDefinition = "int", referencedColumnName = "id")
    private List<Sport> favoriteSports;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "favorite_countries", columnDefinition = "int", referencedColumnName = "id")
    private List<Country> favoriteCountry;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "favorite_clubs", columnDefinition = "int", referencedColumnName = "id")
    private List<Club> favoriteClub;
}
