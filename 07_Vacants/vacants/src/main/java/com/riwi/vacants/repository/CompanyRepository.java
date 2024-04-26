package com.riwi.vacants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.vacants.entities.Company;

/**
 * CompanyRepository
 */
@Repository
public interface CompanyRepository  extends JpaRepository<Company, String>{

    
}