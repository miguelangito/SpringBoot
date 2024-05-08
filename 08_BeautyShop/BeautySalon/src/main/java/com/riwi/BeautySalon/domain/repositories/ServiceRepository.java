package com.riwi.BeautySalon.domain.repositories;

import com.riwi.BeautySalon.domain.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository
        <ServiceEntity, Long> {

    @Query("select s from services s where s.price between :min and :max")
    public List<ServiceEntity> selectBetweenPrice(BigDecimal min, BigDecimal max);

    @Query("select s from services s where s.name like :search")
    public List<ServiceEntity> findByNameContaining(String search);


}
