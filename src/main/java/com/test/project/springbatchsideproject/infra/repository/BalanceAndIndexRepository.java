package com.test.project.springbatchsideproject.infra.repository;

import com.test.project.springbatchsideproject.infra.entity.BalanceAndIndexEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface BalanceAndIndexRepository extends JpaRepository<BalanceAndIndexEntity, Long> {

    Page<BalanceAndIndexEntity> findAllByAccountOpeningDate(Date dateToday, Pageable pageable);
}