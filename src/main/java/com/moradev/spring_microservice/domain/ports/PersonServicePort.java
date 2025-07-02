package com.moradev.spring_microservice.domain.ports;

import com.moradev.spring_microservice.domain.exception.BussinessException;
import com.moradev.spring_microservice.domain.model.PersonModel;

import java.util.List;

public interface PersonServicePort {

    PersonModel addPerson(PersonModel personModel);

    PersonModel updatePerson(PersonModel personModel);

    void deletePerson(Long id) throws BussinessException;

    List<PersonModel> getPersons();

    PersonModel getById(Long id) throws BussinessException;

}
