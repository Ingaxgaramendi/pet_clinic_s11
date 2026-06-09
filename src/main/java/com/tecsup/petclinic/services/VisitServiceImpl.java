package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.repositories.PetRepository;
import com.tecsup.petclinic.repositories.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;
    private final PetRepository petRepository;

    @Override
    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    @Override
    public Visit findById(Long id) {
        return visitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visit not found"));
    }

    @Override
    public Visit save(Visit visit) {

        if (visit.getPet() == null || visit.getPet().getId() == null) {
            throw new RuntimeException("Pet is required");
        }

        Integer petId = visit.getPet().getId();

        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        visit.setPet(pet);

        return visitRepository.save(visit);
    }

    @Override
    public Visit update(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public void delete(Long id) {
        visitRepository.deleteById(id);
    }
}