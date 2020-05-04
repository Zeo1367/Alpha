package com.eduprimehub.alpha.models.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_access_details")
@Data
public class UserAccessDetails extends BaseEntity implements Serializable {

    //todo: foreign key conecpt
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uuid", columnDefinition = "varchar(8)", referencedColumnName = "uuid")
    private User uuid;

    @Column(name = "is_premium_user", nullable = false)
    private Boolean isPremiumUser;

    @Column(columnDefinition = "varchar(40)", nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(30)")
    private String token;

    @Column(columnDefinition = "long")
    private Long tokenExpiryTime;

    @Column(name = "user_account_status", columnDefinition = "varchar(50)", nullable = false)
//    @Enumerated(EnumType.STRING)
    private String userAccountStatus;

    @Column(name = "user_activity_status", columnDefinition = "varchar(50)", nullable = false)
//    @Enumerated(EnumType.STRING)
    private String userActivityStatus;

    @Column(columnDefinition = "char(6)")
    private Integer otp;
}
