package com.shahimart.shahimart.dto;

import lombok.*;

@Getter @Setter
@ToString
public class UserDto {
    private Long userId;
    private String name;
    private String email;
    private String mobileNumber;
}
