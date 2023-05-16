package com.portfolio.backend.repository;

import com.portfolio.backend.model.EducExpType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducExpTypeRepository extends JpaRepository<EducExpType, Long> {
    
}
