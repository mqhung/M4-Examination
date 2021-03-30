package com.module4.exam.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IService<T>{
    Page<T> showAll(Pageable pageable);
    T findById(Long id);
    T save(T t);
    void delete(Long id);
}
