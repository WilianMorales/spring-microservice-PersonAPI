package com.moradev.spring_microservice.infrastructure.mapper;

import com.moradev.spring_microservice.domain.model.EmploymentModel;
import com.moradev.spring_microservice.infrastructure.entity.EmploymentEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmploymentMapper {

    @Mapping(source = "person.id", target = "personId")
    EmploymentModel mapToEmploymentModel(EmploymentEntity entity);

    @InheritInverseConfiguration
    @Mapping(source = "personId", target = "person.id", ignore = true)
    EmploymentEntity mapToEmploymentEntity(EmploymentModel model);

    List<EmploymentModel> mapToEmploymentListModel(List<EmploymentEntity> entities);
}
