package tech.botworks.electriland.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tech.botworks.electriland.entities.Usuario;
import tech.botworks.electriland.exceptions.MyException;
import tech.botworks.electriland.repositories.UsuarioRepository;

@Service
public class UsuarioService {


  @Autowired
  UsuarioRepository usuarioRepository;

  @Transactional
  public void crearUsuario(String email, String nombre, String apellido, String password, String password2)
      throws MyException {
    validarNulidad(null, email, nombre, apellido, password, password2, 1);
    validarExistencia(email, 1);
    // after validating we instanciate a new Usuario
    Usuario usuario = new Usuario();
    // we set its name
    usuario.setNombre(nombre);
    usuario.setApellido(apellido);
    usuario.setEmail(email);
    usuario.setPassword(password);

    // then we persit it to the database
    usuarioRepository.save(usuario);
  }

  @Transactional
  public void modificarUsuario(String id, String email, String nombre, String apellido) throws MyException {
    validarNulidad(id, email, nombre, apellido, null, null, 2);
    validarExistencia(id, 2);
    Usuario usuario = new Usuario();
    usuario = usuarioRepository.findById(id).get();
    usuario.setNombre(nombre);
    usuario.setApellido(apellido);
    usuario.setEmail(email);

    usuarioRepository.save(usuario);
  }

  public List<Usuario> listarUsuarios() {
    return usuarioRepository.findAll();
  }

  @Transactional
  public void borrarUsuario(String id) throws MyException{
    validarNulidad(id, null, null, null, null, null, 4);
    validarExistencia(id, 2);
    usuarioRepository.deleteById(id);
  }
  // VALIDACIÓN

  public void validarNulidad(String id, String email, String nombre, String apellido, String password, String password2,
      Integer metodo) throws MyException {
    if (((id == null) || (id == "")) && metodo == 4) {
      throw new MyException("Por favor ingresa un id.");
    } else if (email == null || email.isEmpty()) {
      throw new MyException("No has ingresado el email");
    } else if (nombre == null || nombre.isEmpty()) {
      throw new MyException("No has ingresado el nombre");
    } else if (apellido == null || apellido.isEmpty()) {
      throw new MyException("No has ingresado el apellido");
    } else if ((password == null || password.isEmpty()) && metodo == 1) {
      throw new MyException("No has ingresado el password");
    } else if (!password.equals(password2) && metodo == 1) {
      throw new MyException("Las claves de acceso no coinciden.");
    } else if ((id == null || id == "") && metodo == 2) {
      throw new MyException("No has ingresado el id del producto.");
    }
  }

  public void validarExistencia(String emailOid, int metodo) throws MyException {

    if ((usuarioRepository.findByNombre(emailOid) != null) && metodo == 1) {
      throw new MyException("Este email ya existe en el sistema.");
    } else if (!usuarioRepository.findById(emailOid).isPresent() && metodo == 2) {
      throw new MyException("Este usuario no está en el sistema.");
    }
  }
}
