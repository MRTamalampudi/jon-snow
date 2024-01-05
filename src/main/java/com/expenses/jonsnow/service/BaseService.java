package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.TransactionDTO;
import com.expenses.jonsnow.mapper.BaseMapper;
import com.expenses.jonsnow.model.Transaction;
import com.expenses.jonsnow.repository.BaseRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<Entity,DTO,Request> {

    private final BaseRepo<Entity,Long> repo;
    private final BaseMapper<Entity,DTO,Request> mapper;
    public BaseService(BaseRepo<Entity,Long> repo, BaseMapper<Entity,DTO,Request> mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public Page<DTO> index(Specification<Entity> specification, Pageable pageable){
        Page<Entity> entityPage = repo.findAll(specification,pageable);
        List<DTO> dtoList = mapper.mapEntityListtoDTOList(entityPage.getContent());
        return new PageImpl<>(
                dtoList,
                pageable,
                entityPage.getTotalElements()
        );
    }

    public Optional<Entity> findById(Long entityId) {
        return repo.findById(entityId);
    }


    public void deleteById(Long enityId) {
        repo.deleteById(enityId);
    }


    public Entity create(Entity entity) {
        return repo.save(entity);
    }


    public List<Entity> findAll(Specification<Entity> specification) {
        return repo.findAll(specification);
    }

    public void delete(Entity entity){
        repo.delete(entity);
    }

    public void deleteAllById(List<Long> entityIds){
        repo.deleteAllById(entityIds);
    }
}
