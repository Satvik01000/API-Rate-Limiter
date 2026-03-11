package com.personalprojects.apiratelimiter.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class API {

    @GetMapping("/unlimited")
    String unlimitedAPI(){
        return "Use as much as you want this is unlimited API!\n";
    }

    @GetMapping("/limited")
    String limitedAPI(){
        return "Careful! this is limited API.\n";
    }
}
