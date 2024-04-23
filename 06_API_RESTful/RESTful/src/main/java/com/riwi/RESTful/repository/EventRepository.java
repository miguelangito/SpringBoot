package com.riwi.RESTful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.RESTful.entity.Events;

@Repository
public interface EventRepository extends JpaRepository<Events, String> {
    
}
