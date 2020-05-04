package com.eduprimehub.alpha.models.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue
    private Integer id;

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

    /**
     * this method set the createdAt and startedAt value with the current date and time
     * it gets called upon when the entity gets persists for the first time
     */
    @PrePersist
    protected void onCreate() {
        createdAt = new Date().getTime();
    }

    /**
     * this method update the updatedAt value with the current date and time
     * it gets called upon everytime there is an update on the entity
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date().getTime();
    }
}
