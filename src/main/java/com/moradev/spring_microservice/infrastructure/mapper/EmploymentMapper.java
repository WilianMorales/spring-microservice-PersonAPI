package com.moradev.spring_microservice.infrastructure.mapper;

import com.moradev.spring_microservice.domain.model.EmploymentModel;
import com.moradev.spring_microservice.infrastructure.entity.EmploymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmploymentMapper {

    @Mapping(source = "person.id", target = "personId")
    EmploymentModel mapToEmploymentModel(EmploymentEntity entity);

    @Mapping(source = "personId", target = "person.id")
    EmploymentEntity mapToEmploymentEntity(EmploymentModel model);

    List<EmploymentModel> mapToEmploymentListModel(List<EmploymentEntity> entities);
}
