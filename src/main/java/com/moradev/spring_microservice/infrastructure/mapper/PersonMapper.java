package com.moradev.spring_microservice.infrastructure.mapper;

import com.moradev.spring_microservice.domain.model.PersonModel;
import com.moradev.spring_microservice.infrastructure.entity.PersonEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonModel mapToPersonModel(PersonEntity personEntity);

    PersonEntity mapToPersonEntity(PersonModel personModel);

    List<PersonModel> mapToPersonListModel(List<PersonEntity> personEntityList);

}
