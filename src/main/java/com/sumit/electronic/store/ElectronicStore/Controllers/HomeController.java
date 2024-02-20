package com.sumit.electronic.store.ElectronicStore.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HomeController {

    @GetMapping
    public String get(){
        return "Welcome to ELECTRONIC STORE";
    }
}
