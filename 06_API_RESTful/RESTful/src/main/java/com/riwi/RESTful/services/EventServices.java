package com.riwi.RESTful.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.riwi.RESTful.entity.Events;
import com.riwi.RESTful.repository.EventRepository;
import com.riwi.RESTful.services.service_abstract.IEventServices;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventServices implements  IEventServices {

    @Autowired
    private final EventRepository eventRepository;

    @Override
    public Events create(Events events) {
        return this.eventRepository.save(events);
    }

    @Override
    public void delete(String id) {
        Events eventFind = this.eventRepository.findById(id).orElseThrow();
        this.eventRepository.delete(eventFind);
    }

    @Override
    public Events findbyId(String id) {
        return this.eventRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Events> listAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public Events update(String id, Events events) {
        this.eventRepository.findById(id);

        events.setId(id);
        return this.eventRepository.save(events);
    }

    @Override
    public Page<Events> findPaginated(Pageable pageable) {
        return this.eventRepository.findAll(pageable);
    }
    
    

}
