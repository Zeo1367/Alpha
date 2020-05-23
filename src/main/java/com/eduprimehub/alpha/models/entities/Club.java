package com.eduprimehub.alpha.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "club")
@Data
public class Club extends BaseEntity{

    @Column(name = "club_name",columnDefinition = "varchar(30)", nullable = false)
    private String clubName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country", columnDefinition = "varchar(30)", referencedColumnName = "country_name")
    private Country country;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sport", columnDefinition = "varchar(20)", referencedColumnName = "sport_name")
    private Sport sport;
}
