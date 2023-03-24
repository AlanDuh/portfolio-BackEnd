package com.portfolio.backend.repository;

import com.portfolio.backend.model.Image;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    
    public Image findByName(String name);
    public List<Image> findByNameIn(List<String> names);
    
}
