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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "favorite_sport", columnDefinition = "varchar(8)", referencedColumnName = "id")
    private List<Sport> favoriteSports;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "favorite_country", columnDefinition = "varchar(8)", referencedColumnName = "id")
    private List<Country> favoriteCountry;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "favorite_club", columnDefinition = "varchar(8)", referencedColumnName = "id")
    private List<Club> favoriteClub;
}
