package tech.botworks.electriland.services;
import jakarta.annotation.PostConstruct;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import tech.botworks.electriland.entities.Articulo;
import tech.botworks.electriland.entities.Fabrica;
import tech.botworks.electriland.exceptions.MyException;
import tech.botworks.electriland.repositories.ArticuloRepository;
import tech.botworks.electriland.repositories.FabricaRepository;

@Service
public class ArticuloService {

  @Autowired
  ArticuloRepository articuloRepository;
  @Autowired
  FabricaRepository fabricaRepository;

  private AtomicInteger atomicInteger;

  @PostConstruct
  public void init() {
    // Initialize atomicInteger after dependencies are injected
    this.atomicInteger = new AtomicInteger(articuloRepository.encontrarNumeroProducto());
  }

  @Transactional
  public void crearArticulo(String nombreArticulo, String descripcionArticulo, String fabrica) throws MyException {
    validarNulidad(null, nombreArticulo, descripcionArticulo, fabrica, 1);
    validarExistencia(null, nombreArticulo, fabrica, 1);

    Optional<Fabrica> respuesta = fabricaRepository.findById(fabrica);
    if (respuesta.isPresent()) {
      Articulo articulo = new Articulo();
      articulo.setNombreArticulo(nombreArticulo);
      articulo.setDescripcionArticulo(descripcionArticulo);
      articulo.setNroArticulo(atomicInteger.incrementAndGet());
      articulo.setFabrica(respuesta.get());
      articuloRepository.save(articulo);
    } else {
      throw new MyException("La fábrica con ID " + fabrica + " no existe.");
    }
  }

  @Transactional
  public void modificarArticulo(String id, String nombre, String descripcion, String fabrica) throws MyException {
    validarNulidad(id, nombre, descripcion, fabrica, 2);
    validarExistencia(id, nombre, fabrica, 2);
    Optional<Fabrica> respuesta = fabricaRepository.findByNombreFabrica(fabrica);
    if (respuesta.isPresent()) {
      Articulo articulo = articuloRepository.findById(id).get();
      articulo.setNombreArticulo(nombre);
      articulo.setDescripcionArticulo(descripcion);
      articulo.setFabrica(respuesta.get());

      articuloRepository.save(articulo);
    }

  }

  public List<Articulo> listarArticulos() {
    return articuloRepository.findAll();
  }

  @Transactional
  public void borrarArticulo(String id) throws MyException {
    validarNulidad(id, null, null, null, 2);
    validarExistencia(id, null, null, 2);
    articuloRepository.deleteById(id);
  }

  public void validarNulidad(String id, String nombre, String descripcion, String fabrica, Integer metodo)
      throws MyException {
    if (nombre == null || nombre.isEmpty()) {
      throw new MyException("No has ingresado un nombre, intenta otra vez");
    } else if (descripcion == null || descripcion.isEmpty()) {
      throw new MyException("No has ingresado una descripción, intenta de nuevo)");
    } else if (fabrica == null || fabrica.isEmpty()) {
      throw new MyException("No has ingresado una fabrica, intenta de nuevo");
    }
    if ((id == null || id.isEmpty()) && metodo == 2) {
      throw new MyException("No has ingresado el artículo que quieres modificar.");
    }
  }

  public void validarExistencia(String id, String nombre, String fabrica, int metodo) throws MyException {
    if (metodo == 2 && (id == null || id.isEmpty() || articuloRepository.findById(id).isEmpty())) {
      throw new MyException("El artículo que quieres modificar no está en el sistema.");
    } else if (metodo == 1 && articuloRepository.findByNombreArticulo(nombre) != null) {
      throw new MyException("Este nombre ya se encuentra en el sistema.");
    } else if (metodo == 2 && (fabrica == null || fabrica.isEmpty() || fabricaRepository.findById(fabrica).isEmpty())) {
      throw new MyException("La fabrica no se encuentra en el sistema.");
    }
  }

}
