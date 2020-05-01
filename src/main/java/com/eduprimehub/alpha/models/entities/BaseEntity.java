package com.eduprimehub.alpha.models.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue
    private String id;

    /**
     * Created UNIX time
     */
    @Column(name = "created_at", columnDefinition = "bigint", nullable = false)
    @CreatedDate
    private Long createdAt;

    /**
     * Last updated UNIX time
     */
    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "bigint", nullable = false)
    private Long updatedAt;
}
