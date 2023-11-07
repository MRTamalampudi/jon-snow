package com.expenses.jonsnow.service;

import com.expenses.jonsnow.dto.TransacteeDTO;
import com.expenses.jonsnow.mapper.TransacteeMapper;
import com.expenses.jonsnow.model.Transactee;
import com.expenses.jonsnow.repository.TransacteeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransacteeService implements CRUDService<Transactee, TransacteeDTO> {

    private final TransacteeRepo repo;
    private final TransacteeMapper mapper;

    @Override
    public Page<TransacteeDTO> index(Specification<Transactee> specification, Pageable pageable) {
        Page<Transactee> transactees = repo.findAll(specification,pageable);
        List<TransacteeDTO> transacteeDTOS = mapper.map(transactees.getContent());
        return new PageImpl<>(
                transacteeDTOS,
                pageable,
                transactees.getTotalElements()
        );
    }

    @Override
    public Optional<Transactee> findById(Long entityId) {
        return repo.findById(entityId);
    }

    @Override
    public void deleteById(Long enityId) {
        repo.deleteById(enityId);
    }

    @Override
    public Transactee create(Transactee transactee) {
        return repo.save(transactee);
    }

    @Override
    public List<Transactee> findAll(Specification<Transactee> specification) {
        return repo.findAll(specification);
    }
}
