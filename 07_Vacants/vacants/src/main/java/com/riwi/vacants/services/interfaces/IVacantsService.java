package com.riwi.vacants.services.interfaces;

import com.riwi.vacants.utils.DTO.request.VacantRequest;
import com.riwi.vacants.utils.DTO.response.VacantResponse;

/**
 * IVacantsService interface para establecer el contrato con el servicio
 */
public interface IVacantsService extends CrudService<VacantRequest,VacantResponse, Long>{
    
    
}