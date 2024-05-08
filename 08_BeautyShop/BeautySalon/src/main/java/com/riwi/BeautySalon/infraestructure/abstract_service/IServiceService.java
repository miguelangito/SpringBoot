package com.riwi.BeautySalon.infraestructure.abstract_service;

import com.riwi.BeautySalon.api.dto.request.ServiceReq;
import com.riwi.BeautySalon.api.dto.response.ServiceResp;

import java.util.List;

public interface IServiceService extends CrudService<ServiceReq, ServiceResp, Long> {

    public List<ServiceResp> search(String name);

    public String FIELD_BY_SORT = "price";
}
