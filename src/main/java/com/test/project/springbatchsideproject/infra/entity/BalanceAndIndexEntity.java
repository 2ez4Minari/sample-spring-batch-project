package com.test.project.springbatchsideproject.infra.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.time.LocalDate;


@Getter
@Entity
@Table(name = "BALANCE_AND_INDEX", schema = "rwd")
public class BalanceAndIndexEntity {

    @Id
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "CIF")
    private String CIF;

    @Column(name = "MOBILE_NUMBER", nullable = false)
    private String mobileNumber;

    @Column(name = "ACCOUNT_OPENING_DATE")
    private Date accountOpeningDate;

}
