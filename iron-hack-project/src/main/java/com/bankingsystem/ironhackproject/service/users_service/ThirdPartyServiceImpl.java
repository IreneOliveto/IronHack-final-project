package com.bankingsystem.ironhackproject.service.users_service;

import com.bankingsystem.ironhackproject.model.users.ThirdParty;
import com.bankingsystem.ironhackproject.repository.users_repository.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {
    @Autowired
    ThirdPartyRepository thirdPartyRepository;

    @Override
    public ThirdParty saveNewThirdParty(ThirdParty thirdParty) {
        return thirdPartyRepository.save(thirdParty);
    }
}
