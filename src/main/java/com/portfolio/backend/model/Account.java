package com.portfolio.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Account {
    
    @Id
    private String username;
    private String password;
    
}
