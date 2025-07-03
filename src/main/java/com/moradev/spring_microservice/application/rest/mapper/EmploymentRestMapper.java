package com.moradev.spring_microservice.application.rest.mapper;

import com.moradev.spring_microservice.application.rest.model.request.EmploymentRequest;
import com.moradev.spring_microservice.application.rest.model.response.EmploymentResponse;
import com.moradev.spring_microservice.domain.model.EmploymentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmploymentRestMapper {

    EmploymentModel mapToEmploymentModel(EmploymentRequest employmentRequest);

    EmploymentResponse mapToEmploymentResponse(EmploymentModel employmentModel);

    List<EmploymentResponse> mapToEmploymentListResponse(List<EmploymentModel> employmentModelList);

    @Mapping(target = "id", ignore = true)
    void updateModelFromRequest(EmploymentRequest request, @MappingTarget EmploymentModel model);
}
