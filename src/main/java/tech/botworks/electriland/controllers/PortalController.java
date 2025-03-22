package tech.botworks.electriland.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping ("/")
public class PortalController {
  
  @GetMapping("/")
    public String index() {
    return "index";
    //por ahora para desarrollar lo cambio de index a inicio
  }
    @GetMapping("/login")
    public String login() {
      return "login";
    }

    @GetMapping("/registrousuario")
    public String registrousuario() {
      return "registrousuario";
    }
}
    
    
