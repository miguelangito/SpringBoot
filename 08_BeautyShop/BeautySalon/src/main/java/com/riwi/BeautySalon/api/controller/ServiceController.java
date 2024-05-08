package com.riwi.BeautySalon.api.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.BeautySalon.api.dto.request.ServiceReq;
import com.riwi.BeautySalon.api.dto.response.ServiceResp;
import com.riwi.BeautySalon.infraestructure.abstract_service.IServiceService;
import com.riwi.BeautySalon.utils.enums.SortType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/services")
@AllArgsConstructor
public class ServiceController {
    
    @Autowired
    private final IServiceService service;

    @GetMapping
    public ResponseEntity<Page<ServiceResp>> getAll(
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestHeader(required = false) SortType sortType){
        if (Objects.isNull(sortType)) {
            sortType = sortType.NONE;
        }
        return ResponseEntity.ok(this.service.getAll(page - 1, size, sortType));
    }

    @PostMapping
    public ResponseEntity<ServiceResp> create(@Validated @RequestBody ServiceReq serviceReq){
        return ResponseEntity.ok(this.service.create(serviceReq));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ServiceResp> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.get(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delte(@PathVariable Long id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ServiceResp> update(@PathVariable Long id,@Validated @RequestBody ServiceReq serviceReq){
        return ResponseEntity.ok(this.service.update(serviceReq, id));
    }
}
