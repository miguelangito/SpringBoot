package com.riwi.vacants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.vacants.services.interfaces.ICompanyService;
import com.riwi.vacants.utils.DTO.request.CompanyRequest;
import com.riwi.vacants.utils.DTO.response.CompanyResponse;

import lombok.AllArgsConstructor;

/**
 * CompanyController
 */
@RestController
@AllArgsConstructor
@RequestMapping(path = "/company")
public class CompanyController {
    
    @Autowired
    private final ICompanyService companyService;

    @GetMapping
    public ResponseEntity<Page<CompanyResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size){
        return ResponseEntity.ok(this.companyService.getAll(page - 1, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CompanyResponse> get(@PathVariable String id){
        return ResponseEntity.ok(this.companyService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> insert(@RequestBody CompanyRequest company){
        return ResponseEntity.ok(this.companyService.create(company));
    } 

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        this.companyService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CompanyResponse> update(@RequestBody CompanyRequest company,@PathVariable String id){

        return ResponseEntity.ok(this.companyService.update(company, id));
    }
}
