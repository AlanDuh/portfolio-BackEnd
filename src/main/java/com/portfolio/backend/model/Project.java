package com.portfolio.backend.model;

import com.portfolio.backend.dto.ImageDto;
import com.portfolio.backend.dto.ProjectDto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Project {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    private String description;
    private Date date;
    private String pageLink;
    private String gitHubLink;
    @ManyToMany
    @JoinTable(
            name = "project_images",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<Image> imagesP = new ArrayList<>();

    public Project() {
    }

    public Project(String description, Date date, String pageLink, String gitHubLink) {
        this.description = description;
        this.date = date;
        this.pageLink = pageLink;
        this.gitHubLink = gitHubLink;
    }
    
    public ProjectDto getContentDto(Long cardId, String cardName, int cardIdx) {
        
        List<ImageDto> images = new ArrayList<>();
        for (Image img : imagesP) {
            images.add(img.getImageDto());
        }
        
        ProjectDto content = new ProjectDto(
                cardId, 
                cardName, 
                cardIdx,
                "Project", 
                description, 
                date, 
                images, 
                pageLink, 
                gitHubLink
        );
        return content;
        
    }
    
}
