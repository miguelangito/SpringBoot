package com.riwi.API.Rest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.API.Rest.entities.Operations;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/operations")
public class OperationsController {
    
    @PostMapping(path = "/add")
    public String add(@RequestBody Operations operations) { 
        String message = String.valueOf( operations.getNum1() + operations.getNum2());
        
        return operations.getNum1() + " + " + operations.getNum2() + " = " + message;

    }

    @PostMapping(path = "subtract")
    public String subtract(@RequestBody Operations operations) {
        String message = String.valueOf(operations.getNum1() - operations.getNum2());

        return operations.getNum1() + " - " + operations.getNum2() + " = " + message;
    }

    @PostMapping(path = "divide")
    public String divide(@RequestBody Operations operations) {

        if (operations.getNum2() == 0) {
            return "cant divide numbers between 0";
        }else{
            String message = String.valueOf(operations.getNum1() / operations.getNum2());
            return operations.getNum1() + " / " + operations.getNum2() + " = " + message;
        }


    }

    @PostMapping(path = "multiply")
    public String multiply(@RequestBody Operations operations) {
        String message = String.valueOf(operations.getNum1() * operations.getNum2());

        return operations.getNum1() + " * " + operations.getNum2() + " = " + message;
    }
    
    

}
