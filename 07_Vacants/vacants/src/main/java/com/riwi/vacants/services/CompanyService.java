package com.riwi.vacants.services;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.vacants.entities.Company;
import com.riwi.vacants.entities.Vacant;
import com.riwi.vacants.repository.CompanyRepository;
import com.riwi.vacants.services.interfaces.ICompanyService;
import com.riwi.vacants.utils.DTO.request.CompanyRequest;
import com.riwi.vacants.utils.DTO.response.CompanyResponse;
import com.riwi.vacants.utils.DTO.response.VacantToCompany;
import com.riwi.vacants.utils.exceptions.IdNotFoundExeption;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyService implements ICompanyService {

    @Autowired
    private final CompanyRepository companyRepository;

    @Override
    public CompanyResponse create(CompanyRequest request) {
        
        Company company = this.requestToEntity(request,new Company());
        return this.entityToResponse(this.companyRepository.save(company));
    }

    @Override
    public void delete(String id) {

        Company company = this.getId(id);

        this.companyRepository.delete(company);
    }

    @Override
    public Page<CompanyResponse> getAll(int page, int size) {
        if (page < 0) {
            page = 0;
        }
        PageRequest pagination = PageRequest.of(page, size);
        return this.companyRepository.findAll(pagination).map(this::entityToResponse);
    }

    @Override
    public CompanyResponse getById(String id) {
        Company company = this.getId(id);
        return this.entityToResponse(company);
    }

    @Override
    public CompanyResponse update(CompanyRequest request, String id) {
        Company companyToUpdate = this.getId(id);

        Company company = this.requestToEntity(request, companyToUpdate);
        return this.entityToResponse(this.companyRepository.save(company));
    }    

    private CompanyResponse entityToResponse(Company entity){
        CompanyResponse response = new CompanyResponse();

        BeanUtils.copyProperties(entity, response);

        response.setVacants(entity.getVacantList().stream()
        .map(this::vacantToResponse)
        .collect(Collectors.toList()));
        return response;
    }

    private VacantToCompany vacantToResponse(Vacant entity){
        VacantToCompany response = new VacantToCompany();

        BeanUtils.copyProperties(entity, response);

        return response;
    }

    private Company requestToEntity(CompanyRequest entity, Company company){

        BeanUtils.copyProperties(entity, company);
        company.setVacantList(new ArrayList<>());
        return company;
    }

    private Company getId(String id){
        return this.companyRepository.findById(id).orElseThrow(() -> new IdNotFoundExeption("Company"));
        
    }
}
