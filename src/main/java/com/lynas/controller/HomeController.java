package com.lynas.controller;

import com.lynas.model.AppUser;
import com.lynas.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LynAs on 20-Jan-16
 */
@RestController
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    AppUserService appUserService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> home() {
        AppUser appUser = appUserService.findById(1);
        return ResponseEntity.ok(appUser);
    }
}


















