package com.portfolio.backend.service;

import com.portfolio.backend.model.EducExpType;
import com.portfolio.backend.repository.EducExpTypeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducExpTypeService implements IEducExpTypeService {
    
    @Autowired
    EducExpTypeRepository EETRepo;

    @Override
    public List<EducExpType> getEETypes() {
        List<EducExpType> EETypes = EETRepo.findAll();
        return EETypes;
    }
    
}
