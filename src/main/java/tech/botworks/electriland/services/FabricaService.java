package tech.botworks.electriland.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tech.botworks.electriland.entities.Fabrica;
import tech.botworks.electriland.exceptions.MyException;
import tech.botworks.electriland.repositories.FabricaRepository;

@Service
public class FabricaService {

  @Autowired
  FabricaRepository fabricaRepository;

  @Transactional
  public void crearFabrica(String nombre) throws MyException {
    validarNulidad(nombre);
    validarExistencia(nombre, 1);
    // after validating we instanciate a new Fabrica
    Fabrica fabrica = new Fabrica();
    // we set its name
    fabrica.setNombreFabrica(nombre);
    // then we persit it to the database
    fabricaRepository.save(fabrica);
  }

  @Transactional
  public void modificarFabrica(String nombre, String idFabrica) throws MyException {
    validarNulidad(nombre);
    validarNulidad(idFabrica);
    validarExistencia(idFabrica, 2);
    Fabrica resultado = fabricaRepository.findById(idFabrica).get();
    resultado.setNombreFabrica(nombre);
    fabricaRepository.save(resultado);
  }

  public List<Fabrica> listarFabricas() throws MyException {
    return fabricaRepository.findAll();
  }

  @Transactional
  public void borrarFabrica(String id) throws MyException {
    validarNulidad(id);
    validarExistencia(id, 2);
    fabricaRepository.deleteById(id);
  }

  public Fabrica buscarPorId(String id) throws MyException {
    validarNulidad(id);
    validarExistencia(id, 2);
    return fabricaRepository.findById(id).get();
  }

  public void validarNulidad(String idONombre) throws MyException {
    if (idONombre==null || idONombre.isEmpty()){
      throw new MyException ("No has ingresado un nombre, intenta otra vez");
  }
  }

  public void validarExistencia(String id, int metodo) throws MyException {
    
    if (fabricaRepository.findByNombreFabrica(id).isPresent() && metodo==1){
          throw new MyException ("Esta fábrica ya existe en el sistema.");
  } 
  else if (!fabricaRepository.findById(id).isPresent()&& metodo==2){ 
      throw new MyException("Esta fábrica no está en el sistema.");
  }
}
  
}
