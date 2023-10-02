package com.expenses.jonsnow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseRepo<T,ID> extends JpaRepository<T,ID>, PagingAndSortingRepository<T,ID> {
}
