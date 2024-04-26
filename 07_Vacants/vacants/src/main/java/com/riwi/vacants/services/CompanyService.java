package com.riwi.vacants.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.vacants.entities.Company;
import com.riwi.vacants.repository.CompanyRepository;
import com.riwi.vacants.services.interfaces.ICompanyService;
import com.riwi.vacants.utils.DTO.request.CompanyRequest;
import com.riwi.vacants.utils.DTO.response.CompanyResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyService implements ICompanyService {

    @Autowired
    private final CompanyRepository companyRepository;

    @Override
    public CompanyResponse create(CompanyRequest request) {
        return null;
    }

    @Override
    public Void delete(String id) {
        return null;
    }

    @Override
    public Page<CompanyResponse> getAll(int page, int size) {
        if (page < 0) {
            page = 0;
        }
        PageRequest pagination = PageRequest.of(page, size);
        return this.companyRepository.findAll(pagination).map(this::companyResponse);
    }

    @Override
    public CompanyResponse getById(String id) {
        return null;
    }

    @Override
    public CompanyResponse update(CompanyRequest request, String id) {
        return null;
    }    

    private CompanyResponse companyResponse(Company entity){
        CompanyResponse response = new CompanyResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
}
