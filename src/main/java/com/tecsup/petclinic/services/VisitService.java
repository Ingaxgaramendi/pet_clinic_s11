package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;

import java.util.List;

public interface VisitService {
    List<Visit> findAll();
    Visit findById(Long id);
    Visit save(Visit visit);
    Visit update(Visit visit);
    void delete(Long id);
}