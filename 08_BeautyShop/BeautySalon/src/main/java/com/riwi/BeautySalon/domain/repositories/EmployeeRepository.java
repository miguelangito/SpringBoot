package com.riwi.BeautySalon.domain.repositories;

import com.riwi.BeautySalon.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository
        <Employee, Long> {}
