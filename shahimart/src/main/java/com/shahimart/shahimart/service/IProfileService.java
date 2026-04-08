package com.shahimart.shahimart.service;

import com.shahimart.shahimart.dto.ProfileRequestDto;
import com.shahimart.shahimart.dto.ProfileResponseDto;

public interface IProfileService {
    ProfileResponseDto getProfile();

    ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto);
}
