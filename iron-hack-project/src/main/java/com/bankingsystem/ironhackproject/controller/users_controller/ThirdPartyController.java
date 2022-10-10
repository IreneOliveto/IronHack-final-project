package com.bankingsystem.ironhackproject.controller.users_controller;

import com.bankingsystem.ironhackproject.model.users.ThirdParty;
import com.bankingsystem.ironhackproject.service.users_service.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ThirdPartyController {

    @Autowired
    ThirdPartyService thirdPartyService;

    @PostMapping("/third-party/")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty saveNewThirdParty(@Valid @RequestBody ThirdParty thirdParty) {
        return thirdPartyService.saveNewThirdParty(thirdParty);
    }

}
