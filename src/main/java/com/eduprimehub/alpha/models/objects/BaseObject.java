package com.eduprimehub.alpha.models.objects;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseObject implements Serializable {

    private Integer id;

    private Long createdAt;

    private Long updatedAt;
}
