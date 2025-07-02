package com.moradev.spring_microservice.infrastructure.adapters;

import com.moradev.spring_microservice.domain.exception.NotFoundException;
import com.moradev.spring_microservice.domain.model.EmploymentModel;
import com.moradev.spring_microservice.domain.ports.EmploymentPersistencePort;
import com.moradev.spring_microservice.infrastructure.entity.EmploymentEntity;
import com.moradev.spring_microservice.infrastructure.entity.PersonEntity;
import com.moradev.spring_microservice.infrastructure.mapper.EmploymentMapper;
import com.moradev.spring_microservice.infrastructure.repository.EmploymentRepository;
import com.moradev.spring_microservice.infrastructure.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmploymentJpaAdapter implements EmploymentPersistencePort {

    private final EmploymentRepository employmentRepository;
    private final EmploymentMapper employmentMapper;
    private final PersonRepository personRepository;

    @Override
    public EmploymentModel addEmployment(EmploymentModel employmentModel) {
        PersonEntity person = personRepository.findById(employmentModel.getPersonId())
                .orElseThrow(() -> new NotFoundException("Persona no encontrada"));

        EmploymentEntity entity = employmentMapper.mapToEmploymentEntity(employmentModel);
        entity.setPerson(person);

        EmploymentEntity saved = employmentRepository.save(entity);
        return employmentMapper.mapToEmploymentModel(saved);
    }

    @Override
    public List<EmploymentModel> getEmploymentsByPersonId(Long personId) {
        List<EmploymentEntity> entities = employmentRepository.findByPersonId(personId);
        return employmentMapper.mapToEmploymentListModel(entities);
    }

    @Override
    public void deleteEmployment(Long id) {
        employmentRepository.deleteById(id);
    }
}
