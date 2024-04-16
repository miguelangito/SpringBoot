package com.riwi.holamundo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


//indica que esta clase es un controlador
@Controller

//RequestMapping crea la ruta base del controlador
@RequestMapping("/controller")
public class HolaMundo {

    //GetMapping crea una ruta especifica para el metodo
    @GetMapping("/holamundo")
    //ResponseBody nos permite responder en el navegador
    @ResponseBody
    public String mostrarMensaje(){
        return "Hola mundo";
    }

    //Crear una ruta especifica para la suma
    @GetMapping("/suma")
    //Mostrarlo en el navegador
    @ResponseBody
    public String suma(){
        int num1 = 2;
        int num2 = 2;
        return String.valueOf(num1 + num2);
    }
}
