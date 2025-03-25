package tech.botworks.electriland.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tech.botworks.electriland.exceptions.MyException;
import tech.botworks.electriland.repositories.ArticuloRepository;
import tech.botworks.electriland.services.ArticuloService;
import tech.botworks.electriland.services.FabricaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/creararticulo")
public class ArticuloController {

  @Autowired
  ArticuloService articuloService;
  @Autowired
  FabricaService fabricaService;

  @GetMapping("")
  public String mostrarRegistro(ModelMap model) throws MyException {
    try {
      model.put("fabricas", fabricaService.listarFabricas());
    } catch (MyException e) {
      model.put("error", e.getMessage());
    }

    return "creararticulo";
  }

  @PostMapping("/enviar")
  public String enviarRegistro(@RequestParam String nombre, @RequestParam String descripcion, @RequestParam String id, ModelMap model) throws MyException {
    try {

      articuloService.crearArticulo(nombre, descripcion, id);
      model.put("exito", "El artículo" + nombre + "ha sido creado exitósamente");

    } catch (MyException e) {
      model.put("error", e.getMessage());
      model.put("nombre", nombre);
      model.put("descripcion", descripcion);
      model.put("id", id);
    }
    model.put("fabricas", fabricaService.listarFabricas());
    return "creararticulo";
  }

}
