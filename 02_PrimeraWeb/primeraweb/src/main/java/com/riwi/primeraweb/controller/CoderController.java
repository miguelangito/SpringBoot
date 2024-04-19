package com.riwi.primeraweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riwi.primeraweb.entity.Coders;
import com.riwi.primeraweb.services.CoderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/")
public class CoderController {

    @Autowired
    private CoderService objCoderService;


    @GetMapping("/create")
    public String showFormCoder(Model objModel) {

        objModel.addAttribute("coder", new Coders());
        objModel.addAttribute("action", "/create/coder");

        return "createCoder";

    }

    // metodo para insertar un coder mediante un verbo POST
    // @ModelAttribute se encarga de obtener la informacion enviada desde la vista
    @PostMapping("/create/coder")
    public String createCoder(@ModelAttribute Coders objCoders) {

        // Llamamos al servicio enviandole el coder a insertar
        this.objCoderService.insertCoders(objCoders);

        return "redirect:/";

    }

    @GetMapping("/update/{id}")
    public String updateCoder(@PathVariable Long id, Model objModel) {
        Coders objCodersFind = this.objCoderService.findById(id);
        objModel.addAttribute("coder", objCodersFind);
        objModel.addAttribute("action", "/edit/" + id);
        return "createCoder";
    }

    @PostMapping("/edit/{id}")
    public String updateCoder(@PathVariable Long id, @ModelAttribute Coders objCoders) {
        this.objCoderService.update(id, objCoders);
        return "/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCoder(@PathVariable Long id, Model objModel) {

        this.objCoderService.deletCoder(id);

        return "redirect:/";
    }

    @GetMapping("/")
    public String showViewGetAll(Model objModel, @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size) {
        // LLamo el servicio y guardo la lista de coders
        Page<Coders> list = this.objCoderService.fingPaginated(page - 1, size);
        objModel.addAttribute("coderList", list); // Llave- valor
        objModel.addAttribute("currentPage", page); // Llave- valor
        objModel.addAttribute("totalPages", list.getTotalPages()); // Llave- valor

        // Se debe retornar el nombre exacto de la vista html
        return "viewCoder";
    }
}
