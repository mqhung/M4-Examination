package com.module4.exam.service.nation;

import com.module4.exam.model.Nation;
import com.module4.exam.repository.INationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NationService implements INationService{
    @Autowired
    private INationRepository nationRepository;

    @Override
    public Iterable<Nation> showAll() {
        return nationRepository.findAll();
    }

    @Override
    public Page<Nation> showAll(Pageable pageable) {
        return nationRepository.findAll(pageable);
    }

    @Override
    public Nation findById(Long id) {
        return nationRepository.findById(id).get();
    }

    @Override
    public Nation save(Nation nation) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
