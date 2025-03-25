package tech.botworks.electriland.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tech.botworks.electriland.services.ArticuloService;


@Controller
@RequestMapping ("/inicio")
public class InicioController {
  @Autowired
  ArticuloService articuloService;

  @GetMapping()  
  public String inicio() {
    return "inicio.html";
  }
  @GetMapping("/fabricas")
  public String listarFabricas() {
      return "listafabrica";
  }

    @GetMapping("/articulos")
  public String listarArticulos(ModelMap model) {
    model.put("articulos", articuloService.listarArticulos());  
    return "listaarticulo";
  }
  
}
