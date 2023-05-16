package com.portfolio.backend.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DeleteResponseDto implements Serializable {
    
    private String msg;

    public DeleteResponseDto(String msg) {
        this.msg = msg;
    }

    public DeleteResponseDto() {
    }
    
}
