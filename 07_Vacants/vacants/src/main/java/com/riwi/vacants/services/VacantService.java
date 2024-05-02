package com.riwi.vacants.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.vacants.entities.Vacant;
import com.riwi.vacants.repository.CompanyRepository;
import com.riwi.vacants.repository.VacantRepository;
import com.riwi.vacants.services.interfaces.IVacantsService;
import com.riwi.vacants.utils.DTO.request.VacantRequest;
import com.riwi.vacants.utils.DTO.response.CompanyToVacantResponse;
import com.riwi.vacants.utils.DTO.response.VacantResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VacantService implements IVacantsService{

    @Autowired
    private final VacantRepository vacantRepository;

    @Autowired
    private final CompanyRepository companyRepository;

    @Override
    public VacantResponse create(VacantRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {
        
    }

    @Override
    public Page<VacantResponse> getAll(int page, int size) {
        if (page < 0 ) {
            page = 0;
        }

        PageRequest pageRequest = PageRequest.of(page, size);

        return this.vacantRepository.findAll(pageRequest).map(vacant -> this.entityToResponse(vacant)); 
    }

    @Override
    public VacantResponse getById(Long id) {
        return null;
    }

    @Override
    public VacantResponse update(VacantRequest request, Long id) {
        return null;
    }
    
    private VacantResponse entityToResponse(Vacant vacant){

        VacantResponse response = new VacantResponse();

        BeanUtils.copyProperties(vacant, response);
        
        CompanyToVacantResponse companyDto = new CompanyToVacantResponse();

        BeanUtils.copyProperties(vacant.getCompany(), companyDto);
        
        response.setCompany(companyDto);
        
        return response;
    }    
}
