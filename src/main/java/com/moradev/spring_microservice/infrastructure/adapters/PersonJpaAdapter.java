package com.moradev.spring_microservice.infrastructure.adapters;

import com.moradev.spring_microservice.domain.exception.NotFoundException;
import com.moradev.spring_microservice.domain.model.PersonModel;
import com.moradev.spring_microservice.domain.ports.PersonPersistencePort;
import com.moradev.spring_microservice.infrastructure.entity.PersonEntity;
import com.moradev.spring_microservice.infrastructure.mapper.PersonMapper;
import com.moradev.spring_microservice.infrastructure.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonJpaAdapter implements PersonPersistencePort {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public PersonModel addPerson(PersonModel personModel) {
        PersonEntity personEntity = personMapper.mapToPersonEntity(personModel);
        PersonEntity personSaved = this.personRepository.save(personEntity);
        return personMapper.mapToPersonModel(personSaved);
    }

    @Override
    public PersonModel updatePerson(PersonModel personModel) {
        return this.addPerson(personModel);
    }

    @Override
    public void deletePerson(Long id) {
        if (!personRepository.existsById(id)) {
            throw new NotFoundException("Person with id " + id + " not found");
        }
        this.personRepository.deleteById(id);
    }

    @Override
    public List<PersonModel> getPersons() {
        return personMapper.mapToPersonListModel(this.personRepository.findAll());
    }

    @Override
    public Optional<PersonModel> getById(Long id) {
        return this.personRepository.findById(id)
                .map(personMapper::mapToPersonModel);
    }
}
