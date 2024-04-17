package com.riwi.primeraweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.primeraweb.entity.Coders;

@Repository
public interface CodersRepository extends JpaRepository<Coders, Long> {
    
}
