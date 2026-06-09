package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.repositories.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository repository;

    @Override
    public List<Specialty> findAll() {
        return repository.findAll();
    }

    @Override
    public Specialty findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialty not found"));
    }

    @Override
    public Specialty save(Specialty specialty) {
        return repository.save(specialty);
    }

    @Override
    public Specialty update(Specialty specialty) {
        return repository.save(specialty);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}