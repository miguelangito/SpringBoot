package com.riwi.primeraweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.riwi.primeraweb.entity.Coders;
import com.riwi.primeraweb.services.CoderService;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
@RequestMapping("/")
public class CoderController {
    
    @Autowired
    private CoderService objCoderService;


    //Metodo para mostrar la vista y enviarle la lista de coders
    @GetMapping
    public String findAll(Model objModel){
        
        //Llamo del servicio y guardo en la lista de Coders
        List<Coders> list = this.objCoderService.findAllCoders();
        System.out.println(list);
        objModel.addAttribute("coderList", list);

        return "viewCoder";
    }

    @GetMapping("/create")
    public String createCoder(Model objModel) {
        return "createCoder";
    }
    
}
