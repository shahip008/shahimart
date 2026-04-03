package com.shahimart.shahimart.dto;

public record LoginResponseDto(String message, UserDto user, String jwtToken) {
}
