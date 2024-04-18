package com.riwi.primeraweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.riwi.primeraweb.entity.Coders;
import com.riwi.primeraweb.services.CoderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





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
        objModel.addAttribute("coderList", list);

        return "viewCoder";
    }

    @GetMapping("/create")
    public String showFormCoder(Model objModel) {
        
        objModel.addAttribute("coder", new Coders());
        objModel.addAttribute("action", "/create/coder");

        return "createCoder";

    }

    //metodo para insertar un coder mediante un verbo POST
    //@ModelAttribute se encarga de obtener la informacion enviada desde la vista
    @PostMapping("/create/coder")
    public String createCoder(@ModelAttribute Coders objCoders) {
        
        //Llamamos al servicio enviandole el coder a insertar
        this.objCoderService.insertCoders(objCoders);

        return "redirect:/";

        
    }
    
    
}
