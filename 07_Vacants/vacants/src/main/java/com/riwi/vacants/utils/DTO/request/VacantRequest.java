package com.riwi.vacants.utils.DTO.request;

import com.riwi.vacants.utils.enums.StatusVacant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacantRequest {
    
    private String title;
    private String description;
    private String desciption;
    private StatusVacant statud;
    private String companyId;

}
