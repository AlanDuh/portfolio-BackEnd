package com.portfolio.backend.model;

import com.portfolio.backend.dto.ImageDto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Image {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String path;
    @ManyToMany(mappedBy = "imagesP")
    private List<Project> projects = new ArrayList<>();
    @ManyToMany(mappedBy = "imagesB")
    private List<OwnerInfo> banners = new ArrayList<>();

    public Image() {
    }

    public Image(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public Image(Long id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }
    
    public ImageDto getImageDto() {
        return new ImageDto(id, name, path);
    }
    
}
