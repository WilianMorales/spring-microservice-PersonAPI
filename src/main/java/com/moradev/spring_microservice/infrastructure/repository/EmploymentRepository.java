package com.moradev.spring_microservice.infrastructure.repository;

import com.moradev.spring_microservice.infrastructure.entity.EmploymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentRepository extends JpaRepository<EmploymentEntity, Long> {
    List<EmploymentEntity> findByPersonId(Long personId);
}
