package com.riwi.BeautySalon.infraestructure.service;

import com.riwi.BeautySalon.api.dto.request.ServiceReq;
import com.riwi.BeautySalon.api.dto.response.ServiceResp;
import com.riwi.BeautySalon.domain.entities.ServiceEntity;
import com.riwi.BeautySalon.domain.repositories.ServiceRepository;
import com.riwi.BeautySalon.infraestructure.abstract_service.IServiceService;
import com.riwi.BeautySalon.utils.enums.SortType;
import com.riwi.BeautySalon.utils.exception.BadRequestException;
import com.riwi.BeautySalon.utils.message.ErrorMessage;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ServiceService implements IServiceService {

    private final ServiceRepository serviceRepository;
    @Override
    public ServiceResp create(ServiceReq request) {
        ServiceEntity service = this.requestToEntity(request);

        return this.entityToResponse(this.serviceRepository.save(service));
    }

    @Override
    public ServiceResp get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public ServiceResp update(ServiceReq request, Long id) {
        ServiceEntity service = this.find(id);

        service = this.requestToEntity(request);
        service.setId(id);

        return this.entityToResponse(this.serviceRepository.save(service));
    }

    @Override
    public void delete(Long id) {
        this.serviceRepository.delete(this.find(id));
    }

    @Override
    public Page<ServiceResp> getAll(Integer page, Integer size, SortType sortType) {

        if (page < 0) page = 0;

        PageRequest pagination = null;

        switch (sortType){

            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        this.serviceRepository.findAll(pagination);

        return this.serviceRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    @Override
    public List<ServiceResp> search(String name) {
        return null;
    }

    private ServiceResp entityToResponse(ServiceEntity entity){
        

        return ServiceResp.builder()
        .id(entity.getId())
        .name(entity.getName())
        .price(entity.getPrice())
        .description(entity.getDescription())
        .build();
    }

    private ServiceEntity requestToEntity(ServiceReq serviceReq){

        return ServiceEntity.builder()
        .name(serviceReq.getName())
        .price(serviceReq.getPrice())
        .description(serviceReq.getDescription())
        .build();

    }

    private ServiceEntity find(Long id){
        return this.serviceRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("service")));
    }
}
