package com.riwi.BeautySalon.domain.repositories;

import com.riwi.BeautySalon.domain.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRespository extends JpaRepository
        <Appointment, Long> {

    @Query("select p from Appointments p join fetch p.client c where c.id = :idClient")
    public Optional<Appointment> findByClientId(Long idClient);
}
