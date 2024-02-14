package com.expenses.jonsnow.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<Entity,DTO,Request> {
    List<DTO> mapEntityListtoDTOList(List<Entity> entities);
    DTO mapEntityToDTO(Entity entity);
    Entity mapRequestToEntity(Request request);
    void mapRequestToEntity(Request request, @MappingTarget Entity entity);
}
