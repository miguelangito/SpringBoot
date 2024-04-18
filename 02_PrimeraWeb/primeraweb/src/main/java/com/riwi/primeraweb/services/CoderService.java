package com.riwi.primeraweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riwi.primeraweb.entity.Coders;
import com.riwi.primeraweb.repository.CodersRepository;

@Service
public class CoderService {

    //Autowired le indica a springboot que estos es una inyeccion de dependencias
    @Autowired
    private CodersRepository objCodersRepository;
    
    //Servicio para listar todos los coders
    public List<Coders> findAllCoders(){
        return this.objCodersRepository.findAll();
    }

    public Coders insertCoders(Coders objCoders){
        return this.objCodersRepository.save(objCoders);
    }

}