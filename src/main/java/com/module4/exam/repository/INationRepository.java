package com.module4.exam.repository;

import com.module4.exam.model.Nation;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface INationRepository extends PagingAndSortingRepository<Nation, Long> {

}
