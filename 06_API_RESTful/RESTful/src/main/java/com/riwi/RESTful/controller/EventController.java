package com.riwi.RESTful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // @PostMapping()
    // // public ResponseEntity<Events> create(@RequestBody Events events){
    // //     if (events.getCapacity() < 0) {
    // //         System.out.println("Invalid capacity");
    // //     }if (events.getDate() < LocalDate.now()) {
            
    // //     }
    //     return ResponseEntity.ok(this.eventServices.create(events));
    // }

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

}
