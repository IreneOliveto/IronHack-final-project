package com.bankingsystem.ironhackproject.controller.users_controller;

import com.bankingsystem.ironhackproject.model.users.ThirdParty;
import com.bankingsystem.ironhackproject.service.users_service.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ThirdPartyController {

    @Autowired
    ThirdPartyService thirdPartyService;

    @GetMapping("/admin/third-party/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ThirdParty getThirdPartyByUserId (@PathVariable(value="userId") Integer userId) {
        return thirdPartyService.findThirdPartyUserId(userId).get();
    }


    @PostMapping("/third-party/")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty saveNewThirdParty(@Valid @RequestBody ThirdParty thirdParty) {
        return thirdPartyService.saveNewThirdParty(thirdParty);
    }

}
