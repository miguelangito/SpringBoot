package com.riwi.vacants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.vacants.entities.Vacant;

@Repository
public interface VacantRepository extends JpaRepository<Vacant, Long>{
    
}
