package com.moradev.spring_microservice.domain.ports;

import com.moradev.spring_microservice.domain.model.EmploymentModel;

import java.util.List;

public interface EmploymentServicePort {

    EmploymentModel addEmployment(EmploymentModel employmentModel);

    List<EmploymentModel> getEmploymentsByPersonId(Long personId);

    void deleteEmployment(Long id);

    EmploymentModel findById(Long id);

    EmploymentModel updateEmployment(EmploymentModel employment);
}
