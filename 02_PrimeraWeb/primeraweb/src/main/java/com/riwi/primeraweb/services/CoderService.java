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

    public Coders update(Long id,Coders objCoders){
        Coders objCodersBD = this.findById(id);

        if (objCodersBD == null) return null;

        objCodersBD = objCoders;
        return this.objCodersRepository.save(objCodersBD);
    }

    public Coders findById(Long id){
        return this.objCodersRepository.findById(id).orElse(null);
    }

    public void deletCoder(Long id){
        
        this.objCodersRepository.deleteById(id);

    }
}