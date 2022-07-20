package com.cindy.ocrdemo.dto;

import lombok.Data;

@Data
public class UserReturnDTO {

    private Long userId;

    private String username;

    private String role;

    private String token;

}
