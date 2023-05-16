package com.portfolio.backend.service;

import com.portfolio.backend.dto.ImageDto;
import com.portfolio.backend.model.Image;
import java.util.List;

public interface IImageService {
    
    public List<Image> getImages();
    public Image getImage(Long id);
    public Image findByName(String name);
    public List<Image> findByNameIn(List<String> names);
    public void addImage(Image img);
    public void addAllImages(List<Image> imgs);
    public Image addIfNotExist(ImageDto img);
    public List<Image> addIfNotExist(List<ImageDto> imgs);
    public void deleteImage(Long id);
    public void deleteImage(String name);
    
}
