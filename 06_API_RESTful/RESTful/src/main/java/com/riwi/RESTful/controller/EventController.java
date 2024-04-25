package com.riwi.RESTful.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.riwi.RESTful.entity.Events;
import com.riwi.RESTful.services.service_abstract.IEventServices;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/events")
@AllArgsConstructor
public class EventController {
    
    @Autowired
    private final IEventServices eventServices;

    @GetMapping
    public ResponseEntity<List<Events>> getAll(){
        return ResponseEntity.ok(this.eventServices.listAll());
    }

    @PostMapping()
    public ResponseEntity<Events> create(@RequestBody Events events){
        if (events.getCapacity() < 1) {
            return null;
        }if (events.getDate().isBefore(LocalDate.now())) {
            return null;
        }
         return ResponseEntity.ok(this.eventServices.create(events));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Events> findById(@PathVariable String id){
        return ResponseEntity.ok(this.eventServices.findbyId(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Events> update(@PathVariable String id, @RequestBody Events events){
        return ResponseEntity.ok(this.eventServices.update(id, events));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        this.eventServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/page/{page}")
    public ResponseEntity<Page<Events>> showViewGetAll(@PathVariable Integer page) {
        return ResponseEntity.ok(this.eventServices.findPaginated(PageRequest.of(page, 3)));
    }

}
