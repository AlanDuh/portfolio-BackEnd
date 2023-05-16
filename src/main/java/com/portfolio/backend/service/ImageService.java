package com.portfolio.backend.service;

import com.portfolio.backend.dto.ImageDto;
import com.portfolio.backend.model.Image;
import com.portfolio.backend.repository.ImageRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService {
    
    @Autowired
    ImageRepository imgRepo;

    @Override
    public List<Image> getImages() {
        List<Image> imageList = imgRepo.findAll();
        return imageList;
    }

    @Override
    public Image getImage(Long id) {
        Image img = imgRepo.findById(id).orElse(null);
        return img;
    }

    @Override
    public Image findByName(String name) {
        Image img = imgRepo.findByName(name);
        return img;
    }

    @Override
    public void addImage(Image img) {
        imgRepo.save(img);
    }

    @Override
    public void deleteImage(Long id) {
        imgRepo.deleteById(id);
    }

    @Override
    public void deleteImage(String name) {
        Image img = imgRepo.findByName(name);
        if (img != null) {
            imgRepo.deleteById(img.getId());
        }
    }

    @Override
    public void addAllImages(List<Image> imgs) {
        imgRepo.saveAll(imgs);
    }

    @Override
    public List<Image> findByNameIn(List<String> names) {
        List<Image> imageList = imgRepo.findByNameIn(names);
        return imageList;
    }

    @Override
    public Image addIfNotExist(ImageDto img) {
        Image image = imgRepo.findByName(img.getName());
        if (image == null) {
            image = new Image(img.getName(), img.getPath());
            imgRepo.save(image);
        }
        return image;
    }

    @Override
    public List<Image> addIfNotExist(List<ImageDto> imgs) {
        List<String> names = new ArrayList<>();
        for (ImageDto img : imgs) {
            names.add(img.getName());
        }
        List<Image> oldImages = imgRepo.findByNameIn(names);
        int oldImagesSize = oldImages.size();
        List<Image> images = new ArrayList<>();
        List<Image> newImages = new ArrayList<>();
        for (ImageDto imgDto : imgs) {
            if (oldImages.isEmpty()) {
                Image newImage = new Image(imgDto.getName(), imgDto.getPath());
                images.add(newImage);
                newImages.add(newImage);
            } else {
                for (int i = 0; i < oldImagesSize; i++) {
                    Image img = oldImages.get(i);
                    if (img.getName().equals(imgDto.getName())) {
                        images.add(img);
                        break;
                    } else if ((i + 1) == oldImagesSize) {
                        Image newImage = new Image(imgDto.getName(), imgDto.getPath());
                        images.add(newImage);
                        newImages.add(newImage);
                    }
                }
            }
        }
        if (!newImages.isEmpty()) {
            imgRepo.saveAll(newImages);
        }
        return images;
    }
    
}
