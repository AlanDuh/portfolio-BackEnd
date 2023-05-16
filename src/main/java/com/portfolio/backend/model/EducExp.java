package com.portfolio.backend.model;

import com.portfolio.backend.dto.EducExpDto;
import com.portfolio.backend.dto.ImageDto;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class EducExp {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private EducExpType type;
    private String title;
    private String institution;
    @OneToOne
    @JoinColumn(name = "instimage_id", referencedColumnName = "id")
    private Image institutionImage;
    private Date date1;
    private Date date2;
    private String description;

    public EducExp() {
    }

    public EducExp(EducExpType type, String title, String institution, Image institutionImage, Date date1, Date date2, String description) {
        this.type = type;
        this.title = title;
        this.institution = institution;
        this.institutionImage = institutionImage;
        this.date1 = date1;
        this.date2 = date2;
        this.description = description;
    }
    
    public EducExpDto getContentDto(Long cardId, String cardName, int cardIdx) {
        
        ImageDto instImgDto = null;
        if (institutionImage != null) {
            instImgDto = institutionImage.getImageDto();
        }
        EducExpDto content = new EducExpDto(
                cardId, 
                cardName, 
                cardIdx,
                type.getType(), 
                title, 
                institution, 
                instImgDto,
                date1, 
                date2, 
                description
        );
        return content;
        
    }
    
}
