package com.moradev.spring_microservice.domain.service;

import com.moradev.spring_microservice.domain.exception.BussinessException;
import com.moradev.spring_microservice.domain.exception.PersonNotFoundException;
import com.moradev.spring_microservice.domain.model.PersonModel;
import com.moradev.spring_microservice.domain.ports.PersonPersistencePort;
import com.moradev.spring_microservice.domain.ports.PersonServicePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PersonServiceImpl implements PersonServicePort {

    private final PersonPersistencePort personPersistencePort;

    @Override
    public PersonModel addPerson(PersonModel personModel) {
        return this.personPersistencePort.addPerson(personModel);
    }

    @Override
    public PersonModel updatePerson(PersonModel personModel) {
        return this.personPersistencePort.updatePerson(personModel);
    }

    @Override
    public void deletePerson(Long id) throws BussinessException {
        PersonModel person = personPersistencePort.getById(id)
                .orElseThrow(PersonNotFoundException::new);
        personPersistencePort.deletePerson(person.getId());
    }

    @Override
    public List<PersonModel> getPersons() {
        return this.personPersistencePort.getPersons();
    }

    @Override
    public PersonModel getById(Long id) throws BussinessException {
        return personPersistencePort.getById(id)
                .orElseThrow(PersonNotFoundException::new);
    }
}
