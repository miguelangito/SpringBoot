package com.riwi.API.Rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 */
@RestController
@RequestMapping("/api/greet")
public class HelloController {

    @GetMapping
    public String greet(){
        return "Hola Mundo";
    }

}
