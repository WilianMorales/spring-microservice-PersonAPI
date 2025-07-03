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
import java.util.Optional;

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
    public Optional<EmploymentModel> findById(Long id) {
        return employmentRepository.findById(id)
                .map(employmentMapper::mapToEmploymentModel);
    }

    @Override
    public List<EmploymentModel> getEmploymentsByPersonId(Long personId) {
        List<EmploymentEntity> entities = employmentRepository.findByPersonId(personId);
        return employmentMapper.mapToEmploymentListModel(entities);
    }

    @Override
    public void deleteEmployment(Long id) {
        if (!employmentRepository.existsById(id)) {
            throw new NotFoundException("Empleo no encontrado");
        }
        employmentRepository.deleteById(id);
    }

    @Override
    public EmploymentModel updateEmployment(EmploymentModel employmentModel) {
        EmploymentEntity existing = employmentRepository.findById(employmentModel.getId())
                .orElseThrow(() -> new NotFoundException("Empleo no encontrado"));

        existing.setPosition(employmentModel.getPosition());
        existing.setCompany(employmentModel.getCompany());
        existing.setSalary(employmentModel.getSalary());

        EmploymentEntity updated = employmentRepository.save(existing);
        return employmentMapper.mapToEmploymentModel(updated);
    }
}
