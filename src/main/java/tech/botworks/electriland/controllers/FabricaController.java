package tech.botworks.electriland.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.model.Model;
import tech.botworks.electriland.exceptions.MyException;
import tech.botworks.electriland.services.FabricaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping ("/crearfabrica")
public class FabricaController {
  @Autowired
  FabricaService fabricaService;

  @GetMapping("")
  public String registrar() {
      return "crearfabrica";
  }

  @PostMapping("/registro")
  public String registro(@RequestParam String nombre, ModelMap modelo) throws MyException{ 
    try {
      fabricaService.crearFabrica(nombre);
      modelo.put ("exito","La Nueva fábrica fue creada con éxito.");

    } catch (MyException e) {
      System.out.println(e.getMessage());
      modelo.put ("error",e.getMessage());

    }  
    return "crearfabrica";
  }
  
  
}
