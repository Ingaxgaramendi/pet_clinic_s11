package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Specialty;

import java.util.List;

public interface SpecialtyService {
    List<Specialty> findAll();
    Specialty findById(Integer id);
    Specialty save(Specialty specialty);
    Specialty update(Specialty specialty);
    void delete(Integer id);
}