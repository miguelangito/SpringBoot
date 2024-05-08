package com.riwi.BeautySalon.infraestructure.abstract_service;

import com.riwi.BeautySalon.api.dto.request.ClientReq;
import com.riwi.BeautySalon.api.dto.response.ClientResp;

public interface IClientService extends CrudService<ClientReq, ClientResp, Long>{
    
    public String FILED_BY_SORT = "fistName";
}
