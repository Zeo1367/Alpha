package com.eduprimehub.alpha.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sport")
@Data
public class Sport extends BaseEntity{

    @Column(name = "sports_name",columnDefinition = "varchar(20)", nullable = false)
    private String sportsName;
}
