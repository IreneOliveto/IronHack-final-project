package com.bankingsystem.ironhackproject.service.users_service;

import com.bankingsystem.ironhackproject.model.users.ThirdParty;
import com.bankingsystem.ironhackproject.repository.users_repository.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Override
    public ThirdParty saveNewThirdParty(ThirdParty newThirdParty) {
        return thirdPartyRepository.save(newThirdParty);
    }

    @Override
    public Optional<ThirdParty> findThirdPartyUserId(Integer userId) {
        return Optional.ofNullable(thirdPartyRepository.findThirdPartiesByUserId(userId));
    }

}
