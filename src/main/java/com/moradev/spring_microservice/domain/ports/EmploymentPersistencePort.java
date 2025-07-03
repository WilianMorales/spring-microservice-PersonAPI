package com.moradev.spring_microservice.domain.ports;

import com.moradev.spring_microservice.domain.model.EmploymentModel;

import java.util.List;
import java.util.Optional;

public interface EmploymentPersistencePort {

    EmploymentModel addEmployment(EmploymentModel employmentModel);

    Optional<EmploymentModel> findById(Long id);

    List<EmploymentModel> getEmploymentsByPersonId(Long personId);

    void deleteEmployment(Long id);

    EmploymentModel updateEmployment(EmploymentModel employment);
}
