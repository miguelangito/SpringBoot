package com.riwi.vacants.utils.DTO.response;

import com.riwi.vacants.utils.enums.StatusVacant;

import lombok.Data;


@Data
public class VacantResponse {
    
    private Long id;
    private String title;
    private String description;
    private StatusVacant status;
    private CompanyToVacantResponse company;

}
