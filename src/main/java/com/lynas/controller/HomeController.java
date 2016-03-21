package com.lynas.controller;

import com.lynas.model.AppUser;
import com.lynas.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sazzad on 2/11/16.
 */
@RestController
public class HomeController {

    @Autowired
    AppUserService appUserService;



    @RequestMapping(value = "/")
    @ResponseBody
    public String home(){
        appUserService.insertAppUser(new AppUser("tes2t","test22"));

        return "home";
    }
}
