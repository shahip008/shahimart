package com.shahimart.shahimart.service;

import com.shahimart.shahimart.dto.ContactRequestDto;

public interface IContactService {
    boolean saveContact(ContactRequestDto contactRequestDto);
}
