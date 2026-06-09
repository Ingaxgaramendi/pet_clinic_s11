package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.entities.Specialty;
import com.tecsup.petclinic.services.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialties")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService service;

    @GetMapping
    public List<Specialty> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Specialty findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Specialty> create(@RequestBody Specialty specialty) {
        return ResponseEntity.status(201).body(service.save(specialty));
    }

    @PutMapping("/{id}")
    public Specialty update(@PathVariable Integer id, @RequestBody Specialty specialty) {
        specialty.setId(id);
        return service.update(specialty);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}