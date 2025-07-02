package com.moradev.spring_microservice.application.rest.mapper;

import com.moradev.spring_microservice.application.rest.model.request.PersonRequest;
import com.moradev.spring_microservice.application.rest.model.response.PersonResponse;
import com.moradev.spring_microservice.domain.model.PersonModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonRestMapper {

    PersonModel mapToPersonModel(PersonRequest personRequest);

    PersonResponse mapToPersonResponse(PersonModel personModel);

    List<PersonResponse> mapToPersonListResponse(List<PersonModel> personModelList);
}
