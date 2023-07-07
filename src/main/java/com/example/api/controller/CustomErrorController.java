package com.example.api.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        logErrorInService();
        return "error"; // Exemplo: nome da página de erro no seu template (error.html, por exemplo)
    }

    public String getErrorPath() {
        return "/error";
    }

    private void logErrorInService() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
