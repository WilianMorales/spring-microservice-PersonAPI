package com.moradev.spring_microservice.domain.ports;

import com.moradev.spring_microservice.domain.model.PersonModel;

import java.util.List;
import java.util.Optional;

public interface PersonPersistencePort {

    PersonModel addPerson(PersonModel personModel);

    PersonModel updatePerson(PersonModel personModel);

    void deletePerson(Long id);

    List<PersonModel> getPersons();

    Optional<PersonModel> getById(Long id);

}
