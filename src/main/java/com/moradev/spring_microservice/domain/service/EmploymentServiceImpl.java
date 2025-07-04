package com.moradev.spring_microservice.domain.service;

import com.moradev.spring_microservice.domain.model.EmploymentModel;
import com.moradev.spring_microservice.domain.ports.EmploymentPersistencePort;
import com.moradev.spring_microservice.domain.ports.EmploymentServicePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class EmploymentServiceImpl implements EmploymentServicePort {

    private final EmploymentPersistencePort employmentPersistencePort;

    @Override
    public EmploymentModel addEmployment(EmploymentModel employmentModel) {
        return employmentPersistencePort.addEmployment(employmentModel);
    }

    @Override
    public List<EmploymentModel> getEmploymentsByPersonId(Long personId) {
        return employmentPersistencePort.getEmploymentsByPersonId(personId);
    }

    @Override
    public void deleteEmployment(Long id) {
        employmentPersistencePort.deleteEmployment(id);
    }

    @Override
    public EmploymentModel findById(Long id) {
        return employmentPersistencePort.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleo no encontrado")); // Puedes usar tu propia excepción aquí
    }

    @Override
    public EmploymentModel updateEmployment(EmploymentModel employment) {
        return employmentPersistencePort.updateEmployment(employment);
    }
}
