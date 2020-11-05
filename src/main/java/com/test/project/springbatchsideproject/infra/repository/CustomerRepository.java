package com.test.project.springbatchsideproject.infra.repository;

import com.test.project.springbatchsideproject.infra.CustomerEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    List<CustomerEntity> findAll();

    @Query(value = "SELECT * FROM CUSTOMER c WHERE c.ADDRESS = :address ",
            nativeQuery = true)
    List<CustomerEntity> findAllUsingNativeQuery(@Param("address") String address);

    Page<CustomerEntity> findAllByName(String name, Pageable pageable);

    Page<CustomerEntity> findAllByAccountOpeningDate(Date dateToday, Pageable pageable);
}


