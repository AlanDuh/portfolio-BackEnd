package com.portfolio.backend.model;

import com.portfolio.backend.dto.ImageDto;
import com.portfolio.backend.dto.OwnerInfoDto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class OwnerInfo {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private String description;
    @ManyToMany
    @JoinTable(
            name = "banner_images",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<Image> imagesB = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Image photo;

    public OwnerInfo() {
    }

    public OwnerInfo(String description, Image photo) {
        this.description = description;
        this.photo = photo;
    }
    
    public OwnerInfoDto getContentDto(Long cardId, String cardName, int cardIdx) {
        
        List<ImageDto> bannerDto = new ArrayList<>();
        for (Image img : imagesB) {
            bannerDto.add(img.getImageDto());
        }
        ImageDto photoDto = null;
        if (photo != null) {
            photoDto = photo.getImageDto();
        }
        OwnerInfoDto contentDto = new OwnerInfoDto(
                cardId, 
                cardName, 
                cardIdx,
                "OwnerInfo", 
                description, 
                bannerDto, 
                photoDto
        );
        
        return contentDto;
        
    }
    
}
