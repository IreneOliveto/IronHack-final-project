package com.bankingsystem.ironhackproject.service.users_service;

import com.bankingsystem.ironhackproject.model.users.ThirdParty;
import com.bankingsystem.ironhackproject.model.utils.Money;

import java.util.Optional;

public interface ThirdPartyService {
    ThirdParty saveNewThirdParty(ThirdParty thirdParty);

    Optional<ThirdParty> findThirdPartyUserId(Integer userId);
}
