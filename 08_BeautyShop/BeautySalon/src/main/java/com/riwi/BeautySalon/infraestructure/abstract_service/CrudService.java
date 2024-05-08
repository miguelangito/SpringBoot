package com.riwi.BeautySalon.infraestructure.abstract_service;

import com.riwi.BeautySalon.utils.enums.SortType;
import org.springframework.data.domain.Page;

public interface CrudService<RQ, RS, ID>{

    RS create(RQ request);

    RS get(ID id);

    RS update(RQ request, ID id);

    void delete(ID id);

    Page<RS> getAll(Integer page, Integer size, SortType sortType);


}
