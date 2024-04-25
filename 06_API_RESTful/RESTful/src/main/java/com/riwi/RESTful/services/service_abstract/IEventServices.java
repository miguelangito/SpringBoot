package com.riwi.RESTful.services.service_abstract;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.riwi.RESTful.entity.Events;

public interface IEventServices {
    
    public Events create(Events events);
    public List<Events> listAll();
    public Events findbyId(String id);
    public Events update(String id, Events events);
    public void delete(String id);
    public Page<Events> findPaginated(Pageable pageable);
}
