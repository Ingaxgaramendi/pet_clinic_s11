package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.services.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService service;

    @GetMapping
    public List<Visit> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Visit findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Visit> create(@RequestBody Visit visit) {
        return ResponseEntity.status(201).body(service.save(visit));
    }

    @PutMapping("/{id}")
    public Visit update(@PathVariable Long id, @RequestBody Visit visit) {
        visit.setId(id);
        return service.update(visit);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}