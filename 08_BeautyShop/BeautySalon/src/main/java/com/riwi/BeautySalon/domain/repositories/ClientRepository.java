package com.riwi.BeautySalon.domain.repositories;

import com.riwi.BeautySalon.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository
        <Client, Long> {}
