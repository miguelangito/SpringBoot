package com.riwi.vacants.utils.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyToVacantResponse {
    
    private String id;
    private String name;
    private String location;
    private String contact;
}
