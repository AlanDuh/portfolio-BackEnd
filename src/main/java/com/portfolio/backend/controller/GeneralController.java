package com.portfolio.backend.controller;

import com.portfolio.backend.dto.CardDto;
import com.portfolio.backend.dto.DeleteResponseDto;
import com.portfolio.backend.dto.EducExpDto;
import com.portfolio.backend.dto.HardSkillDto;
import com.portfolio.backend.dto.ImageDto;
import com.portfolio.backend.dto.LogResponseDto;
import com.portfolio.backend.dto.OwnerInfoDto;
import com.portfolio.backend.dto.ProjectDto;
import com.portfolio.backend.dto.SoftSkillDto;
import com.portfolio.backend.model.Account;
import com.portfolio.backend.model.Card;
import com.portfolio.backend.model.CardType;
import com.portfolio.backend.model.EducExp;
import com.portfolio.backend.model.EducExpType;
import com.portfolio.backend.model.HardSkill;
import com.portfolio.backend.model.Image;
import com.portfolio.backend.model.MoveMessage;
import com.portfolio.backend.model.OwnerInfo;
import com.portfolio.backend.model.Point;
import com.portfolio.backend.model.Project;
import com.portfolio.backend.model.SoftSkill;
import com.portfolio.backend.service.IAccountService;
import com.portfolio.backend.service.ICardService;
import com.portfolio.backend.service.ICardTypeService;
import com.portfolio.backend.service.IEducExpTypeService;
import com.portfolio.backend.service.IImageService;
import com.portfolio.backend.utils.JWTUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {
    
    @Autowired
    ICardService interCard;
    @Autowired
    ICardTypeService interCT;
    @Autowired
    IEducExpTypeService interEET;
    @Autowired
    IImageService interImg;
    @Autowired
    IAccountService interAcc;
    @Autowired
    JWTUtil jwtUtil;
    
    private List<CardType> cardTypes = new ArrayList<>();
    private List<EducExpType> educExpTypes = new ArrayList<>();
    private final String addPath = "/cards/agregar/";
    private final String editPath = "/cards/editar/";
    private final String frontPath = "*";
    
    private CardType selectType(String type) {
        
        return cardTypes.stream()
                .filter(ct -> ct.getType().equals(type))
                .collect(Collectors.toList())
                .get(0);
                        
    }
    
    private boolean verifyToken(String token) {
        
        return jwtUtil.getKey(token) != null;
        
    }
    
    @CrossOrigin(origins = frontPath)
    @GetMapping("/cards/traer")
    public List<CardDto> getCards() {
        List<Card> cardList = interCard.getCards();
        List<CardDto> dtoList = new ArrayList<>();
        
        for (Card card : cardList) {
            dtoList.add(card.getCardDto());
        }
        
        return dtoList;
        
    }
    
    @CrossOrigin(origins = frontPath)
    @GetMapping("/cards/traer/{id}")
    public CardDto getCard(@PathVariable Long id) {
        Card card = interCard.getCard(id);
        return card.getCardDto();
    }
    
    @CrossOrigin(origins = frontPath)
    @GetMapping("/images/traer")
    public List<ImageDto> getImages() {
        List<Image> images = interImg.getImages();
        List<ImageDto> imagesDto = new ArrayList<>();
        
        for (Image img : images) {
            imagesDto.add(img.getImageDto());
        }
        
        return imagesDto;
        
    }
    
    @CrossOrigin(origins = frontPath)
    @PostMapping(addPath + "ownerInfo")
    public CardDto addOwnerInfo(@RequestBody OwnerInfoDto cont,
                               @RequestHeader(value = "Authorization") String token) {
        
        if (!verifyToken(token)) {
            return null;
        }
        
        if (cardTypes.isEmpty()) {
            cardTypes = interCT.getCardTypes();
        }
        
        List<Image> newBanner = interImg.addIfNotExist(cont.getImages());
        Image newPhoto = interImg.addIfNotExist(cont.getPhoto());
        
        OwnerInfo newContent = new OwnerInfo(cont.getDescription(), newPhoto);
        newContent.setImagesB(newBanner);
        
        Card newCard = new Card(
            cont.getName(), 
            cont.getIdx(),
            selectType("OwnerInfo")
        );
        newCard.setOIContent(newContent);
        
        interCard.addCard(newCard);
        
        return newCard.getCardDto();
        
    }
    
    @CrossOrigin(origins = frontPath)
    @PostMapping(addPath + "educExp")
    public CardDto addEducExp(@RequestBody EducExpDto cont,
                             @RequestHeader(value = "Authorization") String token) {
        
        if (!verifyToken(token)) {
            return null;
        }
        
        if (cardTypes.isEmpty()) {
            cardTypes = interCT.getCardTypes();
        }
        
        if (educExpTypes.isEmpty()) {
            educExpTypes = interEET.getEETypes();
        }
        
        EducExp newContent = new EducExp(
                educExpTypes.stream()
                    .filter(eet -> eet.getType().equals(cont.getType()))
                    .collect(Collectors.toList())
                    .get(0), 
                cont.getTitle(),
                cont.getInstitution(), 
                interImg.addIfNotExist(cont.getInstitutionImage()), 
                cont.getDate1(), 
                cont.getDate2(), 
                cont.getDescription()
        );
        
        Card newCard = new Card(
                cont.getName(), 
                cont.getIdx(),
                selectType("EducExp")
        );
        newCard.setEEContent(newContent);
        
        interCard.addCard(newCard);
        
        System.out.println(newCard.getId());
        
        return newCard.getCardDto();
        
    }
    
    @CrossOrigin(origins = frontPath)
    @PostMapping(addPath + "hardSkill")
    public CardDto addHardSkill(@RequestBody HardSkillDto cont,
                               @RequestHeader(value = "Authorization") String token) {
        
        if (!verifyToken(token)) {
            return null;
        }
        
        if (cardTypes.isEmpty()) {
            cardTypes = interCT.getCardTypes();
        }
        
        HardSkill newContent = new HardSkill(
                cont.getVal(),
                interImg.addIfNotExist(cont.getBackground()),
                cont.getPoints()
        );
        
        Card newCard = new Card(
                cont.getName(), 
                cont.getIdx(),
                selectType("HardSkill")
        );
        newCard.setHSContent(newContent);
        
        interCard.addCard(newCard);
        
        return newCard.getCardDto();
        
    }
    
    @CrossOrigin(origins = frontPath)
    @PostMapping(addPath + "softSkill")
    public CardDto addSoftSkill(@RequestBody SoftSkillDto cont,
                               @RequestHeader(value = "Authorization") String token) {
        
        if (!verifyToken(token)) {
            return null;
        }
        
        if (cardTypes.isEmpty()) {
            cardTypes = interCT.getCardTypes();
        }
        
        SoftSkill newContent = new SoftSkill(
                cont.getDescription(), 
                cont.getSubSkills()
        );
        
        Card newCard = new Card(
                cont.getName(), 
                cont.getIdx(),
                selectType("SoftSkill")
        );
        newCard.setSSContent(newContent);
        
        interCard.addCard(newCard);
        
        return newCard.getCardDto();
        
    }
    
    @CrossOrigin(origins = frontPath)
    @PostMapping(addPath + "project")
    public CardDto addProject(@RequestBody ProjectDto cont,
                             @RequestHeader(value = "Authorization") String token) {
        
        if (!verifyToken(token)) {
            return null;
        }
        
        if (cardTypes.isEmpty()) {
            cardTypes = interCT.getCardTypes();
        }
        
        List<Image> images = interImg.addIfNotExist(cont.getImages());
        Project newContent = new Project(
                cont.getDescription(), 
                cont.getDate(), 
                cont.getPageLink(), 
                cont.getGitHubLink()
        );
        newContent.setImagesP(images);
        
        Card newCard = new Card(
                cont.getName(), 
                cont.getIdx(),
                selectType("Project")
        );
        newCard.setPJContent(newContent);
        
        interCard.addCard(newCard);
        
        return newCard.getCardDto();
        
    }
    
    @CrossOrigin(origins = frontPath)
    @PostMapping("/account/tryToLog")
    public LogResponseDto tryToLog(@RequestBody Account log) {
        
        if (interAcc.tryToLog(log)) {
            
            String tokenJwt = jwtUtil.create(log.getUsername(), log.getPassword());
            Long exp = jwtUtil.getTtlMillis();
            
            return new LogResponseDto(tokenJwt, exp);
            
        }
        
        return null;
        
    }
    
    @CrossOrigin(origins = frontPath)
    @PutMapping(editPath + "ownerInfo")
    public CardDto editOwnerInfo(@RequestBody OwnerInfoDto cont,
                                @RequestHeader(value = "Authorization") String token) {
        
        if (!verifyToken(token)) {
            return null;
        }
        
        Card card = interCard.getCard(cont.getId());
        OwnerInfo content = card.getOIContent();
        
        card.setName(cont.getName());
        content.setDescription(cont.getDescription());
        if (
            cont.getPhoto() == null
        ) {
            content.setPhoto(null);
        } else if (
            (content.getPhoto() == null) ||
            (content.getPhoto().getName() != cont.getPhoto().getName())
        ) {
            content.setPhoto(
                    interImg.addIfNotExist(cont.getPhoto())
            );  
        }
        if (!Objects.equals(
                cont.getImages()
                        .stream().map(img -> img.getName())
                        .collect(Collectors.toList()),
                content.getImagesB()
                        .stream().map(img -> img.getName())
                        .collect(Collectors.toList())
        )) {
            content.setImagesB(
                    interImg.addIfNotExist(cont.getImages())
            );
        }
        
        interCard.addCard(card);
        
        return card.getCardDto();
        
    }
    
    @CrossOrigin(origins = frontPath)
    @PutMapping(editPath + "educExp")
    public CardDto editEducExp(@RequestBody EducExpDto cont,
                              @RequestHeader(value = "Authorization") String token) {
        
        if (!verifyToken(token)) {
            return null;
        }
        
        Card card = interCard.getCard(cont.getId());
        EducExp content = card.getEEContent();
        
        card.setName(cont.getName());
        content.setTitle(cont.getTitle());
        content.setInstitution(cont.getInstitution());
        content.setDate1(cont.getDate1());
        content.setDate2(cont.getDate2());
        content.setDescription(cont.getDescription());
        if (
            cont.getInstitutionImage() == null
        ) {
            content.setInstitutionImage(null);
        } else if (
            (content.getInstitutionImage() == null) ||
            (content.getInstitutionImage().getName() != cont.getInstitutionImage().getName())
        ) {
            content.setInstitutionImage(
                    interImg.addIfNotExist(cont.getInstitutionImage())
            );  
        }
        
        interCard.addCard(card);
        
        return card.getCardDto();
        
    }
    
    @CrossOrigin(origins = frontPath)
    @PutMapping(editPath + "hardSkill")
    public CardDto editHardSkill(@RequestBody HardSkillDto cont,
                                @RequestHeader(value = "Authorization") String token) {
        
        if (!verifyToken(token)) {
            return null;
        }
        
        Card card = interCard.getCard(cont.getId());
        HardSkill content = card.getHSContent();
        
        card.setName(cont.getName());
        content.setVal(cont.getVal());
        if (
            cont.getBackground()== null
        ) {
            content.setBackground(null);
        } else if (
            (content.getBackground() == null) ||
            (content.getBackground().getName() != cont.getBackground().getName())
        ) {
            content.setBackground(
                    interImg.addIfNotExist(cont.getBackground())
            );  
        }
        content.getPoints().clear();
        content.getPoints().addAll(cont.getPoints());
        
        interCard.addCard(card);
        
        return card.getCardDto();
        
    }
    
    @CrossOrigin(origins = frontPath)
    @PutMapping(editPath + "softSkill")
    public CardDto editSoftSkill(@RequestBody SoftSkillDto cont,
                                @RequestHeader(value = "Authorization") String token) {
        
        if (!verifyToken(token)) {
            return null;
        }
        
        Card card = interCard.getCard(cont.getId());
        SoftSkill content = card.getSSContent();
        
        card.setName(cont.getName());
        content.setDescription(cont.getDescription());
        
        content.getSubSkills().clear();
        content.getSubSkills().addAll(cont.getSubSkills());
        
        interCard.addCard(card);
        
        return card.getCardDto();
        
    }
    
    @CrossOrigin(origins = frontPath)
    @PutMapping(editPath + "project")
    public CardDto editProject(@RequestBody ProjectDto cont,
                              @RequestHeader(value = "Authorization") String token) {
        
        if (!verifyToken(token)) {
            return null;
        }
        
        Card card = interCard.getCard(cont.getId());
        Project content = card.getPJContent();
        
        card.setName(cont.getName());
        content.setDescription(cont.getDescription());
        content.setDate(cont.getDate());
        if (!Objects.equals(
                cont.getImages()
                        .stream().map(img -> img.getName())
                        .collect(Collectors.toList()),
                content.getImagesP()
                        .stream().map(img -> img.getName())
                        .collect(Collectors.toList())
        )) {
            content.setImagesP(
                    interImg.addIfNotExist(cont.getImages())
            );
        }
        content.setPageLink(cont.getPageLink());
        content.setGitHubLink(cont.getGitHubLink());
        
        interCard.addCard(card);
        
        return card.getCardDto();
        
    }
    
    @CrossOrigin(origins = frontPath)
    @PutMapping("/cards/mover")
    public void cardMove(@RequestBody List<MoveMessage> movements,
                         @RequestHeader(value = "Authorization") String token) {
        
        if (!verifyToken(token)) {
            return;
        }
        
        List<Card> cards = interCard.getCards(movements
                .stream()
                .map(mov -> mov.getCardId())
                .collect(Collectors.toList())
        );
        for (MoveMessage movement : movements) {
            for (Card card : cards) {
                if (Objects.equals(card.getId(), movement.getCardId())) {
                    card.setIdx(movement.getNewPosition());
                }
            }
        }
        interCard.addCards(cards);
        
    }
    
    @CrossOrigin(origins = frontPath)
    @DeleteMapping("/cards/borrar/{id}")
    public DeleteResponseDto deleteCard(@PathVariable Long id,
                             @RequestHeader(value = "Authorization") String token) {
        
        if (!verifyToken(token)) {
            return null;
        }
        
        interCard.deleteCard(id);
        return new DeleteResponseDto("La carta (id = " + id + ") se eliminó correctamente");
        
    }
    
    @CrossOrigin(origins = frontPath)
    @DeleteMapping("/images/borrar")
    public String deleteImage(@RequestParam ("id") Optional<Long> id,
                              @RequestParam ("nombre") Optional<String> name,
                              @RequestHeader(value = "Authorization") String token) {
        
        if (!verifyToken(token)) {
            return null;
        }
        
        if (id.isPresent()) {
        
            interImg.deleteImage(id.get());
            return "La imagen (id = " + id.get() + ") se eliminó correctamente";
            
        } else if (name.isPresent()) {
        
            interImg.deleteImage(name.get());
            return "La imagen (nombre = " + name.get() + ") se eliminó correctamente";
        
        }
        
        return "No se eliminó ninguna imágen. Especifique un ID o NOMBRE para eliminar una imágen";
        
    }
    
}
