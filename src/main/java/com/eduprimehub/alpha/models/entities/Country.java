package com.eduprimehub.alpha.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "country")
@Data
public class Country extends BaseEntity{

    @Column(name = "country_name",columnDefinition = "varchar(30)", nullable = false)
    private String countryName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sport", columnDefinition = "varchar(20)", referencedColumnName = "id")
    private Sport sport;
}
