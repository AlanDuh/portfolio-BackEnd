package com.portfolio.backend.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LogResponseDto implements Serializable {
    
    private String token;
    private Long exp;

    public LogResponseDto(String token, Long exp) {
        this.token = token;
        this.exp = exp;
    }
    
}
